import { Component } from '@angular/core';
import { FormControl, ReactiveFormsModule } from '@angular/forms';
import { Book } from '../../../../core/models/book';
import { BookService } from '../../book.service';
import { debounceTime } from 'rxjs';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-search-by-author',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './search-by-author.component.html',
  styleUrl: './search-by-author.component.scss'
})
export class SearchByAuthorComponent {
  authorControl = new FormControl('');
  books: Book[] = [];
  noResults: boolean = false;

  constructor(private bookService: BookService) { }

  ngOnInit() {
    this.authorControl.valueChanges.pipe(
      debounceTime(300) // Wait 300ms after typing stops
    ).subscribe((name) => {
      if (name) {
        this.bookService.searchByAuthorName(name).subscribe((books) => {
          this.books = books;
          this.noResults = books.length === 0;
        });
      } else {
        this.books = [];
        this.noResults = false;
      }
    });
  }
}
