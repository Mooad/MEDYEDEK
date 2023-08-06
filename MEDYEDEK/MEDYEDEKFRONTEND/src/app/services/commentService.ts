import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable, of} from 'rxjs';
import {AppConfig} from '../config/appConfig';
import {CommentAttachement, CommentDto} from "../entities/Post";
import {Comment } from '../entities/Post';

@Injectable({
  providedIn: 'root',
})
export class CommentService {


  constructor(private http: HttpClient, private appConfig: AppConfig) {
  }

  public getCommentsGrappe(identifier: string): Observable<CommentDto[]> {
    return this.http.post<CommentDto[]>(this.appConfig.baseUrl + 'postComments/search', {identifier});
  }

  public insertCommentlevel0(comment: Comment): Observable<any> {
    console.log('Comment service comment: ' + comment.text);
    // Get the current comments state as an observable
    if(!comment.parent )
    {
      return  this.http.post<any>(this.appConfig.baseUrl + 'postComments/comment', {
        text: comment.text,
        content: comment.content,
        post_id: comment.post_id,
        user_id: comment.user_id,
        level: 0,
        parent: null,
        commentsTree:{}
      });
    }
      return  this.http.post<any>(this.appConfig.baseUrl + 'postComments/comment', {
        text: comment.text,
        content: comment.content,
        post_id: comment.post_id,
        user_id: comment.user_id,
        level: 0,
        parent: comment.parent,
        commentsTree:{}
      });

  }

  public insertCommentlevelx(comment: Comment): Observable<any> {
    return of(comment);
  }
  public synchroniseCommentlevelx(comment: Comment): Observable<any> {
    if(comment.parent )
    {
      return  this.http.post<any>(this.appConfig.baseUrl + 'postReply/reply', {
        _id:comment._id,
        text: comment.text,
        content: comment.content,
        post_id: comment.post_id,
        user_id: comment.user_id,
        level: 0,
        parent: comment.parent,
        commentsTree:{}
      });
    }
  }

  public attachCommentToPost( commentAttachement :CommentAttachement ) : void
{
  this.http.post<any>(this.appConfig.baseUrl + 'postComments/attachComment', commentAttachement).subscribe();
}

}


//
// Pour la personne qui souhaite ouvrir une société de création d'enseignes publicitaires et qui recherche des livres sur la vente, la compréhension du client et la publicité, voici quelques suggestions de livres qui pourraient l'aider à approfondir ces sujets :
//
// Livres sur la vente et la compréhension du client :
// a) "Comment se faire des amis" (Titre original : "How to Win Friends and Influence People") par Dale Carnegie - Ce livre classique de développement personnel offre des conseils pratiques sur la communication, la persuasion et la compréhension des autres. Il aide à améliorer les relations interpersonnelles, ce qui peut être très utile lors de la vente de produits ou de services.
//
// b) "To Sell Is Human: The Surprising Truth About Moving Others" par Daniel H. Pink - Dans ce livre, Daniel Pink explore le concept selon lequel nous sommes tous des vendeurs, que nous soyons ou non dans un rôle de vente traditionnel. Il examine comment la persuasion et la communication influencent nos interactions quotidiennes et comment développer des compétences de vente efficaces.
//
// c) "Pitch Anything: An Innovative Method for Presenting, Persuading, and Winning the Deal" par Oren Klaff - Ce livre propose une approche novatrice pour présenter et persuader lors de réunions de vente ou de négociations. Il aborde les aspects psychologiques de la persuasion et fournit des techniques pour captiver et convaincre votre auditoire.
//
// Livre sur la publicité :
// a) "Hey, Whipple, Squeeze This: The Classic Guide to Creating Great Ads" par Luke Sullivan - Ce livre est une référence pour les professionnels de la publicité. Il explore les principes de base de la création d'annonces efficaces et engageantes, en utilisant des exemples concrets et des études de cas.
//
// Il est important de noter que la plupart de ces livres sont disponibles en anglais. Si la personne préfère des livres en français, il peut être utile de rechercher des traductions ou des ouvrages similaires écrits dans cette langue.
//
// En plus de la lecture de livres, la personne peut également consulter des ressources en ligne, assister à des webinaires ou des ateliers, et chercher des mentors ou des experts locaux dans le domaine de la publicité et de la vente pour obtenir des conseils et des informations spécifiques au marché de Casablanca.
//
// N'oubliez pas que chaque entreprise est unique, donc il est important d'adapter les connaissances acquises à la situation particulière de la société de création d'enseignes publicitaires et de son marché local. Bonne chance dans cette nouvelle aventure entrepreneuriale !




