import { Component, OnInit } from '@angular/core';
import {Params, Router,ActivatedRoute, NavigationExtras } from '@angular/router';
import {PromotionService} from '../../../../Admin/services/PromotionService/promotion.service';
import {AppConst} from '../../../../UserFront/constants/app-const';
import {Promotion} from '../../../../Admin/models/promotion';
import {Product} from '../../../../Admin/models/product';

@Component({
  selector: 'app-manage-flow',
  templateUrl: './manage-flow.component.html',
  styleUrls: ['./manage-flow.component.css']
})
export class ManageFlowComponent implements OnInit {

  private id:number;
  private promotion:Promotion;

    constructor(private router: Router,
    private activatedRoute:ActivatedRoute,
    private promotionService:PromotionService,) { }

  ngOnInit() {
    this.activatedRoute.params.forEach((params: Params) => {
    this.id = Number.parseInt(params['id']);
  })
  }

  goToAddProductPromotion(){
    this.router.navigate(["admin/promotionManager/add", this.id])
  }

  goToDeletePromotionFromProduct(){
    this.router.navigate(["admin/promotionManager/delete", this.id])
  }

  goToAddPromotionByCategory(){
    this.router.navigate(["admin/promotionManager/byCategory", this.id])
  }
}
