import { Component, EventEmitter, Output } from '@angular/core';
import { Reader } from '../../../core/models/reader';
import { FormBuilder, FormControl, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ReaderService } from '../reader.service';
import { BehaviorSubject, combineLatest, debounceTime, distinctUntilChanged, forkJoin, map, Observable, of, startWith, switchMap } from 'rxjs';

@Component({
  selector: 'app-search',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule],
  templateUrl: './search.component.html',
  styleUrl: './search.component.scss'
})
export class SearchComponent {
  @Output() searchResults = new EventEmitter<Reader[]>();
  searchForm!: FormGroup;

  name$ = new BehaviorSubject<string>('');
  email$ = new BehaviorSubject<string>('');
  phone$ = new BehaviorSubject<string>('');

  constructor(private fb: FormBuilder, private readerService: ReaderService) { }

  ngOnInit() {
    this.searchForm = this.fb.group({
      name: [''],
      email: [''],
      phone: ['']
    });

    this.searchForm.get('name')?.valueChanges.subscribe(value => this.name$.next(value));
    this.searchForm.get('email')?.valueChanges.subscribe(value => this.email$.next(value));
    this.searchForm.get('phone')?.valueChanges.subscribe(value => this.phone$.next(value));
  }


  onSearch() {
    const { name, email, phone } = this.searchForm.value;

    let searchRequests: Observable<Reader[]>[] = [];

    if (name) {
      searchRequests.push(this.readerService.searchByName(name));
    }

    if (email) {
      searchRequests.push(this.readerService.searchByEmail(email));
    }

    if (phone) {
      searchRequests.push(this.readerService.searchByPhoneNumber(phone));
    }

    if (searchRequests.length === 0) {
      return;
    }

    forkJoin(searchRequests).subscribe((resultsArray: Reader[][]) => {
      const mergedResults = resultsArray.flat();
      this.searchResults.emit(mergedResults);
    });
  }
}
