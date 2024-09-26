import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { Book } from '../../../core/models/book';
import { BookService } from '../book.service';
import { BookSearchRequest } from '../../search-models/book-search-request';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-books-unified-search',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './books-unified-search.component.html',
  styleUrl: './books-unified-search.component.scss'
})
export class BooksUnifiedSearchComponent {
  searchForm!: FormGroup;
  searchResults: Book[] = [];

  constructor(private fb: FormBuilder, private bookService: BookService) { }

  ngOnInit(): void {
    this.searchForm = this.fb.group({
      query: [''], // Single input for unified search
    });
  }

  onSearch(): void {
    const query = this.searchForm.value.query;

    const searchRequest: BookSearchRequest = { query };

    this.bookService.searchByCriteria(searchRequest).subscribe(
      (results: Book[]) => {
        this.searchResults = results;
      },
      (error) => {
        console.error('Error fetching search results:', error);
      }
    );
  }
}
