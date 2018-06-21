import { TestBed, async, inject } from '@angular/core/testing';

import { DoktorGuard } from './doktor.guard';

describe('DoktorGuard', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [DoktorGuard]
    });
  });

  it('should ...', inject([DoktorGuard], (guard: DoktorGuard) => {
    expect(guard).toBeTruthy();
  }));
});
