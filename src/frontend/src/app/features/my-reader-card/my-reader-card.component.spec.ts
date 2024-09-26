import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MyReaderCardComponent } from './my-reader-card.component';

describe('MyReaderCardComponent', () => {
  let component: MyReaderCardComponent;
  let fixture: ComponentFixture<MyReaderCardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MyReaderCardComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(MyReaderCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
