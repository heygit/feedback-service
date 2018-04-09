import {Injectable} from "@angular/core";
import {HttpService} from "./http.service";
import {URLSearchParams} from "@angular/http";

import 'rxjs/add/operator/toPromise';

@Injectable()
export class AuthService {

  constructor(private httpService: HttpService) {}

  checkCard(cardNumber: string): Promise<string> {
    let params = new URLSearchParams();
    params.append('cardNumber', cardNumber);
    return this.httpService.post('api/v1/authManagement/checkCard', params)
      .toPromise()
      .then(response => {
        return response.json().status as string
      })
      .catch(this.handleError);
  }

  checkPin(pin: string): Promise<string> {
    let params = new URLSearchParams();
    params.append('pin', pin);
    return this.httpService.post('api/v1/authManagement/checkPin', params)
      .toPromise()
      .then(response => {
        return response.json().status as string
      })
      .catch(this.handleError);
  }

  logout(): Promise<boolean> {
    let params = new URLSearchParams();
    return this.httpService.post('api/v1/authManagement/logout', params)
      .toPromise()
      .then(response => {
        return response.status == 200;
      })
      .catch(this.handleError);
  }

  deleteAllCookies() {
    document.cookie
      .split(';')
      .forEach(cookie => {
        var eqPos = cookie.indexOf("=");
        var name = eqPos > -1 ? cookie.substr(0, eqPos) : cookie;
        document.cookie = name + "=;expires=Thu, 01 Jan 1970 00:00:00 GMT";
      });
  }

  private handleError(error: any): Promise<any> {
    return Promise.reject(error.error || error);
  }
}
