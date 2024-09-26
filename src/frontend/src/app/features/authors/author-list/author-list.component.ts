import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { Author } from '../../../core/models/author';
import { AuthorService } from '../author.service';
import { AuthService } from '../../../core/auth/auth.service';
import { AuthorDetailsComponent } from "../author-details/author-details.component";
import { DeleteAuthorConfirmationComponent } from "../delete-author-confirmation/delete-author-confirmation.component";
import { SearchComponent } from '../search/search.component';
import { PaginationComponent } from "../../../core/components/pagination/pagination.component";
import { LoadingService } from '../../../shared/services/loading.service';
import { CapitalizePipe } from '../../../shared/pipes/capitalize.pipe';
import { UppercasePipe } from '../../../shared/pipes/uppercase.pipe';

@Component({
  selector: 'app-author-list',
  standalone: true,
  imports: [
    CommonModule,
    RouterLink,
    AuthorDetailsComponent,
    DeleteAuthorConfirmationComponent,
    SearchComponent,
    PaginationComponent,
    CapitalizePipe,
    UppercasePipe
],
  templateUrl: './author-list.component.html',
  styleUrl: './author-list.component.scss'
})
export class AuthorListComponent {
  authors: Author[] = [];
  paginatedItems: any[] = [];
  totalItems: number = 0;
  isLibrarian: boolean = false;
  selectedAuthor!: Author;
  isModalOpen: boolean = false;
  showDeleteModal: boolean = false;
  isLoading$ = this.loadingService.loading$;

  constructor(private authorService: AuthorService, private authService: AuthService, private loadingService: LoadingService) {
  }

  ngOnInit(): void {
    this.isLibrarian = this.authService.getUserRole() === 'librarian';
    this.loadAuthors();
  }

  openAuthorDetails(author: Author): void {
    this.selectedAuthor = author;
    this.isModalOpen = true;
  }

  closeAuthorDetails(): void {
    this.isModalOpen = false;
  }

  loadAuthors(): void {
    this.authorService.getAllAuthors().subscribe((authors) => {
      this.authors = authors;
      console.log(this.authors);
      this.totalItems = authors.length;
      this.updatePagination();
    });
  }

  onPaginatedItemsChange(paginatedItems: any[]) {
    this.paginatedItems = paginatedItems;
  }

  updatePagination() {
    this.paginatedItems = this.authors.slice(0, 10); 
  }

  onAuthorUpdated(updatedAuthor: Author) {
    this.authorService.editAuthor(updatedAuthor.id, updatedAuthor).subscribe(() => {
      this.loadAuthors();
      this.selectedAuthor = this.authors.find(author => author.id === updatedAuthor.id) || this.selectedAuthor;
    });
  }

  onCancelEdit() {
    this.selectedAuthor = this.authors.find(author => author.id === this.selectedAuthor.id) || this.selectedAuthor;
  }

  openDeleteModal(author: Author): void {
    this.selectedAuthor = author;
    this.showDeleteModal = true;
  }

  closeDeleteModal(): void {
    this.showDeleteModal = false;
  }

  confirmDelete(): void {
    if (this.selectedAuthor) {
      this.authorService.deleteAuthor(this.selectedAuthor.id).subscribe(() => {
        this.loadAuthors();
        this.closeDeleteModal();
      });
    }
  }

  deleteAuthor(id: number): void {
    this.authorService.deleteAuthor(id).subscribe(() => {
      this.loadAuthors();
    })
  }

  updateAuthorList(results: Author[]) {
    this.authors = results;
    this.totalItems = results.length;
    this.paginatedItems = results
  }
}
