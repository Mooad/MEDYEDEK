import { Router } from '@angular/router';
import { Component, OnInit, Input } from '@angular/core';
import { FormGroup, FormBuilder, Validators, AbstractControl, FormControl } from '@angular/forms';
import { InscriptionService } from '../services/registration/registration-service.service';
import { LoginproxyService } from '../services/loginproxy.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.scss']
})
export class InscriptionComponent implements OnInit {

  registerForm: FormGroup;
  submitted;
  selectedFile: Blob;
  retrievedImage: any;
  base64Data: any;
  retrieveResonse: any;
  message: string;
  imageName: any;
  USER: any;
  Image64: any;
  retreivedImage:any;
  ImageBaseData:string | ArrayBuffer=null;
  @Input() ErrorLogin: string;
  validPattern: string;
  constructor(private formBuilder: FormBuilder, private inscriptionService: InscriptionService, private router: Router , private Loginproxy:LoginproxyService ) { }

  ngOnInit(): void {
    this.registerForm = this.formBuilder.group({

      lastname: ['', Validators.required],
      firstname: ['', Validators.required],
      pseudo: ['', Validators.pattern(this.validPattern),Validators.required],
      phone_number: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      postalcode: ['', [Validators.required, Validators.minLength(6)]],
      age: ['', Validators.maxLength(3)],
      mdp: ['', Validators.maxLength(20)],
      confirmationmdp: ['', Validators.maxLength(20)],
      domaine: ['', Validators.required],
      country: ['', Validators.required],
      city: ['', Validators.required],
      rue: ['', Validators.required],
      quartier: ['', Validators.required],
      cin: ['', Validators.required],
      role: ['', Validators.required]
    }, {
    });
  }

  public get f() { return this.registerForm.controls; }


    //Gets called when the user selects an image
    public onFileChanged(event) {
    
      let me = this;
      let file = event.target.files[0];
      let reader = new FileReader();
      reader.readAsDataURL(file);
      reader.onload = function () {
        console.log(reader.result);
        me.ImageBaseData=reader.result;
      };
      reader.onerror = function (error) {
        console.log('Error: ', error);
      };
    }
  
  onSubmit() {
    let reader = new FileReader();
    let readerBlob = new FileReader();

    console.log(this.registerForm);
    this.submitted = true;
    // stop here if form is invalid
    var b;
    if (this.registerForm.invalid) {
      alert('Merci de renseigner tout les champs necessaires');
    }
    else {
      // display form values on success

      var PrenomUtilisateur = this.registerForm.controls['firstname'].value;
     
      //Adding the selected image to the JSON
    
    /*   reader.readAsDataURL(this.selectedFile);
      reader.onload = function (event: any) {
       b= event.target.result;



      } */

      /* onst uploadImageData = new FormData();
      uploadImageData.append('imageFile', this.selectedFile, this.selectedFile.name);
      readerBlob.readAsBinaryString(this.selectedFile);
      
      this.registerForm.patchValue({
        image: this.selectedFile
      });
      this.registerForm.get('image').updateValueAndValidity() */
      var formData: any = new FormData();
      console.log(this.registerForm);

      this.USER = {};
      this.registerForm.addControl('image', new FormControl(''), ); // Add new form control

      
      //affectation de l'image (Base 64) a une variable du formulaire
      this.registerForm.controls['image'].setValue(this.ImageBaseData);

      this.inscriptionService.addUser(JSON.parse(JSON.stringify(this.registerForm.value)));


    }


  }

  Test() {
    this.registerForm.controls['lastname'].setValue('MOAD');
    this.registerForm.controls['firstname'].setValue('MOAD');
    this.registerForm.controls['pseudo'].setValue('MOADXXRR');
    this.registerForm.controls['phone_number'].setValue('0605662626');
    this.registerForm.controls['email'].setValue("MOAD52@hotmail.fr");
    this.registerForm.controls['postalcode'].setValue("1111111");
    this.registerForm.controls['age'].setValue("12");
    this.registerForm.controls['mdp'].setValue("AAAAAA");
    this.registerForm.controls['confirmationmdp'].setValue("AAAAAA");
    this.registerForm.controls['country'].setValue("MAROC");
    this.registerForm.controls['city'].setValue("CAsablanca");
    this.registerForm.controls['rue'].setValue("hasania");
    this.registerForm.controls['quartier'].setValue("moard");
    this.registerForm.controls['cin'].setValue("VIZZZZ");
    this.registerForm.controls['role'].setValue("Baker");
    this.registerForm.addControl('image', new FormControl(''), ); // Add new form control
    


  }



  onReset() {
    this.registerForm.reset();
  }


  //Gets called when the user clicks on submit to upload the image

}

