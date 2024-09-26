import { Component } from '@angular/core';
import { FormControl, ReactiveFormsModule } from '@angular/forms';
import { Book } from '../../../../core/models/book';
import { BookService } from '../../book.service';
import { debounceTime } from 'rxjs';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-search-by-genre',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './search-by-genre.component.html',
  styleUrl: './search-by-genre.component.scss'
})
export class SearchByGenreComponent {
  genreControl = new FormControl('');
  books: Book[] = [];
  noResults: boolean = false;

  constructor(private bookService: BookService) { }

  ngOnInit() {
    this.genreControl.valueChanges.pipe(
      debounceTime(300)
    ).subscribe((genre) => {
      if (genre) {
        this.bookService.searchByGenre(genre).subscribe((books) => {
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
