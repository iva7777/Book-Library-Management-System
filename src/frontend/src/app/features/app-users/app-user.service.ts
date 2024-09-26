import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { AppUser } from '../../core/models/app-user';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AppUserService {
  private baseUrl = 'http://localhost:8080/api/v1/users';
  private usersSubject = new BehaviorSubject<AppUser[]>([]);
  users$ = this.usersSubject.asObservable();

  constructor(private http: HttpClient) { 
    this.loadUsers();
  }

  private loadUsers() {
    this.http.get<AppUser[]>(this.baseUrl, this.getHttpOptions()).subscribe(users => this.usersSubject.next(users));
  }

  getAllUsers(): Observable<AppUser[]> {
    return this.users$;
  }

  searchByUsername(username: string): Observable<AppUser[]> {
    return this.http.get<AppUser[]>(`${this.baseUrl}/searchByUsername/${username}`, this.getHttpOptions());
  }

  searchByRole(role: string): Observable<AppUser[]> {
    return this.http.get<AppUser[]>(`${this.baseUrl}/searchByRole/${role}`, this.getHttpOptions());
  }

  addUser(user: AppUser): Observable<AppUser> {
    return this.http.post<AppUser>(this.baseUrl, user, this.getHttpOptions()).pipe(
      tap(() => this.loadUsers())
    );
  }

  editUser(id: number, user: AppUser): Observable<AppUser> {
    return this.http.put<AppUser>(`${this.baseUrl}/${id}`, user, this.getHttpOptions()).pipe(
      tap(() => this.loadUsers())
    );

  }

  deleteUser(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`, this.getHttpOptions()).pipe(
      tap(() => this.loadUsers())
    );
  }

  private getHttpOptions() {
    const token = localStorage.getItem('token');
    console.log('Token from localStorage:', token);
    return {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${token}`
      })
    };
}

}
