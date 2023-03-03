import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {IUser} from "../interface/iuser";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  public baseUrl = "http://localhost:8080/security/current";

  constructor(private httpClient: HttpClient) { }

  public getCurrentUser(): Observable<IUser> {
    return this.httpClient.get<IUser>(this.baseUrl);
  }
}
