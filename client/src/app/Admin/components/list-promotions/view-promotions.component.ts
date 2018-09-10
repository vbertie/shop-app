import { Component, OnInit } from '@angular/core';
import { PromotionService } from '../../services/PromotionService/promotion.service';
import { Params, ActivatedRoute, Router } from '@angular/router';
import { LoginService } from '../../services/LoginService/login.service';
import { AppConst } from '../../../UserFront/constants/app-const';
import {Promotion} from '../../models/promotion';
@Component({
  selector: 'app-view-promotions',
  templateUrl: './view-promotions.component.html',
  styleUrls: ['./view-promotions.component.css']
})
export class ListPromotionsComponent implements OnInit {

  constructor(private promotionService: PromotionService,
    private route: ActivatedRoute,
    private router: Router) { }

    private promotions:Promotion[];
    private checked:boolean;
    private allChecked:boolean;
    private removePromotionList:Promotion[] = new Array();

  ngOnInit() {
    this.promotionService.getPromotions().subscribe(
      res=>{
        this.promotions = res.json();
        console.log(res)
      },
      err=>{
        console.log(err)
      }
    )
  }

  viewPromotion(promotion:Promotion){
    this.router.navigate(["/admin/viewPromotion", promotion.id])
  }

  removePromotionLists(checked:boolean, promotion:Promotion){
    if (checked){
      this.removePromotionList.push(promotion);
    } else {
      this.removePromotionList.splice(this.removePromotionList.indexOf(promotion), 1);
    }
  }

  updateSelected(checked:boolean){
    if (checked){
      this.allChecked = true;
      this.removePromotionList = this.promotions.slice();
    } else {
      this.allChecked=false;
      this.removePromotionList=[];
    }
  }

  removePromotion(promotion:Promotion) {
          this.promotionService.removePromotion(promotion.id).subscribe(
            res => {
              console.log(res);
              this.promotionService.getPromotions().subscribe(
                res=>{
                  this.promotions = res.json();
                },
                err =>{
                  console.log(err);
                }
              )
            },
            err => {
              console.log(err);
            }
          );
                  // location.reload();
        }

  removeSelected(){
          for (let promotion of this.removePromotionList){
            this.promotionService.removePromotion(promotion.id).subscribe(
              res => {
                console.log(res);
              },
              err => {
                console.log(err);
              }
            );
          }
        location.reload();
      }
}
