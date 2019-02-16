import { Component, OnInit } from '@angular/core';
import { User } from '../models/user.model';
import { UserService } from '../user/user.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  users: User[];
  selectedUser: User;

  constructor(private userService: UserService) { }

  ngOnInit() {
    this.userService.getUsers()
    .subscribe(data => { 
      this.users = data;
      }
    );
  }

  onSelect(user: User): void {
    this.selectedUser = user;
  }

}
