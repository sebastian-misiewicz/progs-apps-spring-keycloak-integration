import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {IToken} from "../interface/itoken";
import {JWTTokenServiceService} from "./jwttoken-service.service";
import {lastValueFrom} from "rxjs";
import {IKeycloakConfiguration} from "../interface/ikeycloakconfiguraiton";

@Injectable({
  providedIn: 'root'
})
export class SecurityService {

  public baseUrl = "http://localhost:8080/security/";

  constructor(private httpClient: HttpClient,
              private jwtTokenService: JWTTokenServiceService) {
  }

  public async assignToken(authenticationCode: String) {
    const accessToken: string = (await this.getToken(authenticationCode)).access_token;
    this.jwtTokenService.setToken(accessToken);
  }

  async getToken(authenticationCode: String): Promise<IToken> {
    return await lastValueFrom(this.httpClient.post<IToken>(this.baseUrl + 'token',
      JSON.stringify({
        'code': authenticationCode
      }), {
        headers: {
          "Content-Type": 'application/json',
        }
      }));
  }

  public async goToAuth() {
    window.location.href = (await this.getAuthUrl());
  }

  private async getAuthUrl() {
    const redirectUri = window.location.href.split('?')[0];
    const keycloakConfiguration: IKeycloakConfiguration = (await lastValueFrom(this.httpClient.get<IKeycloakConfiguration>(this.baseUrl + "configuration")));
    return keycloakConfiguration.issuerUri + '/protocol/openid-connect/auth?client_id=' + keycloakConfiguration.clientId + '&response_type=code&redirect_uri=' + redirectUri;
  }


}
