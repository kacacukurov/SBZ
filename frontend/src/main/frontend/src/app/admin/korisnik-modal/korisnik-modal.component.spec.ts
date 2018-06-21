import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { KorisnikModalComponent } from './korisnik-modal.component';

describe('KorisnikModalComponent', () => {
  let component: KorisnikModalComponent;
  let fixture: ComponentFixture<KorisnikModalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ KorisnikModalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(KorisnikModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
