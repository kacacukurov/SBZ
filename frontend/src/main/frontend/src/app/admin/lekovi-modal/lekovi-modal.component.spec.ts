import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LekoviModalComponent } from './lekovi-modal.component';

describe('LekoviModalComponent', () => {
  let component: LekoviModalComponent;
  let fixture: ComponentFixture<LekoviModalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LekoviModalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LekoviModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
