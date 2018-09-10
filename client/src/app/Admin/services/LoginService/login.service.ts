import { Injectable } from '@angular/core';
import {Http, Headers } from '@angular/http';
import {Observable} from 'rxjs';
import {AppConst} from '../../../UserFront/constants/app-const';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http:Http) { }

  sendCredential(username: string, password: string) {
  	let url = AppConst.serverPath + "/admin/token";
  	let encodedCredentials = btoa(username+":"+password);
  	let basicHeader = "Basic "+encodedCredentials;
  	let headers = new Headers ({
  		'Content-Type' : 'application/json',
  		'Authorization' : basicHeader
  	});
    console.log(basicHeader);
  	return this.http.get(url, {headers: headers});

  }

  verifySession(){
    let url = AppConst.serverPath + "/session/verifySession";
    let headers = new Headers({
      'x-auth-token' : localStorage.getItem('xAuthToken')
    });
    return this.http.get(url, {headers:headers});
  }

  verifyCustomerSession(){
    let url = AppConst.serverPath + "/customer/session/verifySession";
    let headers = new Headers({
      'x-auth-token' : localStorage.getItem('xAuthToken')
    });
    return this.http.get(url, {headers:headers});
  }

  getSession(){
    let url = AppConst.serverPath + "/customer/session/getSession"
    let headers = new Headers ({
      'Content-Type' : 'application/json',
    });
    return this.http.get(url, {headers: headers});
  }

  logout(){
    let url = AppConst.serverPath + "/panel/admin/logout";
    let headers = new Headers({
      'x-auth-token' : localStorage.getItem('xAuthToken')
    });
      return this.http.post(url,'', {headers:headers});
  }

}
