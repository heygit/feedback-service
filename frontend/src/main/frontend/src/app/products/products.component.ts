import {Component, OnInit} from "@angular/core";
import {Router} from "@angular/router";
import {ProductService} from "../service/product.service";
import {Product} from "../model/product";
import {AuthService} from "../service/auth.service";

@Component({
  selector: 'products',
  templateUrl: './products.component.html'
})
export class ProductsComponent implements OnInit {

  products: Product[];

  constructor(private router: Router, private productService: ProductService, private authService: AuthService) {}

  ngOnInit(): void {
    this.productService.getProducts()
      .then((products) => {
        this.products = products;
      })
      .catch(() => {
        this.router.navigate(['error']);
      });
  }

  updateProducts(categoryId: number): void {
    this.productService.getProductsByCategory(categoryId)
      .then((products) => {
        this.products = products;
      })
      .catch(() => {
        this.router.navigate(['error']);
      });
  }

  logout(): void {
    this.authService.logout()
      .then(() => {
        this.router.navigate(['']);
      })
      .catch(() => {
        this.router.navigate(['error']);
      });
  }

  gotoDetail(product: Product): void {
    this.router.navigate(['/product', product.id]);
  }
}
