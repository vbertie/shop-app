import { Injectable } from '@angular/core';
import {Http, Headers } from '@angular/http';
import { Product } from '../../models/product';
import {Observable} from 'rxjs';
import {AppConst} from '../../../UserFront/constants/app-const';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http:Http) { }

  removeProduct(productId:number){
    let url = AppConst.serverPath + "/product/remove/" + productId;
    let headers = new Headers ({
      'Content-Type': 'application/json',
    });
      return this.http.delete(url, {headers: headers});
  }

  getProductList(){
    let url = AppConst.serverPath + "/product/getAll";
    let headers = new Headers ({
      'Content-Type': 'application/json',
    });
        return this.http.get(url, {headers: headers});
  }

  createdProduct(product:Product){
    let url = AppConst.serverPath + "/product/add";
    let headers = new Headers({
      'Content-Type' : 'application/json',
    });
      return this.http.post(url, product, {headers:headers});
  }

  showProduct(productId:number){
        let url = AppConst.serverPath + "/product/" + productId;
    let headers = new Headers({
      'Content-Type' : 'application/json',
    });
      return this.http.get(url, {headers:headers});
  }

  getProductsWithCategory(category:string){
    let url = AppConst.serverPath + "/product/category/" + category;

    let headers = new Headers({
      'Content-Type' : 'application/json',
    });
      return this.http.get(url, {headers:headers});
  }

  searchByName(key:string){
    let url = AppConst.serverPath + "/product/search/?name="+ key;
    let headers = new Headers({
      'Content-Type' : 'application/json',
    });
      return this.http.get(url, {headers:headers});
  }

  getProductsAtPromotionWithCategory(category:string){
    let url = AppConst.serverPath + "/product/promotion/" + category;

    let headers = new Headers({
      'Content-Type' : 'application/json',
    });
      return this.http.get(url, {headers:headers});
    }

}
