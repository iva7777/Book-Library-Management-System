import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReadersUnifiedSearchComponent } from './readers-unified-search.component';

describe('ReadersUnifiedSearchComponent', () => {
  let component: ReadersUnifiedSearchComponent;
  let fixture: ComponentFixture<ReadersUnifiedSearchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ReadersUnifiedSearchComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ReadersUnifiedSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
