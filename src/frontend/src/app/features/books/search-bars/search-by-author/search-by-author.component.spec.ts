import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchByAuthorComponent } from './search-by-author.component';

describe('SearchByAuthorComponent', () => {
  let component: SearchByAuthorComponent;
  let fixture: ComponentFixture<SearchByAuthorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SearchByAuthorComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(SearchByAuthorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
