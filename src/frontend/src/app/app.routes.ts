import { Routes } from '@angular/router';
import { LoginComponent } from './core/auth/login/login.component';
import { RegisterComponent } from './core/auth/register/register.component';
import { HomeComponent } from './home/home.component';
import { guestGuard } from './core/auth/guest.guard';
import { BookListComponent } from './features/books/book-list/book-list.component';
import { CreateBookComponent } from './features/books/create-book/create-book.component';
import { roleGuard } from './core/auth/role.guard';
import { authGuard } from './core/auth/auth.guard';
import { EditBookComponent } from './features/books/edit-book/edit-book.component';
import { DeleteConfirmationComponent } from './features/books/delete-confirmation/delete-confirmation.component';
import { AuthorListComponent } from './features/authors/author-list/author-list.component';
import { CreateAuthorComponent } from './features/authors/create-author/create-author.component';
import { EditAuthorComponent } from './features/authors/edit-author/edit-author.component';
import { DeleteAuthorConfirmationComponent } from './features/authors/delete-author-confirmation/delete-author-confirmation.component';
import { AppUserListComponent } from './features/app-users/app-user-list/app-user-list.component';
import { EditAppUserComponent } from './features/app-users/edit-app-user/edit-app-user.component';
import { DeleteUserConfirmationComponent } from './features/app-users/delete-user-confirmation/delete-user-confirmation.component';
import { ReaderCardListComponent } from './features/reader-cards/reader-card-list/reader-card-list.component';
import { CreateReaderCardComponent } from './features/reader-cards/create-reader-card/create-reader-card.component';
import { EditReaderCardComponent } from './features/reader-cards/edit-reader-card/edit-reader-card.component';
import { DeleteReaderCardConfirmationComponent } from './features/reader-cards/delete-reader-card-confirmation/delete-reader-card-confirmation.component';
import { ReaderListComponent } from './features/readers/reader-list/reader-list.component';
import { EditReaderComponent } from './features/readers/edit-reader/edit-reader.component';
import { CreateReaderComponent } from './features/readers/create-reader/create-reader.component';
import { DeleteReaderConfirmationComponent } from './features/readers/delete-reader-confirmation/delete-reader-confirmation.component';
import { MyReaderCardComponent } from './features/my-reader-card/my-reader-card.component';
import { NotFoundComponent } from './shared/error-pages/not-found/not-found.component';
import { ForbiddenComponent } from './shared/error-pages/forbidden/forbidden.component';
import { ServerErrorComponent } from './shared/error-pages/server-error/server-error.component';

export const routes: Routes = [
    {
        path: 'users', component: AppUserListComponent, canActivate: [authGuard], children: [
            { path: 'delete/:id', component: DeleteUserConfirmationComponent, canActivate: [authGuard] },
        ]
    },
    { path: 'users/edit/:id', component: EditAppUserComponent, canActivate: [authGuard] },
    {
        path: 'auth', children: [
            { path: 'login', component: LoginComponent, canActivate: [guestGuard] },
            { path: 'register', component: RegisterComponent, canActivate: [guestGuard] }
        ]
    },
    { path: 'home', component: HomeComponent },
    {
        path: 'books', component: BookListComponent, children: [
            { path: 'delete/:id', component: DeleteConfirmationComponent, canActivate: [authGuard] }
        ]
    },
    { path: 'books/create', component: CreateBookComponent, canActivate: [authGuard] },
    { path: 'books/edit/:id', component: EditBookComponent, canActivate: [authGuard] },
    {
        path: 'authors', component: AuthorListComponent, children: [
            { path: 'delete/:id', component: DeleteAuthorConfirmationComponent, canActivate: [authGuard] }
        ]
    },

    { path: 'authors/create', component: CreateAuthorComponent, canActivate: [authGuard] },
    { path: 'authors/edit/:id', component: EditAuthorComponent, canActivate: [authGuard] },

    {
        path: 'reader-cards', component: ReaderCardListComponent, canActivate: [authGuard], children: [
            { path: 'delete/:id', component: DeleteReaderCardConfirmationComponent, canActivate: [authGuard] },
        ]
    },
    { path: 'reader-cards/create', component: CreateReaderCardComponent, canActivate: [authGuard] },
    { path: 'reader-cards/edit/:id', component: EditReaderCardComponent, canActivate: [authGuard] },

    { path: 'my-reader-card', component: MyReaderCardComponent, canActivate: [authGuard] },

    {
        path: 'readers', component: ReaderListComponent, canActivate: [authGuard], children: [
            { path: 'delete/:id', component: DeleteReaderConfirmationComponent, canActivate: [authGuard] }
        ]
    },
    { path: 'readers/create', component: CreateReaderComponent, canActivate: [authGuard] },
    { path: 'readers/edit/:id', component: EditReaderComponent, canActivate: [authGuard] },
    { path: 'not-found', component: NotFoundComponent },
    { path: 'forbidden', component: ForbiddenComponent },
    { path: 'server-error', component: ServerErrorComponent },
    { path: '**', redirectTo: 'home' }, //wild-card is last ALWAYS
];
