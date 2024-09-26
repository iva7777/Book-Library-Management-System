export interface Book {
    id: number;
    title: string;
    authorNames: string;
    yearOfProduction: number;
    genre: string;
    isbn: string;
    publisher: string;
    status: string;
    description?: string;
}
