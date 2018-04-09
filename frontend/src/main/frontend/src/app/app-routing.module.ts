import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {CardNumberInputComponent} from "./card-number-input/card-number-input.component";
import {PinInputComponent} from "./pin-input/pin-input.component";
import {OperationsComponent} from "./operations/operations.component";
import {BalanceComponent} from "./balance/balance.component";
import {GettingCashComponent} from "./getting-cash/getting-cash.component";
import {GettingCashResultComponent} from "./getting-cash-result/getting-cash-result.component";
import {ErrorComponent} from "./error/error.component";

const routes: Routes = [
  { path: '',
    redirectTo: '/input-card',
    pathMatch: 'full'
  },
  { path: 'input-card',
    component: CardNumberInputComponent
  },
  { path: 'input-pin',
    component: PinInputComponent
  },
  { path: 'operations',
    component: OperationsComponent
  },
  { path: 'balance',
    component: BalanceComponent
  },
  { path: 'getting-cash',
    component: GettingCashComponent
  },
  { path: 'getting-cash-result',
    component: GettingCashResultComponent
  },
  { path: 'error/:code/:redirect',
    component: ErrorComponent
  },
  { path: 'error',
    component: ErrorComponent
  }
];
@NgModule({
  imports: [RouterModule.forRoot(routes, {
    useHash: true
  })],
  exports: [RouterModule]
})
export class AppRoutingModule {}
