import { Injectable } from '@angular/core';
import {Http, Headers } from '@angular/http';
import {Observable} from 'rxjs';
import { Category } from '../../models/category';
import {AppConst} from '../../../UserFront/constants/app-const';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  constructor(private http:Http) { }

  getCategories(){
    let url = AppConst.serverPath + "/category";
    let headers = new Headers({
      'x-auth-token' : localStorage.getItem('xAuthToken')
    });
    return this.http.get(url, {headers:headers});
  }

  addCategory(category:Category){
      let url = AppConst.serverPath + "/category";
      let headers = new Headers({
        'x-auth-token' : localStorage.getItem('xAuthToken')
      });
      return this.http.post(url, category, {headers:headers});
  }

  removeCategory(id:number){
      let url = AppConst.serverPath + "/category/" + id;
      let headers = new Headers({
        'x-auth-token' : localStorage.getItem('xAuthToken')
      });
      return this.http.delete(url , {headers:headers});
  }

  getCategory(id:number){
      let url = AppConst.serverPath + "/category/" + id;
      let headers = new Headers({
        'x-auth-token' : localStorage.getItem('xAuthToken')
      });
      return this.http.get(url , {headers:headers});
  }

  updateCategory(category:Category){
      let url = AppConst.serverPath + "/category";
      let headers = new Headers({
        'x-auth-token' : localStorage.getItem('xAuthToken')
      });
      return this.http.put(url , category,{headers:headers});
  }

}
