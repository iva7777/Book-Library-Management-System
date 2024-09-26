import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Reader } from '../../../core/models/reader';
import { ReaderService } from '../reader.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-edit-reader',
  standalone: true,
  imports: [ReactiveFormsModule, FormsModule],
  templateUrl: './edit-reader.component.html',
  styleUrl: './edit-reader.component.scss'
})
export class EditReaderComponent {
  editReaderForm!: FormGroup;
  readerId!: number;
  reader!: Reader;

  constructor(
    private fb: FormBuilder,
    private readerService: ReaderService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.editReaderForm = this.fb.group({
      firstName: [{ value: '' }, [Validators.required]],
      lastName: [{ value: ''}, [Validators.required]],
      phone: [{ value: '' }, [Validators.required]],
      address: [{ value: ''}, [Validators.required]],
      email: [{ value: ''}, [Validators.required]],
      userId: [{value: 0}, [Validators.required]]
    });

    this.readerId = +this.route.snapshot.paramMap.get('id')!;

    this.readerService.getAllReaders().subscribe(readers => {
      this.reader = readers.find(reader => reader.id === this.readerId)!;
      if (this.reader) {
        this.editReaderForm.patchValue({
          firstName: this.reader.firstName,
          lastName: this.reader.lastName,
          phone: this.reader.phone,
          address: this.reader.address,
          email: this.reader.email,
          userId: this.reader.userId
        });
      }
    });
  }

  onSubmit(): void {
    console.log(this.editReaderForm.value);
    this.readerService.editReader(this.readerId, this.editReaderForm.value).subscribe(() => {
      this.router.navigate(['/readers']);
    });
  }

  cancel(): void {
    this.router.navigate(['/readers']);
  }
}
