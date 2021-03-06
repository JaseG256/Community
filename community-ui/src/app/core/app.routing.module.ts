import { Route, RouterModule, Routes } from '@angular/router';
import { UserComponent } from '../user/user.component';
import { LoginComponent } from '../login/login.component';
import { HomeComponent } from '../home/home.component';
import { UserDetailsComponent } from '../user-details/user-details.component';
import { RegisterComponent } from '../register/register.component';
import { MyProfileComponent } from '../my-profile/my-profile.component';
import { NgModule } from '@angular/core';

const indexRoute: Route = {
    path: '', component: HomeComponent
};
const fallbackRoute: Route = {
    path: '**', component: LoginComponent
};
const routes: Routes = [
    {
        path: 'api/users',
        children: [
            {
                path: '',
                component: UserComponent
            },
            {
                path: 'users/:username',
                component: UserDetailsComponent
            }
        ]
    },
    {
        path: 'api/auth/signup', component: RegisterComponent
    },
    {
        path: 'api/auth/login', component: LoginComponent
    },
    {
        path: 'users/:username', component: UserDetailsComponent
    },
    {
        path: 'api/user/me', component: MyProfileComponent
    },
    indexRoute,
    fallbackRoute
];

@NgModule({
    imports: [
        RouterModule.forRoot(
            routes,
        // {enableTracing: true}
    )
    ],
    exports: [
        RouterModule
    ],
    declarations: []
})
export class AppRoutingModule { }