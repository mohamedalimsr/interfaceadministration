import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Boitier } from '../_models/boitier';
import { User } from '../_models/user';
import { WebClientAccount } from '../_models/UserAccount';


const API_BOITIER_URL = 'http://localhost:8080/api/user/boitier/';
const API_ADMIN_WEB_URL = 'http://localhost:8080/api/admin/';


@Injectable({
  providedIn: 'root'
})
export class ServerService {

  constructor(private http: HttpClient) { }
  
  getAllBoitiers(request): Observable<any>{
    return this.http.get(API_BOITIER_URL+'all?page='+request['page']+'&size='+request['size']);
  }

  addBoitier(boitier: Boitier) {
    return this.http.post<Boitier>(API_BOITIER_URL + 'add', boitier);
  }

  getAllWebAccounts(request): Observable<any>{
    return this.http.get(API_ADMIN_WEB_URL+'listeWebClientAccount?page='+request['page']+'&size='+request['size']);
  }
  addWebAccount(webAccount: WebClientAccount){
    const headers = new HttpHeaders().set('Content-Type', 'application/json; charset=utf-8');
    return this.http.post<User>(API_ADMIN_WEB_URL + 'listeWebClientAccount', webAccount,{headers: headers});
  }
  getWebAccount(id:number): Observable<any>{
    return this.http.get(API_ADMIN_WEB_URL +"listeWebClientAccount/" + id);
  }
  updateWebAccount(id: number, value: any){
    return this.http.put(API_ADMIN_WEB_URL+'listeWebClientAccount/'+id, value);
  }
  deleteWebAccount(id: number): Observable<any>{
    return this.http.delete(API_ADMIN_WEB_URL +"listeWebClientAccount/" + id);
  }
}
