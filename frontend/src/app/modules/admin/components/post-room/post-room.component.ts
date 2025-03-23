import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AdminService } from '../../admin-services/admin.service';

@Component({
  selector: 'app-post-room',
  templateUrl: './post-room.component.html',
  styleUrls: ['./post-room.component.scss'],
})
export class PostRoomComponent implements OnInit {
  roomDetailsForm: FormGroup;
  successMessage: string | null = null;
  errorMessage: string | null = null;

  constructor(
    private router: Router,
    private fb: FormBuilder,
    private adminService: AdminService
  ) {}

  ngOnInit(): void {
    this.roomDetailsForm = this.fb.group({
      name: ['', Validators.required],
      type: ['', Validators.required],
      price: ['', Validators.required],
    });
  }

  onSubmit() {
    if (this.roomDetailsForm.valid) {
      this.adminService.postRoomDetails(this.roomDetailsForm.value).subscribe(
        (res) => {
          this.successMessage = 'Room added successfully!';
          setTimeout(() => {
            this.successMessage = null;
            this.router.navigateByUrl('/admin/dashboard');
          }, 2000);
        },
        (error) => {
          this.errorMessage = 'Failed to add room. Please try again.';
          setTimeout(() => {
            this.errorMessage = null;
          }, 3000);
        }
      );
    }
  }
}
