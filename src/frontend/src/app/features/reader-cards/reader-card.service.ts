import { Injectable } from '@angular/core';
import { BehaviorSubject, map, Observable, tap } from 'rxjs';
import { ApiResponse, ReaderCard } from '../../core/models/reader-card';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ReaderCardService {
  private baseUrl = 'http://localhost:8080/api/v1/reader-cards';
  private readerCardsSubject = new BehaviorSubject<ReaderCard[]>([]);
  readerCards$ = this.readerCardsSubject.asObservable();

  constructor(private http: HttpClient) {
    this.loadReaderCards();
  }

  private loadReaderCards() {
    this.http.get<ReaderCard[]>(this.baseUrl).subscribe(cards => this.readerCardsSubject.next(cards));
  }

  getAllReaderCards(): Observable<ReaderCard[]> {
    return this.readerCards$;
  }

  searchByReaderId(readerId: number): Observable<ReaderCard> {
    return this.http.get<ReaderCard>(`${this.baseUrl}/searchByReaderId/${readerId}`);
  }

  searchByUserId(userId: number): Observable<ReaderCard> {
    return this.http.get<ReaderCard>(`${this.baseUrl}/searchByUserId/${userId}`);
  }  

  getOwnReaderCard(): Observable<ReaderCard> {
    return this.http.get<ApiResponse<ReaderCard>>(`${this.baseUrl}/getOwnReaderCard`).pipe(map(res => res.data));
  }


  addReaderCard(readerCard: ReaderCard): Observable<ReaderCard> {
    return this.http.post<ReaderCard>(this.baseUrl, readerCard, this.getHttpOptions()).pipe(
      tap(() => this.loadReaderCards())
    );
  }

  editReaderCard(id: number, readerCard: ReaderCard): Observable<ReaderCard> {
    return this.http.put<ReaderCard>(`${this.baseUrl}/${id}`, readerCard, this.getHttpOptions()).pipe(
      tap(() => this.loadReaderCards())
    );
  }

  deleteReaderCard(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`, this.getHttpOptions()).pipe(
      tap(() => this.loadReaderCards())
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
