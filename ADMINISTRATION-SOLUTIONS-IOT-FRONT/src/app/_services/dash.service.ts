import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DashService {

  private baseUrl = 'https://api.coinranking.com/v2';
  private proxyUrl = 'https://cors-anywhere.herokuapp.com/';

  constructor(private http: HttpClient) { 
    
  }
}
