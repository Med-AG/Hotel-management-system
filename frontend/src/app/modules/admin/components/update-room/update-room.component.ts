import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AdminService } from '../../admin-services/admin.service';

@Component({
  selector: 'app-update-room',
  templateUrl: './update-room.component.html',
  styleUrls: ['./update-room.component.scss'],
})
export class UpdateRoomComponent {
  updateRoomForm: FormGroup;
  id: number = this.activatedRoute.snapshot.params['id'];

  constructor(
    private router: Router,
    private fb: FormBuilder,
    private adminService: AdminService,
    private activatedRoute: ActivatedRoute
  ) {
    this.updateRoomForm = this.fb.group({
      name: ['', Validators.required],
      type: ['', Validators.required],
      price: ['', Validators.required],
    });
  }

  ngOnInit(): void {
    this.getRoomById();
  }

  getRoomById() {
    this.adminService.getRoomById(this.id).subscribe((res) => {
      console.log(res);
      this.updateRoomForm.patchValue(res);
    });
  }

  onSubmit() {
    if (this.updateRoomForm.valid) {
      const roomDto = this.updateRoomForm.value;
      this.adminService.updateRoom(this.id, roomDto).subscribe((res) => {
        this.router.navigateByUrl('/admin/dashboard');
      });
    }
  }
}
