import { Component, OnInit } from '@angular/core';
import { User } from '../models/user.model';
import { FormControl, Validators } from '@angular/forms';
import { MyErrorStateMatcher } from '../core/input-error-state-matcher.component';
import { Router } from '@angular/router';
import { UserService } from '../user/user.service';
import { AuthService } from '../core/auth.service';

@Component({
  templateUrl: './register.component.html'
})
export class RegisterComponent {

  user: User = new User();
  addUserFormControl = new FormControl('', [
      Validators.required,
      Validators.email
  ]);

  matcher = new MyErrorStateMatcher();

  constructor(private router: Router, private userService: UserService, private authService: AuthService) { }

  createUser() {
      this.authService.registerUser(this.user)
      .subscribe(
          res => {
              console.log(res);
              // localStorage.setItem('token', JSON.stringify(res.accessToken)),
              // this.router.navigate(['api/users']);
          alert('User created successfully')
          },
          err => console.log(err)
          );
  }
}

