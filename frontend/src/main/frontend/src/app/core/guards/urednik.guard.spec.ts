import { TestBed, async, inject } from '@angular/core/testing';

import { UrednikGuard } from './urednik.guard';

describe('UrednikGuard', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [UrednikGuard]
    });
  });

  it('should ...', inject([UrednikGuard], (guard: UrednikGuard) => {
    expect(guard).toBeTruthy();
  }));
});
