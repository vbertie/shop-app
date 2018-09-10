import { Injectable } from '@angular/core';
import {Http, Headers } from '@angular/http';
import {Observable} from 'rxjs';
import {CartItem} from '../../../Admin/models/cartItem';
import {Cart} from '../../../Admin/models/cart';
import {Product} from '../../../Admin/models/product';
import {HttpClientModule} from '@angular/common/http';
import { URLSearchParams } from "@angular/http";
import { HttpClient,HttpHeaders, HttpParams } from '@angular/common/http';
import {AppConst} from '../../../UserFront/constants/app-const';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  constructor(private http:Http) { }

  createShoppingCart(cartId:string){
    let url = AppConst.serverPath + "/cart";

    let headers = new Headers ({
      'Content-Type': 'application/json',
      'x-auth-token' : localStorage.getItem('xAuthToken')
    });
        return this.http.post(url, cartId, {headers: headers});
  }

  getShoppingCart(cartId:string){
    let url = AppConst.serverPath + "/cart/" +cartId;

    let headers = new Headers ({
      'Content-Type': 'application/json',
      'x-auth-token' : localStorage.getItem('xAuthToken')
    });
        return this.http.get(url, {headers: headers});
  }

  addCartItem(cartItem:CartItem){
    let url = AppConst.serverPath + "/cart/add";

    let headers = new Headers ({
      'Content-Type': 'application/json',
      'x-auth-token' : localStorage.getItem('xAuthToken')
    });
        return this.http.put(url, cartItem, {headers: headers});
  }

  removeCartItem(id:number){
    let url = AppConst.serverPath + "/cart/removeItem/" + id;

    let headers = new Headers ({
      'Content-Type': 'application/json',
      'x-auth-token' : localStorage.getItem('xAuthToken')
    });
        return this.http.delete(url, {headers: headers});
  }

  removeAllCartItems(){
    let url = AppConst.serverPath + "/cart/remove";

    let headers = new Headers ({
      'Content-Type': 'application/json',
      'x-auth-token' : localStorage.getItem('xAuthToken')
    });
        return this.http.delete(url, {headers: headers});
  }
}
