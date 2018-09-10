import { Injectable } from '@angular/core';
import {Http, Headers } from '@angular/http';
import {Observable} from 'rxjs';
import {CartItem} from '../../../Admin/models/cartItem';
import {Cart} from '../../../Admin/models/cart';
import {Product} from '../../../Admin/models/product';
import {HttpClientModule} from '@angular/common/http';
import { URLSearchParams } from "@angular/http";
import { HttpClient,HttpHeaders, HttpParams } from '@angular/common/http';
import {Order} from '../../../Admin/models/order';
import {AppConst} from '../../../UserFront/constants/app-const';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(private http:Http) { }

  processOrder(order:Order){
    let url = AppConst.serverPath + "/order/process";

    let headers = new Headers ({
      'Content-Type': 'application/json',
      'x-auth-token' : localStorage.getItem('xAuthToken')
    });
        return this.http.post(url, order, {headers: headers});
  }

  getAllOrders(){
    let url = AppConst.serverPath + "/order/getAll";

    let headers = new Headers ({
      'Content-Type': 'application/json',
      'x-auth-token' : localStorage.getItem('xAuthToken')
    });
        return this.http.get(url, {headers: headers});
  }

  getOrder(id:number){
    let url = AppConst.serverPath + "/order/" + id;

    let headers = new Headers ({
      'Content-Type': 'application/json',
      'x-auth-token' : localStorage.getItem('xAuthToken')
    });
        return this.http.get(url, {headers: headers});
  }

  removeOrder(id:number){
    let url = AppConst.serverPath + "/order/remove/" + id;

    let headers = new Headers ({
      'Content-Type': 'application/json',
      'x-auth-token' : localStorage.getItem('xAuthToken')
    });
        return this.http.delete(url, {headers: headers});
  }

  editOrder(order:Order){
    let url = AppConst.serverPath + "/order/edit";

    let headers = new Headers ({
      'Content-Type': 'application/json',
      'x-auth-token' : localStorage.getItem('xAuthToken')
    });
        return this.http.put(url,order, {headers: headers});
  }

  sendEmail(order:Order){
    let url = AppConst.serverPath + "/mail";

    let headers = new Headers ({
      'Content-Type': 'application/json',
      'x-auth-token' : localStorage.getItem('xAuthToken')
    });
        return this.http.post(url,order, {headers: headers});
  }
}
