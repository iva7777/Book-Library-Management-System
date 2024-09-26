import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReaderCardDetailsComponent } from './reader-card-details.component';

describe('ReaderCardDetailsComponent', () => {
  let component: ReaderCardDetailsComponent;
  let fixture: ComponentFixture<ReaderCardDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ReaderCardDetailsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ReaderCardDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
