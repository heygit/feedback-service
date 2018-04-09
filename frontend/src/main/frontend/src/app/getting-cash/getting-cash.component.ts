import {Component, OnInit} from '@angular/core';

import {Router} from "@angular/router";
import {PaymentService} from "../service/payment.service";


@Component({
  selector: 'getting-cash',
  templateUrl: './getting-cash.component.html'
})
export class GettingCashComponent implements OnInit {

  amount: string = "";

  constructor(private router: Router, private paymentService: PaymentService) {}

  ngOnInit(): void {
  }

  onClick(number: Number): void {
    this.amount+= String(number);
  }

  clear(): void {
    this.amount = "";
  }

  submit(): void {
    this.paymentService.getCash(this.amount)
      .then((withdrawalResult) => {
        this.router.navigate(['getting-cash-result']);
      })
      .catch((error) => {
        this.router.navigate(['error', 'cartNotFound', '']);
      });
  }

}
