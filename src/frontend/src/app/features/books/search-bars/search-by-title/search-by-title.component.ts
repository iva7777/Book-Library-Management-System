import { Component } from '@angular/core';
import { Book } from '../../../../core/models/book';
import { BookService } from '../../book.service';
import { CommonModule } from '@angular/common';
import { FormControl, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { debounceTime } from 'rxjs';

@Component({
  selector: 'app-search-by-title',
  standalone: true,
  imports: [CommonModule, FormsModule, ReactiveFormsModule],
  templateUrl: './search-by-title.component.html',
  styleUrl: './search-by-title.component.scss'
})
export class SearchByTitleComponent {
  titleControl = new FormControl('');
  books: Book[] = [];
  noResults: boolean = false;

  constructor(private bookService: BookService) { }

  ngOnInit() {
    this.titleControl.valueChanges.pipe(
      debounceTime(300) // Wait 300ms after typing stops
    ).subscribe((title) => {
      if (title) {
        this.bookService.searchByTitle(title).subscribe((books) => {
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


