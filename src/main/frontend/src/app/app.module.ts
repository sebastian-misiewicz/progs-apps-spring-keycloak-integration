import {APP_INITIALIZER, NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {AppComponent} from './app.component';
import {MatTableModule} from '@angular/material/table';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatCardModule} from '@angular/material/card';
import {MatListModule} from '@angular/material/list';
import {AuthenticationHeaderInterceptor} from './interceptor/authentication-header-interceptor.service';
import {CookieService} from 'ngx-cookie-service';
import {AppRoutingModule} from "./app-routing.module";
import {ProductComponent} from './product/product.component';
import {NavigationComponent} from './navigation/navigation.component';
import {SecurityService} from "./service/security.service";
import {JWTTokenServiceService} from "./service/jwttoken-service.service";

export function  initializeApp1(securityService: SecurityService, jwtTokenService: JWTTokenServiceService) {
  return (): Promise<any> => {
    return new Promise<void>(async (resolve) => {
      const queryString = window.location.search;
      const urlParams = new URLSearchParams(queryString)
      const authorizationCode = urlParams.get('code');
      if (authorizationCode) {
        await securityService.assignToken(authorizationCode);
      }

      if (jwtTokenService.isTokenExpired()) {
        securityService.goToAuth();
      }

      if (!jwtTokenService.getUser()) {
        securityService.goToAuth();
      }

      window.history.pushState({}, document.title, "/");

      resolve();
    });
  }
}

@NgModule({
  declarations: [
    AppComponent,
    ProductComponent,
    NavigationComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    MatTableModule,
    MatToolbarModule,
    MatCardModule,
    MatListModule,
    AppRoutingModule
  ],
  providers: [
    CookieService,
    SecurityService,
    {provide: APP_INITIALIZER, useFactory: initializeApp1, deps: [SecurityService, JWTTokenServiceService], multi: true},
    {provide: HTTP_INTERCEPTORS, useClass: AuthenticationHeaderInterceptor, multi: true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
