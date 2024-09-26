import { TestBed } from '@angular/core/testing';

import { ReaderCardService } from './reader-card.service';

describe('ReaderCardService', () => {
  let service: ReaderCardService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ReaderCardService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
