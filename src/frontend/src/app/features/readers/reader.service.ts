import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { Reader } from '../../core/models/reader';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ReaderSearchRequest } from '../search-models/reader-search-request';

@Injectable({
  providedIn: 'root'
})
export class ReaderService {
  private baseUrl = 'http://localhost:8080/api/v1/readers';
  private readersSubject = new BehaviorSubject<Reader[]>([]);
  readers$ = this.readersSubject.asObservable();

  constructor(private http: HttpClient) {
    this.loadReaders();
  }

  private loadReaders() {
    this.http.get<Reader[]>(this.baseUrl).subscribe(readers => this.readersSubject.next(readers));
  }

  getAllReaders(): Observable<Reader[]> {
    return this.readers$;
  }

  searchByUserId(userId: number): Observable<Reader[]> {
    return this.http.get<Reader[]>(`${this.baseUrl}/searchByUserId/${userId}`);
  }

  searchByPhoneNumber(phone: string): Observable<Reader[]> {
    return this.http.get<Reader[]>(`${this.baseUrl}/searchByPhoneNumber/${phone}`);
  }

  searchByName(name: string): Observable<Reader[]> {
    return this.http.get<Reader[]>(`${this.baseUrl}/searchByName/${name}`);
  }

  searchByEmail(email: string): Observable<Reader[]> {
    return this.http.get<Reader[]>(`${this.baseUrl}/searchByEmail/${email}`);
  }

  searchByCriteria(searchRequest: ReaderSearchRequest): Observable<Reader[]> {
    return this.http.post<Reader[]>(`${this.baseUrl}/search`, searchRequest);
  }

  getLoggedReader(): Observable<Reader> {
    return this.http.get<Reader>(`${this.baseUrl}/loggedReader`);
  }

  addReader(reader: Reader): Observable<Reader> {
    return this.http.post<Reader>(this.baseUrl, reader, this.getHttpOptions()).pipe(
      tap(() => this.loadReaders())
    );
  }

  editReader(id: number, reader: Reader): Observable<Reader> {
    return this.http.put<Reader>(`${this.baseUrl}/${id}`, reader, this.getHttpOptions()).pipe(
      tap(() => this.loadReaders())
    );
  }

  deleteReader(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`, this.getHttpOptions()).pipe(
      tap(() => this.loadReaders())
    );
  }

  private getHttpOptions() {
    const token = localStorage.getItem('token');
    return {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${token}`
      })
    };
  }
}
