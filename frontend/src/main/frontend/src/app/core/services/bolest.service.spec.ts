import { TestBed, inject } from '@angular/core/testing';

import { BolestService } from './bolest.service';

describe('BolestService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [BolestService]
    });
  });

  it('should be created', inject([BolestService], (service: BolestService) => {
    expect(service).toBeTruthy();
  }));
});
