import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Book } from '../../../core/models/book';
import { CapitalizePipe } from '../../../shared/pipes/capitalize.pipe';
import { UppercasePipe } from '../../../shared/pipes/uppercase.pipe';

@Component({
  selector: 'app-book-details',
  standalone: true,
  imports: [CapitalizePipe, UppercasePipe],
  templateUrl: './book-details.component.html',
  styleUrl: './book-details.component.scss'
})
export class BookDetailsComponent {
  @Input() book!: Book;
  @Input() visible: boolean = false;

  @Output() onClose = new EventEmitter();

  public close(): void {
    this.visible = false;
    this.onClose.emit();
  }

}
