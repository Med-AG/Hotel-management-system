import { Component, OnInit } from '@angular/core';
import { AdminService } from '../../admin-services/admin.service';

@Component({
  selector: 'app-reservations',
  templateUrl: './reservations.component.html',
  styleUrls: ['./reservations.component.scss'],
})
export class ReservationsComponent implements OnInit {
  currentPage: number = 0;
  reservations: any = [];
  total: any;

  constructor(private adminService: AdminService) {}

  ngOnInit(): void {
    this.getReservations();
  }

  getReservations() {
    this.adminService.getReservations(this.currentPage).subscribe((res) => {
      console.log(res);
      this.reservations = res.reservationDtolList;
      this.total = res.totalPages;
    });
  }

  changeReservationStatus(reservationId: number, status: string) {
    this.adminService
      .changeReservationStatus(reservationId, status)
      .subscribe(() => {
        console.log('status changed successfully');
        this.getReservations();
      });
  }

  pageIndexChange(value: number) {
    this.currentPage = value;
    this.getReservations();
  }

  getPageNumbers(): number[] {
    const pages: number[] = [];
    for (let i = 1; i <= this.total; i++) {
      pages.push(i);
    }
    return pages;
  }
}
