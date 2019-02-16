import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { UserComponent } from './user/user.component';
import { CustomMaterialModule } from './core/material.module';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations'
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { UserService } from './user/user.service';
import { HomeComponent } from './home/home.component';
import { UserDetailsComponent } from './user-details/user-details.component';
import { MyProfileComponent } from './my-profile/my-profile.component';
import { AppRoutingModule } from './app-routing.module';
import { AuthService } from './core/auth.service';
import { Interceptor } from './core/interceptor';
import { UploadImageComponent } from './upload-image/upload-image.component';

@NgModule({
  declarations: [
    AppComponent,
    RegisterComponent,
    LoginComponent,
    UserComponent,
    HomeComponent,
    UserDetailsComponent,
    MyProfileComponent,
    UploadImageComponent
  ],
  imports: [
    BrowserModule,
    CustomMaterialModule,
    HttpClientModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule,
    
  ],
  providers: [UserService,AuthService,
  {
     provide: HTTP_INTERCEPTORS,
    useClass: Interceptor,
    multi: true
  }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
