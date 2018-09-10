import { Component, OnInit } from '@angular/core';
import {Params, Router,ActivatedRoute, NavigationExtras } from '@angular/router';
import {PromotionService} from '../../../../../Admin/services/PromotionService/promotion.service';
import {AppConst} from '../../../../../UserFront/constants/app-const';
import {Promotion} from '../../../../../Admin/models/promotion';
import {Product} from '../../../../../Admin/models/product';
import {ProductService} from '../../../../../Admin/services/ProductService/product.service'
@Component({
  selector: 'app-add-promotion-by-category',
  templateUrl: './add-promotion-by-category.component.html',
  styleUrls: ['./add-promotion-by-category.component.css']
})
export class AddPromotionByCategoryComponent implements OnInit {

  private promotionId:number;
  private promotion:Promotion;

    constructor(private router: Router,
    private activatedRoute:ActivatedRoute,
    private promotionService:PromotionService,
    private productService:ProductService) { }

    ngOnInit() {
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
  }
}
