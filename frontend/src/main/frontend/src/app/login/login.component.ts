import {Component, OnInit} from "@angular/core";
import {Router} from "@angular/router";
import {AuthService} from "../service/auth.service";

@Component({
  selector: 'login',
  templateUrl: './login.component.html'
})
export class LoginComponent implements OnInit {

  username: string;
  password: string;

  constructor(private router: Router, private authService: AuthService) {}

  ngOnInit(): void {
    this.authService.isAuthorized()
      .then((isAuthorized) => {
        if (isAuthorized) {
          this.router.navigate(['products']);
        }
      })
      .catch(() => {
        this.router.navigate(['error']);
      });
  }
}
