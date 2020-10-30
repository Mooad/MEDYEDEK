import { Beneficiaire } from './Beneficiaire';

export interface Post {


    id_post: string;
    textContent: string;
    postdata?: File;
    beneficiaire?: any;

}