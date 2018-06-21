import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { KorisnikListaComponent } from './korisnik-lista.component';

describe('KorisnikListaComponent', () => {
  let component: KorisnikListaComponent;
  let fixture: ComponentFixture<KorisnikListaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ KorisnikListaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(KorisnikListaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
