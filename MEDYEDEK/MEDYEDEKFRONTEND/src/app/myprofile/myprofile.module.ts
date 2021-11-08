import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MyprofileRoutingModule } from './myprofile-routing.module';
import { MyprofileComponent } from './myprofile.component';
import { ProfileInformationsComponent } from './profile-informations/profile-informations.component';
import { ProfileGeneralSettingsComponent } from './profile-general-settings/profile-general-settings.component';
import { ProfileSecuritySettingsComponent } from './profile-security-settings/profile-security-settings.component';
import { RouterModule, RouterOutlet } from '@angular/router';


@NgModule({
  declarations: [MyprofileComponent,
    ProfileInformationsComponent,
    ProfileGeneralSettingsComponent,
    ProfileSecuritySettingsComponent
  ],
  imports: [
    CommonModule,
    MyprofileRoutingModule,
    RouterModule
  ],
  exports: [RouterModule]  
})
export class MyprofileModule { }
