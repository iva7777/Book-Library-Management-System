import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BooksUnifiedSearchComponent } from './books-unified-search.component';

describe('BooksUnifiedSearchComponent', () => {
  let component: BooksUnifiedSearchComponent;
  let fixture: ComponentFixture<BooksUnifiedSearchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BooksUnifiedSearchComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(BooksUnifiedSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
