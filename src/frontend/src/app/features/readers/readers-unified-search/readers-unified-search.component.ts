import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { Reader } from '../../../core/models/reader';
import { ReaderService } from '../reader.service';
import { ReaderSearchRequest } from '../../search-models/reader-search-request';

@Component({
  selector: 'app-readers-unified-search',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './readers-unified-search.component.html',
  styleUrl: './readers-unified-search.component.scss'
})
export class ReadersUnifiedSearchComponent {
  searchForm!: FormGroup;
  searchResults: Reader[] = [];

  constructor(private fb: FormBuilder, private readerService: ReaderService) { }

  ngOnInit(): void {
    this.searchForm = this.fb.group({
      query: [''], // Single input for unified search
    });
  }

  onSearch(): void {
    const query = this.searchForm.value.query;

    const searchRequest: ReaderSearchRequest = { query };

    this.readerService.searchByCriteria(searchRequest).subscribe(
      (results: Reader[]) => {
        this.searchResults = results;
      },
      (error) => {
        console.error('Error fetching search results:', error);
      }
    );
  }
}
