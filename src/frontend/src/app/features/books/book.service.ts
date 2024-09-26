import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { Book } from '../../core/models/book';
import { BookSearchRequest } from '../search-models/book-search-request';

@Injectable({
  providedIn: 'root'
})
export class BookService {
  private baseUrl = 'http://localhost:8080/api/v1/books';
  private booksSubject = new BehaviorSubject<Book[]>([]);
  books$ = this.booksSubject.asObservable();

  constructor(private http: HttpClient) { 
    this.loadBooks();
  }

  private loadBooks() {
    this.http.get<Book[]>(this.baseUrl).subscribe(books => this.booksSubject.next(books));
  }

  getAllBooks(): Observable<Book[]> {
    return this.books$;
  }

  searchByGenre(genre: string): Observable<Book[]> {
    return this.http.get<Book[]>(`${this.baseUrl}/searchByGenre/${genre}`);
  }

  searchByTitle(title: string): Observable<Book[]> {
    return this.http.get<Book[]>(`${this.baseUrl}/searchByBookTitle/${title}`);
  }

  searchByAuthorName(name: string): Observable<Book[]> {
    return this.http.get<Book[]>(`${this.baseUrl}/searchByAuthorName/${name}`);
  }

  search(query: string): Observable<Book[]> {
    return this.http.get<Book[]>(`${this.baseUrl}/search/${query}`);
  }

  searchByIsbnAndStatus(isbn: string, status: string): Observable<Book[]> {
    return this.http.get<Book[]>(`${this.baseUrl}/searchByIsbnStatus/${isbn}/${status}`);
  }

  searchByCriteria(searchRequest: BookSearchRequest): Observable<Book[]> {
    return this.http.post<Book[]>(`${this.baseUrl}/search`, searchRequest);
  }

  addBook(book: Book): Observable<Book> {
    return this.http.post<Book>(this.baseUrl, book, this.getHttpOptions()).pipe(
      tap(() => this.loadBooks())
    );
  }

  editBook(id: number, book: Book): Observable<Book> {
    const body = JSON.stringify({ status: book.status });

    return this.http.put<Book>(`${this.baseUrl}/${id}`, body, this.getHttpOptions()).pipe(
      tap(() => this.loadBooks())
    );
  }

  deleteBook(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`, this.getHttpOptions()).pipe(
      tap(() => this.loadBooks())
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
