import { Component, OnInit, AfterViewInit, ViewChild } from '@angular/core';
import { MatTableDataSource, MatPaginator, MatSort } from '@angular/material';
import { User } from '../models/user.model';
import { UserService } from './user.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit, AfterViewInit {

  displayedColumns = ['id', 'username', 'email'];
  dataSource = new MatTableDataSource<User>();
  users: User[];
  selectedUser: User;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private userService: UserService) { }

  ngOnInit() {
    this.userService.getUsers()
    .subscribe(data => {
      this.users = data;
    });
    this.userService.getUsers()
    .subscribe(data => {
      this.dataSource.data = data;
    });
  }

  onSelect(user: User) {
    this.selectedUser = user;
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  showDetails(row: any) { }

  deleteUser(user: User) {
    this.userService.deleteUser(user)
    .subscribe(data => {
      this.users = this.users.filter(u => u !== user);
    });
  }

}
