import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {ErrorComponent} from "./error/error.component";
import {ProductComponent} from "./product/product.component";
import {ProductsComponent} from "./products/products.component";
import {LoginComponent} from "./login/login.component";

const routes: Routes = [
  { path: '',
    redirectTo: '/login',
    pathMatch: 'full'
  },
  { path: 'login',
    component: LoginComponent
  },
  { path: 'products',
    component: ProductsComponent
  },
  { path: 'product/:id',
    component: ProductComponent
  },
  { path: 'error/:code',
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
