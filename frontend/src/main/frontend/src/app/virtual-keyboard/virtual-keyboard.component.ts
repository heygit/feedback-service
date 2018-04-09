import {Component, EventEmitter, OnInit, Output} from "@angular/core";


@Component({
  selector: 'virtual-keyboard',
  templateUrl: './virtual-keyboard.component.html'
})
export class VirtualKeyboardComponent implements OnInit {

  @Output('buttonPress')
  private clickEmitter = new EventEmitter<Number>();

  constructor() {}

  ngOnInit(): void {
  }

  click(number: Number): void {
    this.clickEmitter.emit(number);
  }

}
