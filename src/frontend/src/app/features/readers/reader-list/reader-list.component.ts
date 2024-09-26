import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { Reader } from '../../../core/models/reader';
import { ReaderService } from '../reader.service';
import { AuthService } from '../../../core/auth/auth.service';
import { ReaderDetailsComponent } from '../reader-details/reader-details.component';
import { DeleteReaderConfirmationComponent } from "../delete-reader-confirmation/delete-reader-confirmation.component";
import { SearchComponent } from "../../readers/search/search.component";
import { PaginationComponent } from "../../../core/components/pagination/pagination.component";
import { LoadingService } from '../../../shared/services/loading.service';
import { ReadersUnifiedSearchComponent } from "../readers-unified-search/readers-unified-search.component";
import { CapitalizePipe } from '../../../shared/pipes/capitalize.pipe';
import { UppercasePipe } from '../../../shared/pipes/uppercase.pipe';

@Component({
  selector: 'app-reader-list',
  standalone: true,
  imports: [
    CommonModule, 
    RouterLink, 
    ReaderDetailsComponent, 
    DeleteReaderConfirmationComponent, 
    SearchComponent, 
    PaginationComponent, 
    ReadersUnifiedSearchComponent,
    CapitalizePipe,
    UppercasePipe
  ],
  templateUrl: './reader-list.component.html',
  styleUrl: './reader-list.component.scss'
})
export class ReaderListComponent {
  readers: Reader[] = [];
  paginatedItems: any[] = [];
  totalItems: number = 0;
  isLibrarian: boolean = false;
  selectedReader!: Reader;
  isModalOpen: boolean = false;
  showDeleteModal: boolean = false;
  isLoading$ = this.loadingService.loading$;

  constructor(private readerService: ReaderService, private authService: AuthService, private loadingService: LoadingService) {
  }

  ngOnInit(): void {
    this.isLibrarian = this.authService.getUserRole() === 'librarian';
    this.loadReaders();
  }

  openReaderDetails(reader: Reader): void {
    this.selectedReader = reader;
    this.isModalOpen = true;
  }

  closeReaderDetails(): void {
    this.isModalOpen = false;
  }

  loadReaders(): void {
    this.readerService.getAllReaders().subscribe((readers) => {
      this.readers = readers;
      console.log(this.readers);
      this.totalItems = readers.length;
      this.updatePagination();
    });
  }

  onPaginatedItemsChange(paginatedItems: any[]) {
    this.paginatedItems = paginatedItems;
  }

  updatePagination() {
    this.paginatedItems = this.readers.slice(0, 10); 
  }

  onReaderUpdated(updatedReader: Reader) {
    this.readerService.editReader(updatedReader.id, updatedReader).subscribe(() => {
      this.loadReaders();
      this.selectedReader = this.readers.find(reader => reader.id === updatedReader.id) || this.selectedReader;
    });
  }

  onCancelEdit() {
    this.selectedReader = this.readers.find(reader => reader.id === this.selectedReader.id) || this.selectedReader;
  }

  openDeleteModal(reader: Reader): void {
    this.selectedReader = reader;
    this.showDeleteModal = true;
  }

  closeDeleteModal(): void {
    this.showDeleteModal = false;
  }

  confirmDelete(): void {
    if (this.selectedReader) {
      this.readerService.deleteReader(this.selectedReader.id).subscribe(() => {
        this.loadReaders();
        this.closeDeleteModal();
      });
    }
  }

  deleteReader(id: number): void {
    this.readerService.deleteReader(id).subscribe(() => {
      this.loadReaders();
    })
  }

  updateReaderList(results: Reader[]) {
    this.readers = results;
    this.totalItems = results.length;
    this.paginatedItems = results;
  }
}
