import { Component, OnInit } from '@angular/core';
import {Params, Router,ActivatedRoute, NavigationExtras } from '@angular/router';
import {PromotionService} from '../../../../../Admin/services/PromotionService/promotion.service';
import {AppConst} from '../../../../../UserFront/constants/app-const';
import {Promotion} from '../../../../../Admin/models/promotion';
import {Product} from '../../../../../Admin/models/product';
import {ProductService} from '../../../../../Admin/services/ProductService/product.service'
@Component({
  selector: 'app-add-promotion-to-products',
  templateUrl: './add-promotion-to-products.component.html',
  styleUrls: ['./add-promotion-to-products.component.css']
})
export class AddPromotionToProductsComponent implements OnInit {

  private promotionId:number;
  private promotion:Promotion = new Promotion();
  private avaibleProducts:Product[];
  private productsToAddPromotion:Product[];

  private checked:boolean;
  private allChecked:boolean;

    constructor(private router: Router,
    private activatedRoute:ActivatedRoute,
    private promotionService:PromotionService,
    private productService:ProductService) { }

  ngOnInit() {
    this.productsToAddPromotion = [];
    this.avaibleProducts = [];

    this.activatedRoute.params.forEach((params: Params) => {
    this.promotionId = Number.parseInt(params['id']);
  })
  this.promotionService.getPromotion(this.promotionId).subscribe(
    res=>{
      this.promotion = res.json();
    },
    err=>{
      console.log(err);
    }
  )

  this.productService.getProductList().subscribe(
    res=>{
      for(let i of res.json()){
        if(i.promotionId == null){
        this.avaibleProducts.push(i);
      }
      }
    },
    err=>{
      console.log(err);
    }
  )
}

  addToPromotionProductList(checked:boolean, product:Product){
    if (checked){
      this.productsToAddPromotion.push(product);
    } else {
      this.productsToAddPromotion.splice(this.productsToAddPromotion.indexOf(product), 1);
    }
  }

  addSelected(){
    this.promotion.products = this.productsToAddPromotion;

    this.promotionService.addProductPromotion(this.promotion).subscribe(
      res=>{
        console.log(res)
      //  location.reload();
        this.router.navigate(["/admin/viewPromotion/" + this.promotion.id]);
      },
      err=>{
        console.log(err)
      }
    )
  }
}
