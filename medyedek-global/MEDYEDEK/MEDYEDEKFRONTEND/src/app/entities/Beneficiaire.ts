export class Beneficiaire {


    firstname: any;
    lastname: any;
    email: any;
    nombre_postes: any;
    cin: any;
    _links : any;
    constructor(PostReponse: any) {

        this.firstname = PostReponse.beneficiaire.firstname;
        this.lastname = PostReponse.beneficiaire.lastname;
        this.email = PostReponse.beneficiaire.email;
        this.nombre_postes = PostReponse.beneficiaire.nombre_postes;
        this.cin = PostReponse.beneficiaire.cin;


    }
}
