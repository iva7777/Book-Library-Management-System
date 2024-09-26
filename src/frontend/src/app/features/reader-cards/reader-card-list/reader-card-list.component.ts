import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { ReaderCard } from '../../../core/models/reader-card';
import { ReaderCardService } from '../reader-card.service';
import { AuthService } from '../../../core/auth/auth.service';
import { ReaderCardDetailsComponent } from '../reader-card-details/reader-card-details.component';
import { DeleteReaderCardConfirmationComponent } from '../delete-reader-card-confirmation/delete-reader-card-confirmation.component';
import { PaginationComponent } from "../../../core/components/pagination/pagination.component";
import { LoadingService } from '../../../shared/services/loading.service';
import { CapitalizePipe } from '../../../shared/pipes/capitalize.pipe';
import { UppercasePipe } from '../../../shared/pipes/uppercase.pipe';

@Component({
  selector: 'app-reader-card-list',
  standalone: true,
  imports: [
    CommonModule,
    RouterLink,
    ReaderCardDetailsComponent,
    DeleteReaderCardConfirmationComponent,
    PaginationComponent,
    CapitalizePipe,
    UppercasePipe
],
  templateUrl: './reader-card-list.component.html',
  styleUrl: './reader-card-list.component.scss'
})
export class ReaderCardListComponent {
  readerCards: ReaderCard[] = [];
  paginatedItems: any[] = [];
  totalItems: number = 0;
  isLibrarian: boolean = false;
  selectedReaderCard!: ReaderCard;
  isModalOpen: boolean = false;
  showDeleteModal: boolean = false;
  isLoading$ = this.loadingService.loading$;
  
  constructor(private readerCardService: ReaderCardService, private authService: AuthService, private loadingService: LoadingService) {}

  ngOnInit(): void {
    this.isLibrarian = this.authService.getUserRole() === 'librarian';
    this.loadReaderCards();
  }

  openReaderCardDetails(readerCard: ReaderCard): void {
    this.selectedReaderCard = readerCard;
    this.isModalOpen = true;
  }

  closeReaderCardDetails(): void {
    this.isModalOpen = false;
  }

  loadReaderCards() {
    this.readerCardService.getAllReaderCards().subscribe((cards) => {
      this.readerCards = cards;
      console.log(this.readerCards);
      this.totalItems = cards.length;
      this.updatePagination();
    });
  }

  onPaginatedItemsChange(paginatedItems: any[]) {
    this.paginatedItems = paginatedItems;
  }

  updatePagination() {
    this.paginatedItems = this.readerCards.slice(0, 10); 
  }

  onReaderCardUpdated(updatedReaderCard: ReaderCard) {
    this.readerCardService.editReaderCard(updatedReaderCard.id, updatedReaderCard).subscribe(() => {
      this.loadReaderCards();
      this.selectedReaderCard = this.readerCards.find(card => card.id === updatedReaderCard.id) || this.selectedReaderCard;
    });
  }

  onCancelEdit() {
    this.selectedReaderCard = this.readerCards.find(card => card.id === this.selectedReaderCard.id) || this.selectedReaderCard;
  }

  openDeleteModal(readerCard: ReaderCard): void {
    this.selectedReaderCard = readerCard;
    this.showDeleteModal = true;
  }

  closeDeleteModal(): void {
    this.showDeleteModal = false;
  }

  confirmDelete(): void {
    if (this.selectedReaderCard) {
      this.readerCardService.deleteReaderCard(this.selectedReaderCard.id).subscribe(() => {
        this.loadReaderCards();
        this.closeDeleteModal();
      });
    }
  }

  deleteReaderCard(id: number): void {
    this.readerCardService.deleteReaderCard(id).subscribe(() => {
      this.loadReaderCards();
    })
  }

  updateReaderCardsList(results: ReaderCard[]) {
    this.readerCards = results;
    this.totalItems = results.length;
    this.paginatedItems = results;
  }
}
