import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateReaderCardComponent } from './create-reader-card.component';

describe('CreateReaderCardComponent', () => {
  let component: CreateReaderCardComponent;
  let fixture: ComponentFixture<CreateReaderCardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CreateReaderCardComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CreateReaderCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
