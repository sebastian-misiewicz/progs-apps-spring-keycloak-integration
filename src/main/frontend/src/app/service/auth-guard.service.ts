import { Injectable } from '@angular/core';
import {Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree} from '@angular/router';
import {Observable} from "rxjs";
import {JWTTokenServiceService} from "./jwttoken-service.service";
import {SecurityService} from "./security.service";

@Injectable({
  providedIn: 'root'
})
export class AuthGuardService implements CanActivate {

  constructor(public jwtTokenService: JWTTokenServiceService,
              private router: Router,
              private securityService: SecurityService) { }

  async canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    if (this.jwtTokenService.getUser() && this.jwtTokenService.isTokenExpired()) {
      this.securityService.goToAuth();
      return false;
    } else {
      return true;
    }
  }


}
