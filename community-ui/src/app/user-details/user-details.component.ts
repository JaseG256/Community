import { Component, OnInit, Input } from '@angular/core';
import { User } from '../models/user.model';
import { UserService } from '../user/user.service';

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css']
})
export class UserDetailsComponent {

  @Input() user: User;

}
