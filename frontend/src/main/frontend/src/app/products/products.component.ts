import {Component, OnInit} from "@angular/core";
import {Router} from "@angular/router";
import {ProductService} from "../service/product.service";
import {Product} from "../model/product";

@Component({
  selector: 'products',
  templateUrl: './products.component.html'
})
export class ProductsComponent implements OnInit {

  products: Product[];

  constructor(private router: Router, private productService: ProductService) {}

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

  gotoDetail(product: Product): void {
    this.router.navigate(['/product', product.id]);
  }
}
