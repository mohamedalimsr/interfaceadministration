import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from './_services/token-storage.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'ADMINISTRATION-SOLUTIONS-IOT-FRONT';
  private roles: string[];
  isLoggedIn = false;
  showAdminBoard = false;
  showServerBoard = false;
  showWebBoard = false;
  username: string;

  constructor(private tokenStorageService: TokenStorageService) { }

  ngOnInit() {
    this.isLoggedIn = !!this.tokenStorageService.getToken();
    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
      this.roles = user.roles;
      this.showAdminBoard = this.roles.includes('ROLE_ADMIN');
      this.showServerBoard = this.roles.includes('ROLE_SERVER');
      this.showWebBoard = this.roles.includes('ROLE_WEB');
      this.username = user.username;
    }
  }
  logout() {
    this.tokenStorageService.signOut();
    window.location.reload();
  }

}
