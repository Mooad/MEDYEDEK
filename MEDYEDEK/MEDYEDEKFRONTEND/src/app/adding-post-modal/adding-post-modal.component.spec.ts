import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddingPostModalComponent } from './adding-post-modal.component';

describe('AddingPostModalComponent', () => {
  let component: AddingPostModalComponent;
  let fixture: ComponentFixture<AddingPostModalComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AddingPostModalComponent]
    });
    fixture = TestBed.createComponent(AddingPostModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });
  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
