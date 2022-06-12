import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../_models/user';
import { ServerClientAccount } from '../_models/UserAccount';

const API_URL = 'http://localhost:8080/api/user/';
const API_ADMIN_URL = 'http://localhost:8080/api/admin/';


@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http: HttpClient) { }
  getAllUsers(): Observable<any> {
    return this.http.get(API_URL + 'all');
  }
  getUser(id:number): Observable<any>{
    return this.http.get(API_URL + id);
  }
  saveUser(user: User) {
    return this.http.post<User>(API_URL + 'add', user);
  }
  deleteUser(id: number): Observable<any>{
    return this.http.delete(API_URL+'delete/'+id);
  }
  updateUser(id: number,value: any): Observable<Object>{
    return this.http.post(API_URL+'update/'+id, value);
  }
  getAllServerAccounts(request): Observable<any>{
    return this.http.get(API_ADMIN_URL+'listeServerClientAccount?page='+request['page']+'&size='+request['size']);
  }
  addServerAccount(serverAccount: ServerClientAccount){
    const headers = new HttpHeaders().set('Content-Type', 'application/json; charset=utf-8');
    return this.http.post<User>(API_ADMIN_URL + 'listeServerClientAccount', serverAccount,{headers: headers});
  }
  getServerAccount(id:number): Observable<any>{
    return this.http.get(API_ADMIN_URL +"listeServerClientAccount/" + id);
  }
  updateServerAccount(id: number, value: any){
    return this.http.put(API_ADMIN_URL+'listeServerClientAccount/'+id, value);
  }
  deleteServerAccount(id: number): Observable<any>{
    return this.http.delete(API_ADMIN_URL +"listeServerClientAccount/" + id);
  }
  getAllWebAccounts(request): Observable<any>{
    return this.http.get(API_ADMIN_URL+'listeWebClientAccount?page='+request['page']+'&size='+request['size']);
  }
  getWebAccount(id:number): Observable<any>{
    return this.http.get(API_ADMIN_URL +"listeWebClientAccount/" + id);
  }
  updateWebAccount(id: number, value: any){
    return this.http.put(API_ADMIN_URL+'listeWebClientAccount/'+id, value);
  }
  deleteWebAccount(id: number): Observable<any>{
    return this.http.delete(API_ADMIN_URL +"listeWebClientAccount/" + id);
  }
}
