import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
import { Observable } from 'rxjs';
import { Promotion } from '../../models/promotion';
import { AppConst } from '../../../UserFront/constants/app-const';

@Injectable({
  providedIn: 'root'
})
export class PromotionService {

  constructor(private http: Http) { }

  addPromotion(promotion: Promotion) {
    let url = AppConst.serverPath + "/promotion";
    let headers = new Headers({
      'x-auth-token': localStorage.getItem('xAuthToken')
    });
    return this.http.post(url, promotion, { headers: headers });
  }

  getPromotions() {
    let url = AppConst.serverPath + "/promotion";
    let headers = new Headers({
      'x-auth-token': localStorage.getItem('xAuthToken')
    });
    return this.http.get(url, { headers: headers });
  }

  removePromotion(id: number) {
    let url = AppConst.serverPath + "/promotion/" + id;
    let headers = new Headers({
      'x-auth-token': localStorage.getItem('xAuthToken')
    });
    return this.http.delete(url, { headers: headers });
  }

  getPromotion(id:number) {
    let url = AppConst.serverPath + "/promotion/" + id;
    let headers = new Headers({
      'x-auth-token': localStorage.getItem('xAuthToken')
    });
    return this.http.get(url, { headers: headers });
  }

  addProductPromotion(promotion:Promotion) {
    let url = AppConst.serverPath + "/promotion/addProductPromotion";
    let headers = new Headers({
      'x-auth-token': localStorage.getItem('xAuthToken')
    });
    return this.http.post(url, promotion, { headers: headers });
  }

  deleteProductsFromPromotion(promotion:Promotion){
    let url = AppConst.serverPath + "/promotion/deleteProductPromotion";
    let headers = new Headers({
      'x-auth-token': localStorage.getItem('xAuthToken')
    });
    return this.http.put(url, promotion, { headers: headers });
  }
}
