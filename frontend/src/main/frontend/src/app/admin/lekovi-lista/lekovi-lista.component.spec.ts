import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LekoviListaComponent } from './lekovi-lista.component';

describe('LekoviListaComponent', () => {
  let component: LekoviListaComponent;
  let fixture: ComponentFixture<LekoviListaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LekoviListaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LekoviListaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
