import { Component, OnInit } from '@angular/core';
import { Router, Params, ActivatedRoute, NavigationExtras } from '@angular/router';
import {ProductService} from '../../../Admin/services/ProductService/product.service';
import {LoginService} from '../../../Admin/services/LoginService/login.service';
import {Product} from '../../../Admin/models/product';
import {Category} from '../../../Admin/models/category';
import {AppConst} from '../../../UserFront/constants/app-const';
import {CategoryService} from '../../../Admin/services/CategoryService/category.service';

@Component({
  selector: 'app-product-category',
  templateUrl: './product-category.component.html',
  styleUrls: ['./product-category.component.css']
})
export class ProductCategoryComponent implements OnInit {

  private category:string;
  private productList:Product[];
  private serverPath:string;
  private categories:Category[];

  constructor(private route:ActivatedRoute,
              private productService:ProductService,
              private router:Router,
              private categoryService:CategoryService) { }

  ngOnInit() {
    this.route.params.forEach((params: Params) => {
    this.category = params['category'];
    });

    this.productService.getProductsWithCategory(this.category).subscribe(
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
