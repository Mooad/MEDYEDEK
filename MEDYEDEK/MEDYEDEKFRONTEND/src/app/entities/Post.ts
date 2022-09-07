/* tslint:disable */
/* eslint-disable */
// Generated using typescript-generator version 2.24.612 on 2021-07-13 21:45:16.

export interface PostDto {
    id_post?: number;
    typepost?: Typepost;
    textContent?: string;
    user?: Utilisateur;
    postContent?: Content[];
}

export interface Typepost {
    id_typepost?: number;
    nomtypepost?: string;
    post?: Post[];
}

export interface Beneficiaire extends Utilisateur {
    nombre_postes?: number;
}

export interface Content {
    id?: number;
    content?: any;
    post_id?: number;
    post?: Post[];
}

export interface Post extends Serializable {
    id_post?: number;
    typepost?: Typepost;
    textContent?: string;
    Beneficiaire?: Beneficiaire;
    user?: Utilisateur;
}

export interface Role {
    role_id?: number;
    rolename?: string;
    utilisateurs?: Utilisateur[];
}

export interface Domaine extends Serializable {
    domaine_id?: number;
    nomdomaine?: string;
}

export interface Adresse {
    adresse_id?: number;
    rue?: string;
    utilisteur?: Utilisateur;
    quartier?: string;
    pays?: string;
    ville?: string;
}

export interface Utilisateur extends Serializable {
    user_id?: number;
    firstname?: string;
    lastname?: string;
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
    notValid?: boolean;
}

export interface Serializable {
}
