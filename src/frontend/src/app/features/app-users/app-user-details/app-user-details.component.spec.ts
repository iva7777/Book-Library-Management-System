import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AppUserDetailsComponent } from './app-user-details.component';

describe('AppUserDetailsComponent', () => {
  let component: AppUserDetailsComponent;
  let fixture: ComponentFixture<AppUserDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AppUserDetailsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AppUserDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
