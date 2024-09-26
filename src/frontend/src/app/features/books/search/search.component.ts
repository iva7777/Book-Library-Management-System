import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Output } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { BehaviorSubject, combineLatest, debounceTime, forkJoin, merge, Observable, switchMap } from 'rxjs';
import { BookService } from '../book.service';
import { Book } from '../../../core/models/book';

@Component({
  selector: 'app-search',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './search.component.html',
  styleUrl: './search.component.scss'
})
export class SearchComponent {
  @Output() searchResults = new EventEmitter<Book[]>();
  searchForm!: FormGroup;
  statuses = ['available', 'borrowed', 'discarded'];

  title$ = new BehaviorSubject<string>('');
  author$ = new BehaviorSubject<string>('');
  status$ = new BehaviorSubject<string>('');
  isbn$ = new BehaviorSubject<string>('');
  genre$ = new BehaviorSubject<string>('');

  constructor(private fb: FormBuilder, private bookService: BookService) { }

  ngOnInit() {
    this.searchForm = this.fb.group({
      title: [''],
      author: [''],
      status: [''],
      isbn: [''],
      genre: ['']
    });

    this.searchForm.get('title')?.valueChanges.subscribe(value => this.title$.next(value));
    this.searchForm.get('author')?.valueChanges.subscribe(value => this.author$.next(value));
    this.searchForm.get('status')?.valueChanges.subscribe(value => this.status$.next(value));
    this.searchForm.get('isbn')?.valueChanges.subscribe(value => this.isbn$.next(value));
    this.searchForm.get('genre')?.valueChanges.subscribe(value => this.genre$.next(value));
  }


  onSearch() {
    const { title, author, status, isbn, genre } = this.searchForm.value;

    let searchRequests: Observable<Book[]>[] = [];

    if (title) {
      searchRequests.push(this.bookService.searchByTitle(title));
    }

    if (author) {
      searchRequests.push(this.bookService.searchByAuthorName(author));
    }

    if (genre) {
      searchRequests.push(this.bookService.searchByGenre(genre));
    }

    if (isbn && status) {
      searchRequests.push(this.bookService.searchByIsbnAndStatus(isbn, status));
    }

    if (searchRequests.length === 0) {
      return;
    }

    forkJoin(searchRequests).subscribe((resultsArray: Book[][]) => {
      const mergedResults = resultsArray.flat();
      this.searchResults.emit(mergedResults);
    });
  }
}
