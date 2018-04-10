import {Component, OnInit} from "@angular/core";
import {ActivatedRoute, Params, Router} from "@angular/router";


@Component({
  selector: 'error',
  templateUrl: './error.component.html'
})
export class ErrorComponent implements OnInit {

  constructor(private route: ActivatedRoute, private router: Router) {}

  message: string = '';

  ngOnInit(): void {
    this.route.params
      .subscribe((params: Params) => {
        this.message = this.getMessage(params['code']);
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

  toMain() {
    this.navigate('');
  }

  navigate(url) {
    this.router.navigate([url]);
  }

}
