import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DijagnozaModulComponent } from './dijagnoza-modul.component';

describe('DijagnozaModulComponent', () => {
  let component: DijagnozaModulComponent;
  let fixture: ComponentFixture<DijagnozaModulComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DijagnozaModulComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DijagnozaModulComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
