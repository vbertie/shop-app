import { Injectable } from '@angular/core';
import {Http, Headers } from '@angular/http';
import {Observable} from 'rxjs';
import {User} from '../../../Admin/models/user';
import {AppConst} from '../../../UserFront/constants/app-const';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http:Http) { }

  createAccount(user:User){
      let url = AppConst.serverPath + "/user/add";
      let headers = new Headers({
        'x-auth-token' : localStorage.getItem('xAuthToken')
      });
      return this.http.post(url,user, {headers:headers});
    }

    passwordRecovery(email:String){
    let url = AppConst.serverPath + "/user/passwordRecovery";
    let headers = new Headers({
      'x-auth-token' : localStorage.getItem('xAuthToken')
    });

    return this.http.post(url, JSON.stringify(email), {headers:headers});
    }
  }
