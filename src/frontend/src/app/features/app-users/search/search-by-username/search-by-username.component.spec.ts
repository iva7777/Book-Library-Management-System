import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchByUsernameComponent } from './search-by-username.component';

describe('SearchByUsernameComponent', () => {
  let component: SearchByUsernameComponent;
  let fixture: ComponentFixture<SearchByUsernameComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SearchByUsernameComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(SearchByUsernameComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
