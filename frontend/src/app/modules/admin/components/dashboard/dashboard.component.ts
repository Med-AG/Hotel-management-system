import { Component, OnInit } from '@angular/core';
import { AdminService } from '../../admin-services/admin.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss'],
})
export class DashboardComponent implements OnInit {
  currentPage: number = 0;
  rooms: any = [];
  total: any;
  success: string;

  constructor(private adminService: AdminService) {}

  ngOnInit(): void {
    this.getRooms();
  }

  getRooms() {
    this.adminService.getRooms(this.currentPage).subscribe((data) => {
      console.log(data);
      this.rooms = data.roomDtoList;
      this.total = data.totalPages;
    });
  }

  confirmDelete(roomId: number) {
    const isConfirmed = confirm('Are you sure you want to delete this room?');
    if (isConfirmed) {
      this.deleteRoom(roomId);
    }
  }

  deleteRoom(roomId: number) {
    this.adminService.deleteRoom(roomId).subscribe(() => {
      this.success = 'Room deleted successfully';
      this.getRooms();
    });
  }

  pageIndexChange(value: number) {
    this.currentPage = value;
    this.getRooms();
  }

  getPageNumbers(): number[] {
    const pages: number[] = [];
    for (let i = 1; i <= this.total; i++) {
      pages.push(i);
    }
    return pages;
  }
}
