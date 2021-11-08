/* tslint:disable */
/* eslint-disable */
// Generated using typescript-generator version 2.24.612 on 2021-11-06 13:50:03.

export interface PostDto {
    id_post?: number;
    typepost?: Typepost;
    textContent?: string;
    beneficiaire?: Beneficiaire;
    postContent?: Content[];
}

export interface UserResetDto extends Serializable {
    userEmail?: string;
}

export interface PasswordDto extends Serializable {
    email?: string;
    tempPass?: string;
    newPass?: string;
}

export interface Typepost {
    id_typepost?: number;
    nomtypepost?: string;
    post?: Post[];
}

export interface Beneficiaire extends User {
    nombre_postes?: number;
}

export interface Content {
    id?: number;
    content?: any;
    post_id?: number;
    post?: Post[];
}

export interface Serializable {
}

export interface Post extends Serializable {
    id_post?: number;
    typepost?: Typepost;
    textContent?: string;
    Beneficiaire?: Beneficiaire;
    beneficiaire?: Beneficiaire;
}

export interface Role {
    role_id?: number;
    rolename?: string;
    utilisateurs?: User[];
}

export interface Domaine extends Serializable {
    domaine_id?: number;
    nomdomaine?: string;
}

export interface Adresse {
    adresse_id?: number;
    rue?: string;
    utilisteur?: User;
    pays?: string;
    ville?: string;
    quartier?: string;
}

export interface User extends Serializable {
    user_id?: number;
    firstname?: string;
    lastname?: string;
    pseudo?: string;
    email?: string;
    cin?: string;
    phone_number?: string;
    image?: any;
    isValidUser?: string;
    role?: Role;
    domaine?: Domaine;
    adresse?: Adresse;
    password?: string;
    userToken?: string;
    temp_pass?: string;
    notValid?: boolean;
}
