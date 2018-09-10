import { Component, OnInit } from '@angular/core';
import {Params, Router,ActivatedRoute, NavigationExtras } from '@angular/router';
import {PromotionService} from '../../../../../Admin/services/PromotionService/promotion.service';
import {AppConst} from '../../../../../UserFront/constants/app-const';
import {Promotion} from '../../../../../Admin/models/promotion';
import {Product} from '../../../../../Admin/models/product';
import {ProductService} from '../../../../../Admin/services/ProductService/product.service'

@Component({
  selector: 'app-delete-promotion-from-products',
  templateUrl: './delete-promotion-from-products.component.html',
  styleUrls: ['./delete-promotion-from-products.component.css']
})
export class DeletePromotionFromProductsComponent implements OnInit {

  private promotionId:number;
  private promotion:Promotion = new Promotion();
  private productsInPromotion:Product[];
  private productsToDeleteFromPromotion:Product[];

  private checked:boolean;
  private allChecked:boolean;

    constructor(private router: Router,
    private activatedRoute:ActivatedRoute,
    private promotionService:PromotionService,
    private productService:ProductService) { }

  ngOnInit() {
    this.productsToDeleteFromPromotion = [];
    this.productsInPromotion = [];

    this.activatedRoute.params.forEach((params: Params) => {
    this.promotionId = Number.parseInt(params['id']);
  })
  this.promotionService.getPromotion(this.promotionId).subscribe(
    res=>{
      this.promotion = res.json();
      for(let i of this.promotion.products){
        this.productsInPromotion.push(i);
      }
    },
    err=>{
      console.log(err);
    }
  )
  }

  deletePromotionFromProduct(checked:boolean, product:Product){
    if (checked){
      this.productsToDeleteFromPromotion.push(product);
    } else {
      this.productsToDeleteFromPromotion.splice(this.productsToDeleteFromPromotion.indexOf(product), 1);
    }
  }

  deleteSelected(){
    this.promotion.products = this.productsToDeleteFromPromotion;

    this.promotionService.deleteProductsFromPromotion(this.promotion).subscribe(
      res=>{
        console.log(res)
        location.reload();
        this.router.navigate(["/admin/listPromotions/" + this.promotion.id]);
      },
      err=>{
        console.log(err)
      }
    )
  }
  }
