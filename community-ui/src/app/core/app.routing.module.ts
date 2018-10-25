import { Route } from '@angular/router';
import { UserComponent } from '../user/user.component';
import { LoginComponent } from '../login/login.component';

const indexRoute: Route = { 
    path: '', component: UserComponent
};
const fallbackRoute: Route = {
    path: '**', component: LoginComponent
};