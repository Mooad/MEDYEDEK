import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConfirmResetPassDialogComponent } from './confirm-reset-pass-dialog.component';

describe('ConfirmResetPassDialogComponent', () => {
  let component: ConfirmResetPassDialogComponent;
  let fixture: ComponentFixture<ConfirmResetPassDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ConfirmResetPassDialogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ConfirmResetPassDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
