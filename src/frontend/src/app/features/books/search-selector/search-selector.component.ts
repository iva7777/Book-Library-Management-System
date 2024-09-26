import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-search-selector',
  standalone: true,
  imports: [],
  templateUrl: './search-selector.component.html',
  styleUrl: './search-selector.component.scss'
})
export class SearchSelectorComponent {
  constructor(private router: Router) {
  }

  onSearchTypeChange(event: Event): void {
    const target = event.target as HTMLSelectElement;
    const searchType = target.value;

    if (searchType) {
      this.router.navigate([`/search/${searchType}`]);
    }
  }
}
