import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchSelectorComponent } from './search-selector.component';

describe('SearchSelectorComponent', () => {
  let component: SearchSelectorComponent;
  let fixture: ComponentFixture<SearchSelectorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SearchSelectorComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(SearchSelectorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
