/* tslint:disable */
/* eslint-disable */

// Generated using typescript-generator version 2.24.612 on 2023-05-20 08:07:40.

export interface PostDto {
  post_id?: number;
  user_id?: number;
  typepost?: Typepost;
  textContent?: string;
  userImage?: string;
  postContent?: Content[];
  likesNB?: number;
  commentsNB?: number;
  sharesNB?: number;
  commentContent?: string;

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
  user_id: number ;
  image?: string;
  firstname?: string;
  lastname?: string;
  address?: Adress;
  postalCode?: string;
  phone_number?: string;
  cin?: string;
  role?: RoleDto;
  email?: string;
  pseudo?: string;
  domain?: string;
}

export interface PostInteractionDto {
  id?: number;
  post_id?: number;
  user_id?: number;
  interactionType?: InteractionType;
  comment?: string;
  nblikesIncremented?: boolean;
  likesNB: number

}

export interface Typepost {
  id_typepost?: number;
  nomtypepost?: string;
  post?: Post[];
}

export interface Content {
  id?: number;
  content?: string;
  contentType?: ContentType;
  post_id?: number;
}

export interface Serializable {
}

export interface Adress {
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
  post_id?: number;
  typepost?: Typepost;
  textContent?: string;
  user?: User;
  content?: Content[];
  likesNB?: number;
  commentsNB?: number;
  sharesNB?: number;
}

export interface User extends Serializable {
  user_id?: number;
  firstname?: string;
  lastname?: string;
  pseudo?: string;
  email?: string;
  cin?: string;
  phone_number?: string;
  image?: string;
  isValidUser?: string;
  role?: Role;
  domaine?: Domaine;
  address?: Adress;
  password?: string;
  userToken?: string;
  temp_pass?: string;
  valid?: boolean;
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

export enum InteractionType {
  LIKE = "LIKE",
  DISLIKE = "DISLIKE",
  COMMENT = "COMMENT",
  SHARE = "SHARE",
}

export enum ContentType {
  TEXT = "TEXT",
  IMAGE = "IMAGE",
  VIDEO = "VIDEO",
  FILE = "FILE",
}
