import {Injectable} from "@angular/core";
import {HttpService} from "./http.service";
import {URLSearchParams} from "@angular/http";

import 'rxjs/add/operator/toPromise';
import {Product} from "../model/product";
import {FeedbackResult} from "../model/feedback-result";
import {Feedback} from "../model/feedback";

@Injectable()
export class FeedbackService {

  constructor(private httpService: HttpService) {}

  getFeebacksByProductId(productId: number): Promise<FeedbackResult> {
    let params = new URLSearchParams();
    return this.httpService.get('api/v1/feedbackManagement/product/' + productId, params)
      .toPromise()
      .then(response => {
        return response.json().feedbackResult as FeedbackResult;
      })
      .catch(this.handleError);
  }

  postFeedback(productId: number, feedback: Feedback): Promise<void> {
    let params = new URLSearchParams();
    return this.httpService.post('api/v1/feedbackManagement/product/' + productId, feedback, params)
      .toPromise()
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    return Promise.reject(error.error || error);
  }
}
