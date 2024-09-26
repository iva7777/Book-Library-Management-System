import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Author } from '../../../core/models/author';
import { AuthorService } from '../author.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-edit-author',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule],
  templateUrl: './edit-author.component.html',
  styleUrl: './edit-author.component.scss'
})
export class EditAuthorComponent {
  editAuthorForm!: FormGroup;
  authorId!: number;
  author!: Author;

  constructor(
    private fb: FormBuilder,
    private authorService: AuthorService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.editAuthorForm = this.fb.group({
      firstName: [{ value: '' }, [Validators.required]],
      lastName: [{ value: ''}, [Validators.required]],
      bio: [{ value: ''}],
    });

    this.authorId = +this.route.snapshot.paramMap.get('id')!;

    this.authorService.getAllAuthors().subscribe(authors => {
      this.author = authors.find(a => a.id === this.authorId)!;
      if (this.author) {
        this.editAuthorForm.patchValue({
          firstName: this.author.firstName,
          lastName: this.author.lastName,
          bio: this.author.bio
        });
      }
    });
  }

  onSubmit(): void {
    console.log(this.editAuthorForm.value);
    this.authorService.editAuthor(this.authorId, this.editAuthorForm.value).subscribe(() => {
      this.router.navigate(['/authors']);
    });
  }

  cancel(): void {
    this.router.navigate(['/authors']);
  }
}
