import { TestBed, inject } from '@angular/core/testing';

import { PacijentService } from './pacijent.service';

describe('PacijentService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [PacijentService]
    });
  });

  it('should be created', inject([PacijentService], (service: PacijentService) => {
    expect(service).toBeTruthy();
  }));
});
