import { Component } from '@angular/core';
import { Book } from '../../../core/models/book';
import { BookService } from '../book.service';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-create-book',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './create-book.component.html',
  styleUrl: './create-book.component.scss'
})
export class CreateBookComponent {
  book: Book = {
    id: 0,
    title: '',
    authorNames: '',
    yearOfProduction: 0,
    genre: '',
    isbn: '',
    publisher: '',
    status: '',
    description: ''
  };

  constructor(private bookService: BookService, private router: Router) {}

  onSubmit(): void {
    this.bookService.addBook(this.book).subscribe(() => {
      this.router.navigate(['/books']);
    });
  }
}
