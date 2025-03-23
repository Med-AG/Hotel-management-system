import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserStorageService } from 'src/app/auth/services/storage/user-storage.service';

const BASIC_URL: string = 'http://localhost:8080/';

@Injectable({
  providedIn: 'root',
})
export class CustomerService {
  constructor(
    private http: HttpClient,
    private userStorage: UserStorageService
  ) {}

  getRooms(pageNumber: number): Observable<any> {
    return this.http.get<any>(BASIC_URL + `api/customer/rooms/${pageNumber}`, {
      headers: this.createAuthorizationHeader(),
    });
  }

  getMyBookings(pageNumber: number): Observable<any> {
    const userId = this.userStorage.getUserId();
    return this.http.get<any>(BASIC_URL + `api/customer/bookings/${userId}/${pageNumber}`, {
      headers: this.createAuthorizationHeader(),
    });
  }

  bookRoom(bookingDto: any): Observable<any> {
    return this.http.post<any>(BASIC_URL + `api/customer/book`, bookingDto, {
      headers: this.createAuthorizationHeader(),
    });
  }

  createAuthorizationHeader() {
    let authHeaders: HttpHeaders = new HttpHeaders();
    return authHeaders.set(
      'Authorization',
      'Bearer ' + this.userStorage.getToken()
    );
  }
}
