import { Component, EventEmitter, Output } from '@angular/core';
import { Author } from '../../../core/models/author';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { BehaviorSubject, debounceTime, switchMap } from 'rxjs';
import { AuthorService } from '../author.service';

@Component({
  selector: 'app-search',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './search.component.html',
  styleUrl: './search.component.scss'
})
export class SearchComponent {
  searchForm: FormGroup;

  @Output() searchResults = new EventEmitter<Author[]>();

  constructor(
    private fb: FormBuilder,
    private authorService: AuthorService
  ) {
    this.searchForm = this.fb.group({
      bookTitle: ['']
    });
  }

  ngOnInit() {
    this.searchForm.get('bookTitle')?.valueChanges.pipe(
      debounceTime(3000),
      switchMap(title => this.authorService.searchByBookTitle(title))
    ).subscribe(authors => {
      this.searchResults.emit(authors);
    });
  }
}
