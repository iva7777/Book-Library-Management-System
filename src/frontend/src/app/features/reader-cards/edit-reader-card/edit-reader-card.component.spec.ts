import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditReaderCardComponent } from './edit-reader-card.component';

describe('EditReaderCardComponent', () => {
  let component: EditReaderCardComponent;
  let fixture: ComponentFixture<EditReaderCardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EditReaderCardComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(EditReaderCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
