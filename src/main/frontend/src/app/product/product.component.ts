import {Component} from '@angular/core';
import {IProduct} from "../interface/iproduct";
import {ProductService} from "../service/product.service";
import {UserService} from "../service/user.service";

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent {

  products!: IProduct[];

  displayedColumns: string[] = ['id', 'name'];

  constructor(private productService: ProductService, private userService: UserService) { }

  ngOnInit() {
    this.productService.getProducts().subscribe(data => {
      this.products = data;
    });
  }

}
