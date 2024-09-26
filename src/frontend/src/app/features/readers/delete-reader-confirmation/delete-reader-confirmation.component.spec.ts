import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeleteReaderConfirmationComponent } from './delete-reader-confirmation.component';

describe('DeleteReaderConfirmationComponent', () => {
  let component: DeleteReaderConfirmationComponent;
  let fixture: ComponentFixture<DeleteReaderConfirmationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DeleteReaderConfirmationComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DeleteReaderConfirmationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
