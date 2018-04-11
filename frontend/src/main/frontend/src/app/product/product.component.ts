import {Component, OnInit} from "@angular/core";
import {ActivatedRoute, Params, Router} from "@angular/router";
import {ProductService} from "../service/product.service";
import {Product} from "../model/product";
import {FeedbackResult} from "../model/feedback-result";
import {FeedbackService} from "../service/feedback.service";
import {Feedback} from "../model/feedback";

@Component({
  selector: 'product',
  templateUrl: './product.component.html'
})
export class ProductComponent implements OnInit {

  product: Product;
  feedbackResult: FeedbackResult;
  rateComment: string;
  rateNumber: number;
  comment: string;

  constructor(private activatedRoute: ActivatedRoute, private router: Router,
              private productService: ProductService, private feedbackService: FeedbackService) {}

  ngOnInit(): void {
    this.activatedRoute.params
      .subscribe((params: Params) => {
        let productId = params['id'];
        this.productService.getProduct(productId)
          .then((product) => {
            this.product = product;
          })
          .catch(() => {
            this.router.navigate(['error']);
          });
        this.feedbackService.getFeebacksByProductId(productId)
          .then((feedbackResult) => {
            this.feedbackResult = feedbackResult;
          })
          .catch(() => {
            this.router.navigate(['error']);
          });
      });
  }

  sendFeedback(): void {
    let feedback = new Feedback();
    feedback.comment = this.comment;
    feedback.mark = this.rateNumber;

    this.feedbackService.postFeedback(this.product.id, feedback)
      .then(() => {
        this.router.navigate(['products']);
      })
      .catch(() => {
        this.router.navigate(['error']);
      });
  }

  rate(mark: number): void {
    this.rateNumber = mark;
    this.rateComment = this.getRateComment(mark);
    this.comment = this.comment;
  }

  getRateComment(mark: number): string {
    if (mark == 1) {
      return 'Ужасно';
    } else if (mark == 2) {
      return 'Плохо';
    } else if (mark == 3) {
      return 'Средне';
    } else if (mark == 4) {
      return 'Хорошо';
    } else if (mark == 5) {
      return 'Отлично';
    }
    return null;
  }
}
