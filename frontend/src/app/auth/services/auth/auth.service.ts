import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

const basicUrl: string = 'http://localhost:8080/';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(private http: HttpClient) {}

  register(signupRequest: any) {
    return this.http.post(basicUrl + 'api/auth/signup', signupRequest);
  }

  login(loginRequest: any): Observable<any> {
    return this.http.post<any>(basicUrl + 'api/auth/login', loginRequest);
  }

}
