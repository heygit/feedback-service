import {Component, OnInit} from "@angular/core";
import {Router} from "@angular/router";
import {PaymentService} from "../service/payment.service";
import {BalanceModel} from "../model/balance-model";


@Component({
  selector: 'balance',
  templateUrl: './balance.component.html'
})
export class BalanceComponent implements OnInit {

  balance: BalanceModel;

  constructor(private router: Router, private paymentService: PaymentService) {}

  ngOnInit(): void {
    this.paymentService.getBalance()
      .then((balance) => {
        this.balance = balance;
      })
      .catch(() => {
        this.router.navigate(['error']);
      });
  }

}
