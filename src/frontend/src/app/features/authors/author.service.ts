import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { Author } from '../../core/models/author';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AuthorService {
  private baseUrl = 'http://localhost:8080/api/v1/authors';
  private authorsSubject = new BehaviorSubject<Author[]>([]);
  authors$ = this.authorsSubject.asObservable();

  constructor(private http: HttpClient) {
    this.loadAuthors();
  }

  private loadAuthors() {
    this.http.get<Author[]>(this.baseUrl).subscribe(authors => this.authorsSubject.next(authors));
  }

  getAllAuthors(): Observable<Author[]> {
    return this.authors$;
  }

  searchByBookTitle(title: string): Observable<Author[]> {
    return this.http.get<Author[]>(`${this.baseUrl}/searchByBookTitle/${title}`);
  }

  addAuthor(author: Author): Observable<Author> {
    return this.http.post<Author>(this.baseUrl, author, this.getHttpOptions()).pipe(
      tap(() => this.loadAuthors())
    );
  }

  editAuthor(id: number, author: Author): Observable<Author> {
    return this.http.put<Author>(`${this.baseUrl}/${id}`, author, this.getHttpOptions()).pipe(
      tap(() => this.loadAuthors())
    );
  }

  deleteAuthor(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`, this.getHttpOptions()).pipe(
      tap(() => this.loadAuthors())
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
