import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Product } from './product';
import { ProductService } from './product.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'progs-apps-spring-keycloak-integration-frontend';

  products!: Product[];

  displayedColumns: string[] = ['id', 'name'];

  constructor(private productService: ProductService) { }

  ngOnInit() {
    this.productService.getProducts().subscribe(data => {
      console.log(data);
      this.products = data;
    })
  }
}
