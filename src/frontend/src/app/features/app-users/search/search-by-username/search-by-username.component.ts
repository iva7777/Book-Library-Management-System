import { Component, EventEmitter, Output } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { AppUser } from '../../../../core/models/app-user';
import { AppUserService } from '../../app-user.service';
import { BehaviorSubject, forkJoin, Observable } from 'rxjs';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-search-by-username',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './search-by-username.component.html',
  styleUrl: './search-by-username.component.scss'
})
export class SearchByUsernameComponent {
  @Output() searchResults = new EventEmitter<AppUser[]>();
  searchForm!: FormGroup;
  roles = ['reader', 'librarian', 'admin'];

  username$ = new BehaviorSubject<string>('');
  role$ = new BehaviorSubject<string>('');

  constructor(private fb: FormBuilder, private appUserService: AppUserService) {}

  ngOnInit() {
    this.searchForm = this.fb.group({
      username: [''],
      role: ['']
    });

    this.searchForm.get('username')?.valueChanges.subscribe(value => this.username$.next(value));
    this.searchForm.get('role')?.valueChanges.subscribe(value => this.role$.next(value));
  }

  onSearch() {
    const { username, role } = this.searchForm.value;
    let searchRequests: Observable<AppUser[]>[] = [];

    if (username) {
      searchRequests.push(this.appUserService.searchByUsername(username));
    }
    if (role) {
      searchRequests.push(this.appUserService.searchByRole(role));
    }
    if (searchRequests.length === 0) {
      return;
    }

    forkJoin(searchRequests).subscribe((resultsArray: AppUser[][]) => {
      const mergedResults = resultsArray.flat();

      const filteredResults = mergedResults.filter(user =>
        (!username || user.username.includes(username)) &&
        (!role || user.role === role)
      );

      this.searchResults.emit(filteredResults);
    });
  }
}
