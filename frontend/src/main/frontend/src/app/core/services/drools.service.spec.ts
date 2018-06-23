import { TestBed, inject } from '@angular/core/testing';

import { DroolsService } from './drools.service';

describe('DroolsService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [DroolsService]
    });
  });

  it('should be created', inject([DroolsService], (service: DroolsService) => {
    expect(service).toBeTruthy();
  }));
});
