import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SimptomiListaComponent } from './simptomi-lista.component';

describe('SimptomiListaComponent', () => {
  let component: SimptomiListaComponent;
  let fixture: ComponentFixture<SimptomiListaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SimptomiListaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SimptomiListaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
