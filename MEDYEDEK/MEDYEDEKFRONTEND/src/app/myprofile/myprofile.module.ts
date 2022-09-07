import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MyprofileRoutingModule } from './myprofile-routing.module';
import { MyprofileComponent } from './myprofile.component';
import { ProfileInformationsComponent } from './profile-informations/profile-informations.component';
import { ProfileGeneralSettingsComponent } from './profile-general-settings/profile-general-settings.component';
import { ProfileSecuritySettingsComponent } from './profile-security-settings/profile-security-settings.component';
import { RouterModule, RouterOutlet } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule } from '@angular/common/http';
import { ScrollingModule } from '@angular/cdk/scrolling';
import { MatDialogModule } from '@angular/material/dialog';


@NgModule({
  declarations: [
    ProfileInformationsComponent,
    ProfileGeneralSettingsComponent,
    ProfileSecuritySettingsComponent
  ],
  imports: [
    CommonModule,
    MyprofileRoutingModule,
    RouterModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    ScrollingModule,
    MatDialogModule,
    
  ],
  exports: [RouterModule]  
})
export class MyprofileModule { }
