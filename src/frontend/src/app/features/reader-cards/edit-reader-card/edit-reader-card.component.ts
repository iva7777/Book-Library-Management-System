import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { ReaderCard } from '../../../core/models/reader-card';
import { ReaderCardService } from '../reader-card.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-edit-reader-card',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule],
  templateUrl: './edit-reader-card.component.html',
  styleUrl: './edit-reader-card.component.scss'
})
export class EditReaderCardComponent {
  editReaderCardForm!: FormGroup;
  readerCardId!: number;
  readerCard!: ReaderCard;

  constructor(
    private fb: FormBuilder,
    private readerCardService: ReaderCardService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.editReaderCardForm = this.fb.group({
      rentDate: [{ value: '' }, [Validators.required]],
      returnDate: [{ value: ''}, [Validators.required]],
      readerNames: [{ value: ''}, [Validators.required]]
    });

    this.readerCardId = +this.route.snapshot.paramMap.get('id')!;

    this.readerCardService.getAllReaderCards().subscribe(cards => {
      this.readerCard = cards.find(c => c.id === this.readerCardId)!;
      if (this.readerCard) {
        this.editReaderCardForm.patchValue({
          rentDate: this.readerCard.rentDate,
          returnDate: this.readerCard.returnDate,
          readerNames: this.readerCard.readerNames
        });
      }
    });
  }

  onSubmit(): void {
    console.log(this.editReaderCardForm.value);
    this.readerCardService.editReaderCard(this.readerCardId, this.editReaderCardForm.value).subscribe(() => {
      this.router.navigate(['/reader-cards']);
    });
  }

  cancel(): void {
    this.router.navigate(['/reader-cards']);
  }
}
