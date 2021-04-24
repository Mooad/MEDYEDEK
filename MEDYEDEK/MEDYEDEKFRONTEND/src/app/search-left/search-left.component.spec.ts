import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchLeftComponent } from './search-left.component';

describe('SearchLeftComponent', () => {
  let component: SearchLeftComponent;
  let fixture: ComponentFixture<SearchLeftComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchLeftComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchLeftComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
