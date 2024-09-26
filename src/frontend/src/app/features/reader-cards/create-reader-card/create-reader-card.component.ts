import { Component } from '@angular/core';
import { ReaderCard } from '../../../core/models/reader-card';
import { ReaderCardService } from '../reader-card.service';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-create-reader-card',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './create-reader-card.component.html',
  styleUrl: './create-reader-card.component.scss'
})
export class CreateReaderCardComponent {
  readerCard: ReaderCard = {
    id: 0,
    rentDate: new Date(),
    returnDate: new Date(),
    readerId: 0,
    readerNames: ''
  };

  constructor(private readerCardService: ReaderCardService, private router: Router) {
  }

  onSubmit(): void {
    this.readerCardService.addReaderCard(this.readerCard).subscribe(() => {
      this.router.navigate(['/reader-cards']);
    });
  }

}
