import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeleteAuthorConfirmationComponent } from './delete-author-confirmation.component';

describe('DeleteAuthorConfirmationComponent', () => {
  let component: DeleteAuthorConfirmationComponent;
  let fixture: ComponentFixture<DeleteAuthorConfirmationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DeleteAuthorConfirmationComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DeleteAuthorConfirmationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
