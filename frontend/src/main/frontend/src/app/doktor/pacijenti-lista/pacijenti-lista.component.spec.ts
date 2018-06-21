import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PacijentiListaComponent } from './pacijenti-lista.component';

describe('PacijentiListaComponent', () => {
  let component: PacijentiListaComponent;
  let fixture: ComponentFixture<PacijentiListaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PacijentiListaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PacijentiListaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
