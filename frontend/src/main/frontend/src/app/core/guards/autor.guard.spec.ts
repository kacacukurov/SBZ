import { TestBed, async, inject } from '@angular/core/testing';

import { AutorGuard } from './autor.guard';

describe('AutorGuard', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [AutorGuard]
    });
  });

  it('should ...', inject([AutorGuard], (guard: AutorGuard) => {
    expect(guard).toBeTruthy();
  }));
});
