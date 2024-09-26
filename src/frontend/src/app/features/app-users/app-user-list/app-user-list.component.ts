import { CommonModule, UpperCasePipe } from '@angular/common';
import { Component, ViewChild } from '@angular/core';
import { AppUser } from '../../../core/models/app-user';
import { AppUserService } from '../app-user.service';
import { AuthService } from '../../../core/auth/auth.service';
import { RouterLink } from '@angular/router';
import { AppUserDetailsComponent } from "../app-user-details/app-user-details.component";
import { DeleteUserConfirmationComponent } from '../delete-user-confirmation/delete-user-confirmation.component';
import { SearchByUsernameComponent } from '../search/search-by-username/search-by-username.component';
import { PaginationComponent } from "../../../core/components/pagination/pagination.component";
import { LoadingService } from '../../../shared/services/loading.service';
import { CapitalizePipe } from '../../../shared/pipes/capitalize.pipe';

@Component({
  selector: 'app-app-user-list',
  standalone: true,
  imports: [
    CommonModule,
    RouterLink,
    DeleteUserConfirmationComponent,
    AppUserDetailsComponent,
    SearchByUsernameComponent,
    PaginationComponent,
    UpperCasePipe
],
  templateUrl: './app-user-list.component.html',
  styleUrl: './app-user-list.component.scss'
})
export class AppUserListComponent {
  users: AppUser[] = [];
  paginatedItems: any[] = [];
  totalItems: number = 0;
  isLibrarian: boolean = false;
  selectedUser!: AppUser;
  isModalOpen: boolean = false;
  showDeleteModal: boolean = false;
  isLoading$ = this.loadingService.loading$;

  constructor(private appUserService: AppUserService, private authService: AuthService, private loadingService: LoadingService) { 
  }

  ngOnInit(): void {
    this.isLibrarian = this.authService.getUserRole() === 'librarian';
    this.loadUsers();
  }

  openUserDetails(user: AppUser): void {
    this.selectedUser = user;
    this.isModalOpen = true;
  }

  closeUserDetails(): void {
    this.isModalOpen = false;
  }

  loadUsers() {
    this.appUserService.getAllUsers().subscribe((users) => {
      this.users = users;
      console.log(this.users);
      this.totalItems = users.length;
      this.updatePagination();
    });
  }

  onPaginatedItemsChange(paginatedItems: any[]) {
    this.paginatedItems = paginatedItems;
  }

  updatePagination() {
    this.paginatedItems = this.users.slice(0, 10); 
  }

  onUserUpdated(updatedUser: AppUser) {
    this.appUserService.editUser(updatedUser.id, updatedUser).subscribe(() => {
      this.loadUsers();
      this.selectedUser = this.users.find(user => user.id === updatedUser.id) || this.selectedUser;
    });
  }

  onCancelEdit() {
    this.selectedUser = this.users.find(user => user.id === this.selectedUser.id) || this.selectedUser;
  }

  openDeleteModal(user: AppUser): void {
    this.selectedUser = user;
    this.showDeleteModal = true;
  }

  closeDeleteModal(): void {
    this.showDeleteModal = false;
  }

  confirmDelete(): void {
    if (this.selectedUser) {
      this.appUserService.deleteUser(this.selectedUser.id).subscribe(() => {
        this.loadUsers();
        this.closeDeleteModal();
      });
    }
  }

  deleteUser(id: number): void {
    this.appUserService.deleteUser(id).subscribe(() => {
      this.loadUsers();
    })
  }

  updateUserList(results: AppUser[]) {
    this.users = results;
    this.totalItems = results.length;
    this.paginatedItems = results;
  }
}
