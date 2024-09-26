import { Component } from '@angular/core';
import { ReaderCard } from '../../core/models/reader-card';
import { ReaderCardService } from '../reader-cards/reader-card.service';
import { AuthService } from '../../core/auth/auth.service';
import { catchError, map, of } from 'rxjs';
import { CommonModule } from '@angular/common';
import { LoadingService } from '../../shared/services/loading.service';

@Component({
  selector: 'app-my-reader-card',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './my-reader-card.component.html',
  styleUrl: './my-reader-card.component.scss'
})
export class MyReaderCardComponent {
  readerCard!: ReaderCard | null;
  errorMessage: string | null = null;
  isLoading = true;
  isReader: boolean = false;
  isLoading$ = this.loadingService.loading$;
  
  constructor(
    private readerCardService: ReaderCardService,
    private authService: AuthService,
    private loadingService: LoadingService
  ) { }

  ngOnInit(): void {
    this.isReader = this.authService.getUserRole() === 'reader';
    this.loadReaderCard();
  }

  private loadReaderCard(): void {
      this.readerCardService.getOwnReaderCard()
        .pipe(
          catchError((error) => {
            this.errorMessage = 'Could not load your reader card. Please try again later.';
            return of(null);
          })
        )
        .subscribe((card) => {
          console.log(card);
          this.readerCard = card;
          this.isLoading = false;
        });
    
  }
}
