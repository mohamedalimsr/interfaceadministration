import { Component, OnInit } from '@angular/core';
import { UserService } from '../_services/user.service';

@Component({
  selector: 'app-board-server',
  templateUrl: './board-server.component.html',
  styleUrls: ['./board-server.component.css']
})
export class BoardServerComponent implements OnInit {

  content = '';

  constructor(private userService: UserService) { }

  ngOnInit() {
    this.userService.getServerBoard().subscribe(
      data => {
        this.content = data;
      },
      err => {
        this.content = JSON.parse(err.error).message;
      }
    );
  }

}
