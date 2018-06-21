import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PacijentModalComponent } from './pacijent-modal.component';

describe('PacijentModalComponent', () => {
  let component: PacijentModalComponent;
  let fixture: ComponentFixture<PacijentModalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PacijentModalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PacijentModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
