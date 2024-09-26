import { Component, EventEmitter, Input, Output } from '@angular/core';
import { ReaderCard } from '../../../core/models/reader-card';
import { CommonModule } from '@angular/common';
import { CapitalizePipe } from '../../../shared/pipes/capitalize.pipe';

@Component({
  selector: 'app-reader-card-details',
  standalone: true,
  imports: [CommonModule, CapitalizePipe],
  templateUrl: './reader-card-details.component.html',
  styleUrl: './reader-card-details.component.scss'
})
export class ReaderCardDetailsComponent {
  @Input() readerCard!: ReaderCard;
  @Input() visible: boolean = false;

  @Output() onClose = new EventEmitter();

  public close(): void {
    this.visible = false;
    this.onClose.emit();
  }
}
