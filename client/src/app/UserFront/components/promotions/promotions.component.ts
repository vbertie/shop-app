import { Component, OnInit } from '@angular/core';
import { Router, Params, ActivatedRoute, NavigationExtras } from '@angular/router';
import {ProductService} from '../../../Admin/services/ProductService/product.service';
import {Product} from '../../../Admin/models/product';
import {Category} from '../../../Admin/models/category';
import {AppConst} from '../../../UserFront/constants/app-const';
import {CategoryService} from '../../../Admin/services/CategoryService/category.service';

@Component({
  selector: 'app-promotions',
  templateUrl: './promotions.component.html',
  styleUrls: ['./promotions.component.css']
})
export class PromotionsComponent implements OnInit {

  constructor(private route:ActivatedRoute,
              private productService:ProductService,
              private router:Router) { }

  private category:string;
  private productList:Product[];
  private serverPath:string;

  ngOnInit() {
    this.route.params.forEach((params: Params) => {
    this.category = params['category'];
    });


    this.productService.getProductsAtPromotionWithCategory(this.category).subscribe(
      res =>{
        this.productList = res.json();
      },
      err => {
        console.log(err);
      }
    );
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
