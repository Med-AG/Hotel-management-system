import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserStorageService } from './auth/services/storage/user-storage.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent implements OnInit {
  title = 'HotelWeb';

  constructor(
    private router: Router,
    private userStorage: UserStorageService
  ) {}

  isCustomerLoggedIn: boolean = this.userStorage.isCustomerLoggedIn();
  isAdminLoggedIn: boolean = this.userStorage.isAdminLoggedIn();

  ngOnInit(): void {
    this.router.events.subscribe((event) => {
      if (event.constructor.name === 'NavigationEnd') {
        this.isCustomerLoggedIn = this.userStorage.isCustomerLoggedIn();
        this.isAdminLoggedIn = this.userStorage.isAdminLoggedIn();
      }
    });
  }

  logout() {
    this.userStorage.signOut();
    this.router.navigateByUrl('/login');
  }
}
