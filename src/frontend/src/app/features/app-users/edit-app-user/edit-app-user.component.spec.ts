import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditAppUserComponent } from './edit-app-user.component';

describe('EditAppUserComponent', () => {
  let component: EditAppUserComponent;
  let fixture: ComponentFixture<EditAppUserComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EditAppUserComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(EditAppUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
