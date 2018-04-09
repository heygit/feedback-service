export class Currency {
  private _integer: string;
  private _fractional: string;


  get integer(): string {
    return this._integer;
  }

  set integer(value: string) {
    this._integer = value;
  }

  get fractional(): string {
    return this._fractional;
  }

  set fractional(value: string) {
    this._fractional = value;
  }
}
