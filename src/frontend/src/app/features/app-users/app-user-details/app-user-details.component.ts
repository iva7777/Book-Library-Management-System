import { Component, EventEmitter, Input, Output } from '@angular/core';
import { AppUser } from '../../../core/models/app-user';
import { UppercasePipe } from '../../../shared/pipes/uppercase.pipe';

@Component({
  selector: 'app-app-user-details',
  standalone: true,
  imports: [UppercasePipe],
  templateUrl: './app-user-details.component.html',
  styleUrl: './app-user-details.component.scss'
})
export class AppUserDetailsComponent {
  @Input() user!: AppUser;
  @Input() visible: boolean = false;

  @Output() onClose = new EventEmitter();

  public close(): void {
    this.visible = false;
    this.onClose.emit();
  }
}
