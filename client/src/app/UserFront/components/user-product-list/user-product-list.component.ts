import { Component, OnInit } from '@angular/core';
import {Params, ActivatedRoute, Router, NavigationExtras} from '@angular/router';
import {ProductService} from '../../../Admin/services/ProductService/product.service';
import {LoginService} from '../../../Admin/services/LoginService/login.service';
import {Product} from '../../../Admin/models/product';
import {AppConst} from '../../../UserFront/constants/app-const';

@Component({
  selector: 'app-user-product-list',
  templateUrl: './user-product-list.component.html',
  styleUrls: ['./user-product-list.component.css']
})
export class UserProductListComponent implements OnInit {

  constructor(private route:ActivatedRoute,
              private productService:ProductService,
              private loginService:LoginService
            , private router:Router ) { }

  private category:string;
  private productList:Product[];
  private product:Product;
  private serverPath:string;

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
          this.productList = JSON.parse(params['productList']);
      });
      this.serverPath = AppConst.serverPath;
  }

  onSelect(product:Product){
    let navigationExtras:NavigationExtras = {
      queryParams: {
        "product" : JSON.stringify(product)
      }
    };

      this.router.navigate(['/user/product/details'], navigationExtras);
}
}
