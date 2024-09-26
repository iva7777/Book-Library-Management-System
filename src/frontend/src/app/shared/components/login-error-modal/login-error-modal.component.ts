import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-login-error-modal',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './login-error-modal.component.html',
  styleUrl: './login-error-modal.component.scss'
})
export class LoginErrorModalComponent {
  @Input() visible: boolean = false;
  @Output() close = new EventEmitter<void>();

  onClose(): void {
    this.close.emit();
  }
}
