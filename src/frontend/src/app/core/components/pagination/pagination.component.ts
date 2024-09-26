import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-pagination',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './pagination.component.html',
  styleUrl: './pagination.component.scss'
})
export class PaginationComponent {
  @Input() items: any[] = [];
  @Output() paginatedItemsChange = new EventEmitter<any[]>();
  currentPage: number = 1;
  itemsPerPage: number = 10;
  paginatedItems: any[] = [];

  ngOnInit(): void {
    this.paginate();
  }

  totalPages(): number {
    return Math.ceil(this.items.length / this.itemsPerPage);
  }

  totalPagesArray(): number[] {
    return Array(this.totalPages()).fill(0).map((x, i) => i + 1);
  }

  paginate(): void {
    const start = (this.currentPage - 1) * this.itemsPerPage;
    const end = start + this.itemsPerPage;
    this.paginatedItems = this.items.slice(start, end);
    this.paginatedItemsChange.emit(this.paginatedItems);
    console.log(`Current Page: ${this.currentPage}, Paginated Items: `, this.paginatedItems);
  }

  goToPreviousPage(): void {
    if (this.currentPage > 1) {
      this.currentPage--;
      this.paginate();
    }
  }

  goToNextPage(): void {
    if (this.currentPage < this.totalPages()) {
      this.currentPage++;
      this.paginate();
    }
  }

  goToPage(page: number): void {
    if (page >= 1 && page <= this.totalPages()) {
      this.currentPage = page;
      this.paginate();
    }
  }
}
