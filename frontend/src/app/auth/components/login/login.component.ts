import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth/auth.service';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserStorageService } from '../../services/storage/user-storage.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnInit {
  loginForm!: FormGroup;

  constructor(
    private authService: AuthService,
    private router: Router,
    private fb: FormBuilder,
    private userStorage: UserStorageService
  ) {}

  ngOnInit(): void {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
    });
  }

  onSubmit() {
    if (this.loginForm.valid) {
      //console.log('Form Submitted', this.loginForm.value);
      this.authService.login(this.loginForm.value).subscribe((data) => {
        if (data.userId != null) {
          const user = {
            id: data.userId,
            role: data.userRole,
          };
          this.userStorage.saveUser(user);
          this.userStorage.saveToken(data.jwt);

          if (this.userStorage.isAdminLoggedIn()) {
            this.router.navigateByUrl('/admin/dashboard');
          } else if (this.userStorage.isCustomerLoggedIn())
            this.router.navigateByUrl('/customer/rooms');
        }
        //this.router.navigate(['/']);
      });
    }
  }
}
