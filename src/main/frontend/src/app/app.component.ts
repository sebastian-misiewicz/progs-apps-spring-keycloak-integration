import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Product } from './product';
import { User } from './user';
import { ProductService } from './product.service';
import { UserService } from './user.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'progs-apps-spring-keycloak-integration-frontend';

  products!: Product[];

  currentUser!: User;

  displayedColumns: string[] = ['id', 'name'];

  constructor(private productService: ProductService, private userService: UserService) { }

  ngOnInit() {
    this.productService.getProducts().subscribe(data => {
      this.products = data;
    });

    this.userService.getCurrentUser().subscribe(data => {
      this.currentUser = data;
    });
  }
}
