import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserStorageService } from 'src/app/auth/services/storage/user-storage.service';

const BASIC_URL: string = 'http://localhost:8080/';

@Injectable({
  providedIn: 'root',
}) 
export class AdminService {
  constructor(
    private http: HttpClient,
    private userStorage: UserStorageService
  ) {}

  postRoomDetails(roomDto: any): Observable<any> {
    return this.http.post<any>(BASIC_URL + 'api/admin/room', roomDto, {
      headers: this.createAuthorizationHeader(),
    });
  }

  getRooms(pageNumber: number): Observable<any> {
    return this.http.get<any>(BASIC_URL + `api/admin/rooms/${pageNumber}`, {
      headers: this.createAuthorizationHeader(),
    });
  }
  
  getReservations(pageNumber: number): Observable<any> {
    return this.http.get<any>(BASIC_URL + `api/admin/reservations/${pageNumber}`, {
      headers: this.createAuthorizationHeader(),
    });
  }
  
  changeReservationStatus(reservationId: number, status: string): Observable<any> {
    return this.http.get<any>(BASIC_URL + `api/admin/reservation/${reservationId}/${status}`, {
      headers: this.createAuthorizationHeader(),
    });
  }

  getRoomById(id: number): Observable<any> {
    return this.http.get<any>(BASIC_URL + `api/admin/room/${id}`, {
      headers: this.createAuthorizationHeader(),
    });
  }
  
  deleteRoom(id: number): Observable<any> {
    return this.http.delete<any>(BASIC_URL + `api/admin/room/${id}`, {
      headers: this.createAuthorizationHeader(),
    });
  }

  updateRoom(id: number, roomDto: any): Observable<any> {
    return this.http.put<any>(BASIC_URL + `api/admin/room/${id}`, roomDto, {
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
