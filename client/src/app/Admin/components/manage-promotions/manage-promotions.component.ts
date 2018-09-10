import { Component, OnInit } from '@angular/core';
import { PromotionService } from '../../services/PromotionService/promotion.service';
import { Params, ActivatedRoute, Router } from '@angular/router';
import { LoginService } from '../../services/LoginService/login.service';
import { AppConst } from '../../../UserFront/constants/app-const';
import {Promotion} from '../../models/promotion';
@Component({
  selector: 'app-manage-promotions',
  templateUrl: './manage-promotions.component.html',
  styleUrls: ['./manage-promotions.component.css']
})
export class ManagePromotionsComponent implements OnInit {

  constructor(private promotionService: PromotionService,
    private route: ActivatedRoute,
    private router: Router) { }

  private promotions:Promotion[];
  private zjebanePromocje:Promotion[];


  ngOnInit() {
      this.promotionService.getPromotions().subscribe(
        res=>{
          this.promotions = res.json();
        },
        err=>{
          console.log(err)
        }
      )
  }

  managePromotion(promotion:Promotion){
    this.router.navigate(["/admin/promotionManager/manage-flow", promotion.id])
  }

}
