import { isPlatformBrowser } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Inject, Injectable, PLATFORM_ID } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8080/api/v1';

  constructor(private http: HttpClient, private router: Router, @Inject(PLATFORM_ID) private platformId: Object) {
    if (isPlatformBrowser(this.platformId)) {
      this.startTokenExpiryCheck();
    }
  }

  login(username: string, password: string): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/auth/authenticate`, { username, password })
      .pipe(tap(response => this.storeToken(response.token)));
  }

  register(email: string, username: string, password: string): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/auth/register`, { email, username, password })
      .pipe(tap(response => this.storeToken(response.token)));
  }

  private storeToken(token: string): void {
    if (isPlatformBrowser(this.platformId)) {
      localStorage.setItem('token', token);
    }
  }

  getToken(): string | null {
    if (isPlatformBrowser(this.platformId)) {
      return localStorage.getItem('token');
    }
    return null;
  }

  getDecodedToken(): any {
    const token = this.getToken();
    if (token) {
      return JSON.parse(atob(token.split('.')[1]));
    }
    return null;
  }

  getUserRole(): string | null {
    const decodedToken = this.getDecodedToken();
    return decodedToken ? decodedToken.role : null;
  }

  isLoggedIn(): boolean {
    const token = this.getToken();
    return !!token && !this.isTokenExpired(token);
  }

  isTokenExpired(token: string): boolean {
    const expiry = JSON.parse(atob(token.split('.')[1])).exp;
    return (Math.floor((new Date).getTime() / 1000)) >= expiry;
  }

  logout(): void {
    localStorage.removeItem('token');
    localStorage.removeItem('role');
    this.router.navigate(['/authenticate']);
  }

  private startTokenExpiryCheck(): void {
    setInterval(() => {
      const token = this.getToken();
      if (token && this.isTokenExpired(token)) {
        this.logout();
      }
    }, 60000); // Check every minute
  }
}
