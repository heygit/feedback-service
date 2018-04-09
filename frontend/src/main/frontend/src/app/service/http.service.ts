import {Injectable} from "@angular/core";
import {Http, Headers, URLSearchParams} from "@angular/http";


@Injectable()
export class HttpService {

  private host: string = 'http://localhost:8066/war-1.0-SNAPSHOT/';

  constructor(private http: Http) {}

  post(url: string, params: URLSearchParams) {
    const headers = this.getHeaders();
    return this.http.post(this.host + url, null, {
      headers: headers,
      params: params,
      withCredentials: true
    });
  }

  private getHeaders(): Headers {
    const csrfToken = this.getCsrfToken();
    return new Headers({
      'Accept': 'application/json',
      'X-CSRF-TOKEN': csrfToken
    });
}

  private getCsrfToken(): string {
    return this.getCookie('JSESSIONID');
  }

  // Given a cookie key `name`, returns the value of
  // the cookie or `null`, if the key is not found.
  private getCookie(name: string): string {
    const nameLenPlus = (name.length + 1);
    return document.cookie
        .split(';')
        .map(c => c.trim())
        .filter(cookie => {
          return cookie.substring(0, nameLenPlus) === `${name}=`;
        })
        .map(cookie => {
          return decodeURIComponent(cookie.substring(nameLenPlus));
        })[0] || null;
  }

}
