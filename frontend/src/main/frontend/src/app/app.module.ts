import {NgModule} from "@angular/core";
import {BrowserModule} from "@angular/platform-browser";
import {FormsModule} from "@angular/forms";
import {HttpModule} from "@angular/http";

import {AppRoutingModule} from "./app-routing.module";

import {AppComponent} from "./app/app.component";
import {ErrorComponent} from "./error/error.component";
import {AuthService} from "./service/auth.service";
import {HttpService} from "./service/http.service";
import {ProductService} from "./service/product.service";
import {ProductsComponent} from "./products/products.component";
import {ProductComponent} from "./product/product.component";
import {FeedbackService} from "./service/feedback.service";
import {CategoriesComponent} from "./categories/categories.component";
import {LoginComponent} from "./login/login.component";
import {RegisterComponent} from "./register/register.component";


@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    AppRoutingModule
  ],
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    ProductsComponent,
    CategoriesComponent,
    ProductComponent,
    ErrorComponent,
  ],
  providers: [
    AuthService,
    ProductService,
    FeedbackService,
    HttpService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {}
