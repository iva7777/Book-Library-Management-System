import { Component } from '@angular/core';
import { Reader } from '../../../core/models/reader';
import { ReaderService } from '../reader.service';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-create-reader',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './create-reader.component.html',
  styleUrl: './create-reader.component.scss'
})
export class CreateReaderComponent {
  reader: Reader = {
    id: 0,
    firstName: '',
    lastName: '',
    phone: '',
    email: '',
    address: '',
    userId: 0
  };

  constructor(private readerService: ReaderService, private router: Router) {
  }

  onSubmit(): void {
    this.readerService.addReader(this.reader).subscribe(() => {
      this.router.navigate(['/readers']);
    });
  }
}
