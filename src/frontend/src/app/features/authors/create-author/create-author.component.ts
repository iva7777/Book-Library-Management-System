import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Author } from '../../../core/models/author';
import { AuthService } from '../../../core/auth/auth.service';
import { Router } from '@angular/router';
import { AuthorService } from '../author.service';

@Component({
  selector: 'app-create-author',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './create-author.component.html',
  styleUrl: './create-author.component.scss'
})
export class CreateAuthorComponent {
  author: Author = {
    id: 0,
    firstName: '',
    lastName: '',
    bio: '',
    bookTitles: ''
  };

  constructor(private authorService: AuthorService, private router: Router) {}

  onSubmit(): void {
    this.authorService.addAuthor(this.author).subscribe(() => {
      this.router.navigate(['/authors']);
    });
  }
}
