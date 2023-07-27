import { Component, OnInit, Input } from '@angular/core';
import { ProfileDto } from 'src/app/entities/UserResetDto';
import { FormGroup, FormBuilder, Validators} from '@angular/forms';
import { ProfileService } from 'src/app/services/profile.service';
import { DialogService } from 'src/app/services/DialogService';

@Component({
  selector: 'app-profile-informations',
  templateUrl: './profile-informations.component.html',
  styleUrls: ['./profile-informations.component.scss']
})
export class ProfileInformationsComponent implements OnInit {

  profileFrom: FormGroup;
  @Input() profile: ProfileDto;

  constructor(private formBuilder:FormBuilder,private profileService:ProfileService,private dialogService: DialogService) {
  }

  ngOnInit(): void {

      //loading the profile informations
    this.profile = JSON.parse(localStorage.getItem("currentProfile"));

    if (this.profile) {
      if(!this.profile.image.startsWith("data:image"))
      {
        this.profile.image = 'data:image/jpeg;base64,' + this.profile.image

    }
    }
    console.log(this.profile);

    this.profileFrom = this.formBuilder.group({
      lastname: [this.profile.lastname, Validators.required],
      firstname: [this.profile.firstname, Validators.required],
      pseudo: [this.profile.pseudo, Validators.required],
      postalCode :[this.profile.address.postalCode, Validators.required],
      role:[this.profile.role.rolename, Validators.required],
      phone_number:[this.profile.phone_number, Validators.required],
      country:[this.profile.address.country , Validators.required],
      city:[this.profile.address.city , Validators.required],
      district:[this.profile.address.district , Validators.required],
      street:[this.profile.address.street , Validators.required],
    }, {
    });

  }
  onSubmit() {

    console.log(this.profileFrom);
    // stop here if form is invalid
    if (this.profileFrom.invalid) {
      alert('Merci de renseigner les bonnes Informations');
      return;
    }
    else {
      console.log(this.profileFrom);
     this.profileService.syncUserProfile(this.profile)
      .subscribe(
          (res) => {
            {
              localStorage['currentProfile']= JSON.stringify(this.profile);
              this.dialogService.confirmationDialog("Your Account Informations are updated");
            }
          }
      );
    }
  }

      //Gets called when the user selects an image
      public onFileChanged(event) {

        let me = this;
        let file = event.target.files[0];
        let reader = new FileReader();
        reader.readAsDataURL(file);
        console.log(reader.result);

        reader.onload = function () {
          console.log(reader.result);
          me.profile.image = reader.result.toString();

        };
        this.profile.image = me.profile.image;
        reader.onerror = function (error) {
          console.log('Error: ', error);
        };
      }


}
