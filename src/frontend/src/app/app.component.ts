import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { NavbarComponent } from './core/components/navbar/navbar.component';
import { HomeComponent } from './home/home.component';
import { BookListComponent } from './features/books/book-list/book-list.component';
import { CommonModule } from '@angular/common';
import { LoadingComponent } from "./shared/components/loading/loading.component";
import { LoadingService } from './shared/services/loading.service';
import { FooterComponent } from "./core/components/footer/footer.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, NavbarComponent, HomeComponent, BookListComponent, CommonModule, LoadingComponent, FooterComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'library-management-fe';
  constructor(private loadingService: LoadingService) {}
  isLoading$ = this.loadingService.loading$;
}
