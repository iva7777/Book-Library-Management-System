import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginErrorModalComponent } from './login-error-modal.component';

describe('LoginErrorModalComponent', () => {
  let component: LoginErrorModalComponent;
  let fixture: ComponentFixture<LoginErrorModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LoginErrorModalComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(LoginErrorModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
