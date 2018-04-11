import {Component, EventEmitter, OnInit, Output} from "@angular/core";
import {Router} from "@angular/router";
import {ProductService} from "../service/product.service";
import {Category} from "../model/category";

@Component({
  selector: 'categories',
  templateUrl: './categories.component.html'
})
export class CategoriesComponent implements OnInit {

  categories: Category[];

  @Output('selectCategory')
  private clickEmitter = new EventEmitter<Number>();

  constructor(private router: Router, private productService: ProductService) {}

  ngOnInit(): void {
    this.productService.getCategories()
      .then((categories) => {
        this.categories = categories;
      })
      .catch(() => {
        this.router.navigate(['error']);
      });
  }

  showProducts(categoryId: number): void {
    this.clickEmitter.emit(categoryId);
  }
}
