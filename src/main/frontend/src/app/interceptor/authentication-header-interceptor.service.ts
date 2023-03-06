import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs';
import {JWTTokenServiceService} from "../service/jwttoken-service.service";
import {SecurityService} from "../service/security.service";

@Injectable()
export class AuthenticationHeaderInterceptor implements HttpInterceptor {

  constructor(private jwtTokenService: JWTTokenServiceService, private securityService:SecurityService) {
  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if (request.url == "http://localhost:8081/security/token" || request.url == "http://localhost:8081/security/configuration") {
      return next.handle(request);
    }

    if (this.jwtTokenService.getToken()) {
      return next.handle(request.clone({
        headers: request.headers.set('Authorization', `Bearer ${this.jwtTokenService.getToken()}`),
      }));
    } else {
      this.securityService.goToAuth();
    }
    return next.handle(request);
  }
}
