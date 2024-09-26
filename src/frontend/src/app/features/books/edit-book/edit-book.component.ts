import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { BookService } from '../book.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Book } from '../../../core/models/book';

@Component({
  selector: 'app-edit-book',
  standalone: true,
  imports: [ReactiveFormsModule, FormsModule],
  templateUrl: './edit-book.component.html',
  styleUrl: './edit-book.component.scss'
})
export class EditBookComponent {
  editBookForm!: FormGroup;
  bookId!: number;
  book!: Book;

  constructor(
    private fb: FormBuilder,
    private bookService: BookService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.editBookForm = this.fb.group({
      title: [{ value: '', disabled: true }, [Validators.required]],
      status: ['', [Validators.required]]
    });

    this.bookId = +this.route.snapshot.paramMap.get('id')!;

    this.bookService.getAllBooks().subscribe(books => {
      this.book = books.find(b => b.id === this.bookId)!;
      if (this.book) {
        this.editBookForm.patchValue({
          title: this.book.title,
          status: this.book.status
        });
      }
    });
  }

  onSubmit(): void {
    console.log(this.editBookForm.value);
    this.bookService.editBook(this.bookId, this.editBookForm.value).subscribe(() => {
      this.router.navigate(['/books']);
    });
  }

  cancel(): void {
    this.router.navigate(['/books']);
  }
}
