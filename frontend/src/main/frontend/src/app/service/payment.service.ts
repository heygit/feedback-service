import {Injectable} from "@angular/core";
import {HttpService} from "./http.service";
import {URLSearchParams} from "@angular/http";

import 'rxjs/add/operator/toPromise';
import {BalanceModel} from "../model/balance-model";
import {WithdrawalResultModel} from "../model/withdrawal-result-model";

@Injectable()
export class PaymentService {

  constructor(private httpService: HttpService) {}

  getBalance(): Promise<BalanceModel> {
    let params = new URLSearchParams();
    return this.httpService.post('api/v1/paymentManagement/getBalance', params)
      .toPromise()
      .then(response => {
        return response.json().balance as BalanceModel;
      })
      .catch(this.handleError);
  }

  getCash(amount: string): Promise<WithdrawalResultModel> {
    let params = new URLSearchParams();
    params.append('amount', amount);
    return this.httpService.post('api/v1/paymentManagement/getCash', params)
      .toPromise()
      .then(response => {
        let body = response.json();
        if (body.withdrawalResult != null) {
          return body.withdrawalResult as WithdrawalResultModel;
        }
        throw new Error(body.error);
      })
      .catch(this.handleError);
  }

  getWithdrawalResult(): Promise<WithdrawalResultModel> {
    let params = new URLSearchParams();
    return this.httpService.post('api/v1/paymentManagement/getWithdrawalResult', params)
      .toPromise()
      .then(response => {
        if (response.json().withdrawalResult == null) {
          throw new Error();
        }
        return response.json().withdrawalResult as WithdrawalResultModel;
      })
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    return Promise.reject(error.error || error);
  }
}
