import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  public baseUrl = "http://localhost:8080/user/current";

  constructor(private httpClient: HttpClient) { }

  public getCurrentUser(): Observable<any> {
    return this.httpClient.get(this.baseUrl);
  }
}
