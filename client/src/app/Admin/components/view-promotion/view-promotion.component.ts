import { Component, OnInit } from '@angular/core';
import {Params, Router,ActivatedRoute, NavigationExtras } from '@angular/router';
import {PromotionService} from '../../../Admin/services/PromotionService/promotion.service';
import {AppConst} from '../../../UserFront/constants/app-const';
import {Promotion} from '../../../Admin/models/promotion';
import {Product} from '../../../Admin/models/product';

@Component({
  selector: 'app-view-promotion',
  templateUrl: './view-promotion.component.html',
  styleUrls: ['./view-promotion.component.css']
})
export class ViewPromotionComponent implements OnInit {

  private promotionId:number;
  private promotion:Promotion=new Promotion();
  private products:Product[];
  private serverPath:string;

    constructor(private router: Router,
    private activatedRoute:ActivatedRoute,
    private promotionService:PromotionService) { }

  ngOnInit() {
    this.activatedRoute.params.forEach((params: Params) => {
    this.promotionId = Number.parseInt(params['id']);
  })

  this.serverPath = AppConst.serverPath;

  this.promotionService.getPromotion(this.promotionId).subscribe(
    res=>{
      this.promotion = res.json();
      this.products = this.promotion.products;
    },
    err=>{
      console.log(err);
    }
  )
  }
}
