import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SimptomiModalComponent } from './simptomi-modal.component';

describe('SimptomiModalComponent', () => {
  let component: SimptomiModalComponent;
  let fixture: ComponentFixture<SimptomiModalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SimptomiModalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SimptomiModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
