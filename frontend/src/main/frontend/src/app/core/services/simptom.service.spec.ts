import { TestBed, inject } from '@angular/core/testing';

import { SimptomService } from './simptom.service';

describe('SimptomService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [SimptomService]
    });
  });

  it('should be created', inject([SimptomService], (service: SimptomService) => {
    expect(service).toBeTruthy();
  }));
});
