import {Injectable} from "@angular/core";
import {Http, Headers, URLSearchParams} from "@angular/http";


@Injectable()
export class HttpService {

  private host: string = 'http://localhost:8080/';

  constructor(private http: Http) {}

  post(url: string, body: object, params: URLSearchParams) {
    const headers = this.getHeaders();
    return this.http.post(this.host + url, body, {
      headers: headers,
      params: params,
      withCredentials: true
    });
  }

  get(url: string, params: URLSearchParams) {
    const headers = this.getHeaders();
    return this.http.get(this.host + url, {
      headers: headers,
      params: params,
      withCredentials: true
    });
  }

  private getHeaders(): Headers {
    return new Headers({
      'Accept': 'application/json'
    });
  }
}
