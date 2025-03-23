import { Injectable } from '@angular/core';

const TOKEN = 'token';
const USER = 'user';

@Injectable({
  providedIn: 'root', // Provided in the root injector
})
export class UserStorageService {
  constructor() {}

  // Save token to localStorage
  saveToken(token: string): void {
    localStorage.removeItem(TOKEN);
    localStorage.setItem(TOKEN, token);
  }

  // Save user to localStorage
  saveUser(user: any): void {
    localStorage.removeItem(USER);
    localStorage.setItem(USER, JSON.stringify(user));
  }

  // Get token from localStorage
  getToken(): string | null {
    return localStorage.getItem(TOKEN);
  }

  // Get user from localStorage
  getUser(): any {
    const user = localStorage.getItem(USER);
    return user ? JSON.parse(user) : null;
  }

  // Get user ID from localStorage
  getUserId(): string {
    const user = this.getUser();
    return user?.id || '';
  }

  // Get user role from localStorage
  getUserRole(): string {
    const user = this.getUser();
    return user?.role || '';
  }

  // Check if admin is logged in
  isAdminLoggedIn(): boolean {
    const token = this.getToken();
    const role = this.getUserRole();
    return !!token && role === 'ADMIN';
  }

  // Check if customer is logged in
  isCustomerLoggedIn(): boolean {
    const token = this.getToken();
    const role = this.getUserRole();
    return !!token && role === 'CUSTOMER';
  }

  // Clear localStorage (sign out)
  signOut(): void {
    localStorage.removeItem(TOKEN);
    localStorage.removeItem(USER);
  }
}
