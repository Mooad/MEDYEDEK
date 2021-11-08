import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfileGeneralSettingsComponent } from './profile-general-settings.component';

describe('ProfileGeneralSettingsComponent', () => {
  let component: ProfileGeneralSettingsComponent;
  let fixture: ComponentFixture<ProfileGeneralSettingsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProfileGeneralSettingsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProfileGeneralSettingsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
