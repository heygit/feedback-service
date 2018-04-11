import {Injectable} from "@angular/core";
import {HttpService} from "./http.service";
import {URLSearchParams} from "@angular/http";

import 'rxjs/add/operator/toPromise';
import {Product} from "../model/product";
import {Category} from "../model/category";

@Injectable()
export class ProductService {

  constructor(private httpService: HttpService) {}

  getProducts(): Promise<Product[]> {
    let params = new URLSearchParams();
    return this.httpService.get('api/v1/productManagement', params)
      .toPromise()
      .then(response => {
        return response.json().products as Product[];
      })
      .catch(this.handleError);
  }

  getProductsByCategory(categoryId: number): Promise<Product[]> {
    let params = new URLSearchParams();
    return this.httpService.get('api/v1/productManagement/category/' + categoryId, params)
      .toPromise()
      .then(response => {
        return response.json().products as Product[];
      })
      .catch(this.handleError);
  }

  getCategories(): Promise<Category[]> {
    let params = new URLSearchParams();
    return this.httpService.get('api/v1/productManagement/category', params)
      .toPromise()
      .then(response => {
        return response.json().categories as Category[];
      })
      .catch(this.handleError);
  }

  getProduct(productId: number): Promise<Product> {
    let params = new URLSearchParams();
    return this.httpService.get('api/v1/productManagement/' + productId, params)
      .toPromise()
      .then(response => {
        return response.json().product as Product;
      })
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    return Promise.reject(error.error || error);
  }
}
