import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Author } from '../../../core/models/author';
import { CapitalizePipe } from '../../../shared/pipes/capitalize.pipe';
import { UppercasePipe } from '../../../shared/pipes/uppercase.pipe';

@Component({
  selector: 'app-author-details',
  standalone: true,
  imports: [CapitalizePipe, UppercasePipe],
  templateUrl: './author-details.component.html',
  styleUrl: './author-details.component.scss'
})
export class AuthorDetailsComponent {
  @Input() author!: Author;
  @Input() visible: boolean = false;

  @Output() onClose = new EventEmitter();

  public close(): void {
    this.visible = false;
    this.onClose.emit();
  }
}
