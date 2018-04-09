import {Component, OnInit} from "@angular/core";

import {Params, Router} from "@angular/router";
import {WithdrawalResultModel} from "../model/withdrawal-result-model";
import {PaymentService} from "../service/payment.service";


@Component({
  selector: 'getting-cash-result',
  templateUrl: './getting-cash-result.component.html'
})
export class GettingCashResultComponent implements OnInit {

  withdrawalResult: WithdrawalResultModel;

  constructor(private router: Router, private paymentService: PaymentService) {}

  ngOnInit(): void {
    this.paymentService.getWithdrawalResult()
      .then((withdrawalResult) => {
        this.withdrawalResult = withdrawalResult;
      })
      .catch((error) => {
        this.router.navigate(['error']);
      });
  }

}
