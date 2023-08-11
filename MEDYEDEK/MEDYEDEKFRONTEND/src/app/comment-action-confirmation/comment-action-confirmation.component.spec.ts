import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CommentActionConfirmationComponent } from './comment-action-confirmation.component';

describe('CommentActionConfirmationComponent', () => {
  let component: CommentActionConfirmationComponent;
  let fixture: ComponentFixture<CommentActionConfirmationComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CommentActionConfirmationComponent]
    });
    fixture = TestBed.createComponent(CommentActionConfirmationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
