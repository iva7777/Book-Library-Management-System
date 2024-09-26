import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchByGenreComponent } from './search-by-genre.component';

describe('SearchByGenreComponent', () => {
  let component: SearchByGenreComponent;
  let fixture: ComponentFixture<SearchByGenreComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SearchByGenreComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(SearchByGenreComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
