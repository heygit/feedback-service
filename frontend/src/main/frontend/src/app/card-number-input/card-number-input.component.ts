import {Component, OnInit} from "@angular/core";

import {Router} from "@angular/router";
import {AuthService} from "../service/auth.service";


@Component({
  selector: 'card-number-input',
  templateUrl: './card-number-input.component.html'
})
export class CardNumberInputComponent implements OnInit {

  cardNumber: string = "";
  cardNumberLabel: string = "";

  constructor(private router: Router, private authService: AuthService) {}

  ngOnInit(): void {
  }

  onClick(number: Number): void {
    this.cardNumber+= String(number);
    this.cardNumberLabel= this.getFormattedValue(this.cardNumber);
  }

  getFormattedValue(cardNumber): string {
    if (cardNumber.length <= 4) {
      return cardNumber;
    }
    return cardNumber.substring(0, 4) + '-' + this.getFormattedValue(cardNumber.substring(4));
  }

  clear(): void {
    this.cardNumber = "";
    this.cardNumberLabel = "";
  }

  submit(): void {
    this.authService.checkCard(this.cardNumber)
      .then((status) => {
        if (status == 'ok') {
          this.router.navigate(['input-pin']);
        } else if (status == 'locked') {
          this.router.navigate(['error', 'cardLocked', 'input-card']);
        } else if (status == 'notFound') {
          this.router.navigate(['error', 'cartNotFound', 'input-card']);
        }
      })
      .catch(() => {
        this.router.navigate(['error']);
      });
  }

}
