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
      return 'Что-то пошло не так...';
    } else if (code == 'notAuthorized') {
      return 'Не удалось авторизовать';
    } else if (code == 'notRegistered') {
      return 'Не удалось зарегистрировать';
    }

    return 'Что-то пошло не так...';
  }

  toMain() {
    this.navigate('');
  }

  navigate(url) {
    this.router.navigate([url]);
  }

}
