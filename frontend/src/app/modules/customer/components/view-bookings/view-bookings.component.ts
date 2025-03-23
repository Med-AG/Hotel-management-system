import { Component, OnInit } from '@angular/core';
import { CustomerService } from '../../service/customer.service';

@Component({
  selector: 'app-view-bookings',
  templateUrl: './view-bookings.component.html',
  styleUrls: ['./view-bookings.component.scss'],
})
export class ViewBookingsComponent implements OnInit {
  currentPage: number = 0;
  bookings: any = [];
  total: any;

  constructor(private customerService: CustomerService) {}

  ngOnInit(): void {
    this.getBookings();
  }

  getBookings() {
    this.customerService.getMyBookings(this.currentPage).subscribe((res) => {
      console.log(res);
      this.bookings = res.reservationDtolList;
      this.total = res.totalPages;
    });
  }

  pageIndexChange(value: number) {
    this.currentPage = value;
    this.getBookings();
  }

  getPageNumbers(): number[] {
    const pages: number[] = [];
    for (let i = 1; i <= this.total; i++) {
      pages.push(i);
    }
    return pages;
  }
}
