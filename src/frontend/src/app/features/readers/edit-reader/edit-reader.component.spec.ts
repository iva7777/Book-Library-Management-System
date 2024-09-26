import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditReaderComponent } from './edit-reader.component';

describe('EditReaderComponent', () => {
  let component: EditReaderComponent;
  let fixture: ComponentFixture<EditReaderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EditReaderComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(EditReaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
