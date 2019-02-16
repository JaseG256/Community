import { HttpInterceptor, HttpRequest, HttpHandler, HttpSentEvent, HttpHeaderResponse, HttpProgressEvent, HttpResponse, HttpUserEvent } from "@angular/common/http";
import { Router } from "@angular/router";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";

const TOKEN_HEADER_KEY = 'Authorization';

@Injectable()
export class Interceptor implements HttpInterceptor {

    constructor(private router: Router) { }

    intercept(req: HttpRequest<any>, next: HttpHandler):
     Observable<HttpSentEvent | HttpHeaderResponse | HttpProgressEvent | HttpResponse<any> | HttpUserEvent<any>> {
        console.log(localStorage.getItem('token'));
        let authReq = req;

        if (localStorage.getItem('token')) {
            authReq = req.clone({
                headers: req.headers.set(TOKEN_HEADER_KEY, 'Bearer ' + localStorage
                .getItem('token').substring(1, localStorage.getItem('token').length - 1))
            });
            console.log('See token');
            console.log(req.headers);
            console.log(req.body);
            console.log(req);
            console.log(req.params);
            console.log(req.reportProgress);
            console.log(authReq);
            return next.handle(authReq);
        } else {
            console.log('No token!');
            return next.handle(req);
        }
    }
}