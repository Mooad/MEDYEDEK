import { Beneficiaire } from './Beneficiaire';

export interface Post {


    id_post: string;
    typePost:any;
    textContent: string;
    postdata?: Array<File>;
    beneficiaire?: any;
    postContent :Array<File>;
    
}