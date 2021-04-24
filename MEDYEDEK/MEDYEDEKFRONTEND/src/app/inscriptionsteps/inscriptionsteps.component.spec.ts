import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InscriptionstepsComponent } from './inscriptionsteps.component';

describe('InscriptionstepsComponent', () => {
  let component: InscriptionstepsComponent;
  let fixture: ComponentFixture<InscriptionstepsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InscriptionstepsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InscriptionstepsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
