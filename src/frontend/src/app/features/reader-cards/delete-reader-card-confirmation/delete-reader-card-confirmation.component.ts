import { Component, EventEmitter, Input, Output } from '@angular/core';
import { ReaderCard } from '../../../core/models/reader-card';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-delete-reader-card-confirmation',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './delete-reader-card-confirmation.component.html',
  styleUrl: './delete-reader-card-confirmation.component.scss'
})
export class DeleteReaderCardConfirmationComponent {
  @Input() selectedReaderCard!: ReaderCard;
  @Input() showDeleteModal = true;
  @Output() confirmDelete = new EventEmitter<void>();
  @Output() cancelDelete = new EventEmitter<void>();

  public onConfirm(): void {
    this.confirmDelete.emit();
    this.closeModal();
  }

  public onCancel(): void {
    this.cancelDelete.emit();
    this.closeModal();
  }

  private closeModal(): void {
    this.showDeleteModal = false;
  }
}
