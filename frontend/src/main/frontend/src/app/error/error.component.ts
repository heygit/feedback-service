import {Component, OnInit} from "@angular/core";
import {ActivatedRoute, Params, Router} from "@angular/router";


@Component({
  selector: 'error',
  templateUrl: './error.component.html'
})
export class ErrorComponent implements OnInit {

  constructor(private route: ActivatedRoute, private router: Router) {}

  message: string = '';
  url: string = '';

  ngOnInit(): void {
    this.route.params
      .subscribe((params: Params) => {
        this.message = this.getMessage(params['code']);
        this.url = this.getRedirectUrl(params['redirect']);
      });
  }

  getMessage(code): string {
    if (code == null) {
      return 'Something went wrong';
    } else if (code == 'cardLocked') {
      return 'Card locked';
    } else if (code == 'cartNotFound') {
      return 'Card not found';
    } else if (code == 'wrongPin') {
      return 'Wrong Pin';
    }

    return 'Something went wrong';
  }

  getRedirectUrl(redirect): string {
    if (redirect == null) {
      return '';
    }

    return redirect;
  }

  return() {
    this.navigate(this.url);
  }

  toMain() {
    this.navigate('');
  }

  navigate(url) {
    this.router.navigate([url]);
  }

}
