import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeleteReaderCardConfirmationComponent } from './delete-reader-card-confirmation.component';

describe('DeleteReaderCardConfirmationComponent', () => {
  let component: DeleteReaderCardConfirmationComponent;
  let fixture: ComponentFixture<DeleteReaderCardConfirmationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DeleteReaderCardConfirmationComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DeleteReaderCardConfirmationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
