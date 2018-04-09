import {Component, OnInit} from "@angular/core";
import {Router} from "@angular/router";
import {AuthService} from "../service/auth.service";


@Component({
  selector: 'operations',
  templateUrl: './operations.component.html'
})
export class OperationsComponent implements OnInit {

  constructor(private router: Router, private authService: AuthService) {}

  ngOnInit(): void {
  }

  showBalance(): void {
    this.router.navigate(['balance']);
  }

  getCash(): void {
    this.router.navigate(['getting-cash']);
  }


  quit(): void {
    this.authService.logout();
    this.authService.deleteAllCookies();
    this.router.navigate(['']);
  }

}
