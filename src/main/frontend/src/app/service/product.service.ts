import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import {IProduct} from "../interface/iproduct";

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  public baseUrl = "http://localhost:8080/product";

  constructor(private httpClient: HttpClient) { }

  public getProducts(): Observable<IProduct[]> {
    return this.httpClient.get<IProduct[]>(this.baseUrl);
  }
}
