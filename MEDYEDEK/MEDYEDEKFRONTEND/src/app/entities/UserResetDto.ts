/* tslint:disable */
/* eslint-disable */
// Generated using typescript-generator version 2.24.612 on 2022-08-27 12:05:45.

export interface PostDto {
    id_post?: number;
    typepost?: Typepost;
    textContent?: string;
    user?: User;
    postContent?: Content[];
}

export interface SimpleUserDto extends Serializable {
    userEmail?: string;
}

export interface PasswordDto extends Serializable {
    email?: string;
    tempPass?: string;
    newPass?: string;
}

export interface ProfileDto {
    image?: string;
    firstname?: string;
    lastname?: string;
    address?: Address;
    postalCode?: string;
    phone_number?: string;
    cin?: string;
    role?: RoleDto;
    email?: string;
    pseudo?: string;
    domain?: string;
}

export interface Typepost {
    id_typepost?: number;
    nomtypepost?: string;
    post?: Post[];
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
    address?: Address;
    password?: string;
    userToken?: string;
    temp_pass?: string;
    notValid?: boolean;
}

export interface Content {
    id?: number;
    content?: any;
    post_id?: number;
    post?: Post[];
}

export interface Serializable {
}

export interface Address {
    adress_id?: number;
    country?: string;
    city?: string;
    district?: string;
    street?: string;
    postalCode?: string;
    utilisteur?: User;
}

export interface RoleDto {
    role_id?: number;
    rolename?: string;
}

export interface Post extends Serializable {
    id_post?: number;
    typepost?: Typepost;
    textContent?: string;
    user?: User;
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
