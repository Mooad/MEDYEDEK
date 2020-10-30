import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SomethingwrongComponent } from './somethingwrong.component';

describe('SomethingwrongComponent', () => {
  let component: SomethingwrongComponent;
  let fixture: ComponentFixture<SomethingwrongComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SomethingwrongComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SomethingwrongComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
