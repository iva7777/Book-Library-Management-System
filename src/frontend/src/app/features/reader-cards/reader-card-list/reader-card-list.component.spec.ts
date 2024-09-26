import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReaderCardListComponent } from './reader-card-list.component';

describe('ReaderCardListComponent', () => {
  let component: ReaderCardListComponent;
  let fixture: ComponentFixture<ReaderCardListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ReaderCardListComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ReaderCardListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
