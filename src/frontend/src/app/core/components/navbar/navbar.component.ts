import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { AuthService } from '../../auth/auth.service';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.scss'
})
export class NavbarComponent {
  constructor(private authService: AuthService, private router: Router) {}

  get isLoggedIn(): boolean {
    return this.authService.isLoggedIn();
  }

  get isLibrarian(): boolean {
    return this.authService.getUserRole() === 'librarian';
  }

  get isReader(): boolean {
    return this.authService.getUserRole() === 'reader';
  }

  logout(): void {
    this.authService.logout();
    this.router.navigate(['/authenticate']);
  }
}
