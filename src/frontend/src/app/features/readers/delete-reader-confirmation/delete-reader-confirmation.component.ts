import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Reader } from '../../../core/models/reader';

@Component({
  selector: 'app-delete-reader-confirmation',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './delete-reader-confirmation.component.html',
  styleUrl: './delete-reader-confirmation.component.scss'
})
export class DeleteReaderConfirmationComponent {
  @Input() selectedReader!: Reader;
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
