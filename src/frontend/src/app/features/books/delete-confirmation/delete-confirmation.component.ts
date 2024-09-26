import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Book } from '../../../core/models/book';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-delete-confirmation',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './delete-confirmation.component.html',
  styleUrl: './delete-confirmation.component.scss'
})
export class DeleteConfirmationComponent {
  @Input() selectedBook!: Book;
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
