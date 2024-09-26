import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Author } from '../../../core/models/author';

@Component({
  selector: 'app-delete-author-confirmation',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './delete-author-confirmation.component.html',
  styleUrl: './delete-author-confirmation.component.scss'
})
export class DeleteAuthorConfirmationComponent {
  @Input() selectedAuthor!: Author;
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
