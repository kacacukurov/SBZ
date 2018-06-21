import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BolestiModalComponent } from './bolesti-modal.component';

describe('BolestiModalComponent', () => {
  let component: BolestiModalComponent;
  let fixture: ComponentFixture<BolestiModalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BolestiModalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BolestiModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
