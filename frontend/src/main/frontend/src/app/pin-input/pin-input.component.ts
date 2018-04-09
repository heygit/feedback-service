import {Component, OnInit} from "@angular/core";
import {Router} from "@angular/router";
import {AuthService} from "../service/auth.service";


@Component({
  selector: 'pin-input',
  templateUrl: './pin-input.component.html'
})
export class PinInputComponent implements OnInit {

  pin: string = "";
  pinLabel: string = "";

  constructor(private router: Router, private authService: AuthService) {}

  ngOnInit(): void {
  }

  onClick(number: Number): void {
    this.pin+= String(number);
    this.pinLabel= this.getFormattedValue(this.pin);
  }

  getFormattedValue(pin): string {
    let result = '';
    for (let i = 0; i < pin.length; i++) {
      result += '*';
    }
    return result;
  }

  clear(): void {
    this.pin = "";
    this.pinLabel = "";
  }

  exit(): void {
    this.router.navigate(['']);
  }

  submit(): void {
    this.authService.checkPin(this.pin)
      .then((status) => {
        if (status == 'ok') {
          this.router.navigate(['operations']);
        } else if (status == 'wrongPin') {
          this.router.navigate(['error', 'wrongPin', 'input-pin']);
        } else if (status == 'cardLocked') {
          this.router.navigate(['error', 'cardLocked', 'input-card']);
        }
      })
      .catch(() => {
        this.router.navigate(['error']);
      });
  }

}
