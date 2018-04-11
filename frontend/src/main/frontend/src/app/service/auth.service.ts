import {Injectable} from "@angular/core";
import {HttpService} from "./http.service";
import {URLSearchParams} from "@angular/http";

import 'rxjs/add/operator/toPromise';

@Injectable()
export class AuthService {

  constructor(private httpService: HttpService) {}

  login(username: string, password: string): Promise<boolean> {
    let params = new URLSearchParams();
    params.append('username', username);
    params.append('password', password);
    return this.httpService.post('api/v1/authManagement/login', null, params)
      .toPromise()
      .then(response => {
        return response.status == 200;
      })
      .catch(this.handleError);
  }

  isAuthorized(): Promise<boolean> {
    let params = new URLSearchParams();
    return this.httpService.get('api/v1/authManagement/check', params)
      .toPromise()
      .then(response => {
        return response.json().isAuthorized;
      })
      .catch(this.handleError);
  }

  logout(): Promise<boolean> {
    let params = new URLSearchParams();
    return this.httpService.post('api/v1/authManagement/logout', null, params)
      .toPromise()
      .then(response => {
        return response.status == 200;
      })
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    return Promise.reject(error.error || error);
  }
}
