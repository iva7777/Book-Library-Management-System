import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { AppUser } from '../../../core/models/app-user';
import { AppUserService } from '../app-user.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-edit-app-user',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './edit-app-user.component.html',
  styleUrl: './edit-app-user.component.scss'
})
export class EditAppUserComponent {
  editUserForm!: FormGroup;
  userId!: number;
  user!: AppUser;

  constructor(
    private fb: FormBuilder, 
    private appUserService: AppUserService, 
    private route: ActivatedRoute, 
    private router: Router
  ) {}

  ngOnInit(): void {
    this.editUserForm = this.fb.group({
      username: ['', Validators.required],
      password: [{ value: '',  }, Validators.required],
      role: ['', Validators.required]
    });

    this.userId = +this.route.snapshot.paramMap.get('id')!;

    this.appUserService.getAllUsers().subscribe(users => {
      this.user = users.find(user => user.id === this.userId)!;

      if (this.user) {
        this.editUserForm.patchValue({
          username: this.user.username,
          password: this.user.password,
          role: this.user.role
        });
      }
    })
  }

  onSubmit(): void {
    console.log(this.editUserForm.value);
    this.appUserService.editUser(this.userId, this.editUserForm.value).subscribe(() => {
      this.router.navigate(['/users']);
    });
  }

  cancel(): void {
    this.router.navigate(['/users']);
  }
}
