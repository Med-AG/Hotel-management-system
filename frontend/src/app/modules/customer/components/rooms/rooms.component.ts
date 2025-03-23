import { Component, OnInit } from '@angular/core';
import { CustomerService } from '../../service/customer.service';
import { FormGroup } from '@angular/forms';
import { UserStorageService } from 'src/app/auth/services/storage/user-storage.service';

@Component({
  selector: 'app-rooms',
  templateUrl: './rooms.component.html',
  styleUrls: ['./rooms.component.scss'],
})
export class RoomsComponent implements OnInit {
  currentPage: number = 0;
  rooms: any = [];
  total: any;
  success: string;
  endDate: Date;
  startDate: Date;

  isModalOpen: boolean = false;
  selectedRoomId: number | null = null;

  constructor(
    private customerService: CustomerService,
    private userStorage: UserStorageService
  ) {}

  ngOnInit(): void {
    this.getRooms();
  }

  getRooms() {
    this.customerService.getRooms(this.currentPage).subscribe((data) => {
      console.log(data);
      this.rooms = data.roomDtoList;
      this.total = data.totalPages;
    });
  }

  openReservationModal(roomId: number) {
    this.selectedRoomId = roomId;
    this.isModalOpen = true;
  }

  closeReservationModal() {
    this.isModalOpen = false;
    this.startDate = null;
    this.endDate = null;
  }

  onReservationSubmit() {
    const res = {
      userId: this.userStorage.getUserId(),
      roomId: this.selectedRoomId,
      checkInDate: this.startDate,
      checkOutDate: this.endDate,
    };
    console.log(res);
    
    this.customerService.bookRoom(res).subscribe((data) => {
      console.log('reservation submited successfully!');
      this.closeReservationModal();
      //this.getRooms()
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
