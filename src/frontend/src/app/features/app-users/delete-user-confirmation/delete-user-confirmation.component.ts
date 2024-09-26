import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { AppUser } from '../../../core/models/app-user';

@Component({
  selector: 'app-delete-user-confirmation',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './delete-user-confirmation.component.html',
  styleUrl: './delete-user-confirmation.component.scss'
})
export class DeleteUserConfirmationComponent {
  @Input() selectedUser!: AppUser;
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
