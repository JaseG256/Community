import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";

@Injectable()
export class AuthService {

    private signupUrl = 'http://localhost:8080/api/auth/signup';
    private loginUrl = 'http://localhost:8080/api/auth/login';

    constructor(private http: HttpClient) { }

    registerUser(user) {
        return this.http.post<any>(this.signupUrl, user);
    }

    attemptAuth(username: string, password: string): Observable<any> {
        const credentials = { username: username, password: password };
        return this.http.post<any>(this.loginUrl, credentials);
    }
}
