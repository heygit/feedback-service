import {Currency} from "./currency";

export class BalanceModel {
  private _cardNumber: string;
  private _date: string;
  private _amount: Currency;


  get cardNumber(): string {
    return this._cardNumber;
  }

  set cardNumber(value: string) {
    this._cardNumber = value;
  }

  get date(): string {
    return this._date;
  }

  set date(value: string) {
    this._date = value;
  }

  get amount(): Currency {
    return this._amount;
  }

  set amount(value: Currency) {
    this._amount = value;
  }
}
