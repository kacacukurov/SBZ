import { TestBed, async, inject } from '@angular/core/testing';

import { RecenzentGuard } from './recenzent.guard';

describe('RecenzentGuard', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [RecenzentGuard]
    });
  });

  it('should ...', inject([RecenzentGuard], (guard: RecenzentGuard) => {
    expect(guard).toBeTruthy();
  }));
});
