import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConfirmationResetComponent } from './confirmation-reset.component';

describe('ConfirmationResetComponent', () => {
  let component: ConfirmationResetComponent;
  let fixture: ComponentFixture<ConfirmationResetComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ConfirmationResetComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ConfirmationResetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
