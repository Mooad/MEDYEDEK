import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MyprofileComponent } from './myprofile.component';
import { LoginComponent } from '../login/login.component';
import { ProfileSecuritySettingsComponent } from './profile-security-settings/profile-security-settings.component';
import { ProfileInformationsComponent } from './profile-informations/profile-informations.component';
import { ProfileGeneralSettingsComponent } from './profile-general-settings/profile-general-settings.component';

 const routes: Routes = [
  {
    path: '', component: MyprofileComponent, children: [

      {
        path: 'infos', component: ProfileInformationsComponent,
      },
      {
        path: 'security', component: ProfileSecuritySettingsComponent,
      },
      {
        path: 'preferences', component: ProfileGeneralSettingsComponent,
      },
]
  }
 ];

 
@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MyprofileRoutingModule { 

  
}

