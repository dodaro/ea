import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Inject, Injectable, Optional }                      from '@angular/core';
import { Observable }                                        from 'rxjs';

@Injectable()
export class KeycloakService {
  private accessTokenUrl = 'http://localhost:8181/realms/UniEa/protocol/openid-connect/token';

  constructor(private http: HttpClient) { }

  generateToken(username: string, password: string): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/x-www-form-urlencoded'
    });

    const body = new URLSearchParams();
    body.set('grant_type', 'password');
    body.set('client_id', 'uniEa-client');
    body.set('username', username);
    body.set('password', password);

    return this.http.post(this.accessTokenUrl, body.toString(), { headers });
  }
}
