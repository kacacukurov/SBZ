import { TestBed, inject } from '@angular/core/testing';

import { LekService } from './lek.service';

describe('LekService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [LekService]
    });
  });

  it('should be created', inject([LekService], (service: LekService) => {
    expect(service).toBeTruthy();
  }));
});
