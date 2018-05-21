import {Component, OnInit} from "@angular/core";
import {Router} from "@angular/router";
import {AuthService} from "../service/auth.service";

@Component({
  selector: 'register',
  templateUrl: './register.component.html'
})
export class RegisterComponent implements OnInit {

  username: string;
  password: string;

  constructor(private router: Router, private authService: AuthService) {}

  ngOnInit(): void {
  }

  register(): void {
    this.authService.register(this.username, this.password)
      .then((isRegistered) => {
        if (isRegistered) {
          this.login();
        }
      })
      .catch(() => {
        this.router.navigate(['error', 'notRegistered']);
      });
  }

  login(): void {
    this.router.navigate(['login']);
  }
}
