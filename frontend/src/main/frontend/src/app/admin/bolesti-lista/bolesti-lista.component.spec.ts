import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BolestiListaComponent } from './bolesti-lista.component';

describe('BolestiListaComponent', () => {
  let component: BolestiListaComponent;
  let fixture: ComponentFixture<BolestiListaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BolestiListaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BolestiListaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
