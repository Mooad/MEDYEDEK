import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfileSecuritySettingsComponent } from './profile-security-settings.component';

describe('ProfileSecuritySettingsComponent', () => {
  let component: ProfileSecuritySettingsComponent;
  let fixture: ComponentFixture<ProfileSecuritySettingsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProfileSecuritySettingsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProfileSecuritySettingsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
