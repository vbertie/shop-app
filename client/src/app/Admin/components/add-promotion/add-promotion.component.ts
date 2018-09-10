import { Component, OnInit } from '@angular/core';
import { Product } from '../../models/product';
import { Promotion } from '../../models/promotion';
import { Params, ActivatedRoute, Router } from '@angular/router';
import { PromotionService } from '../../../Admin/services/PromotionService/promotion.service'

@Component({
  selector: 'app-add-promotion',
  templateUrl: './add-promotion.component.html',
  styleUrls: ['./add-promotion.component.css']
})
export class AddPromotionComponent implements OnInit {

  private promotion: Promotion = new Promotion();

  private name: string;
  private promotionNameNotNull: boolean;
  private promotionNameSize: boolean;
  private percentageReductionNotNull: boolean;
  private percentageReaductionPattern: boolean;
  private percentageReductionMax:boolean;

  constructor(private router: Router,
    private promotionService: PromotionService) { }

  ngOnInit() {
  }

  onSubmit(promotion: Promotion) {
    this.promotionNameNotNull = false;
    this.promotionNameSize = false;
    this.percentageReductionNotNull = false;
    this.percentageReaductionPattern = false;

    this.promotionService.addPromotion(promotion).subscribe(
      res => {
        location.reload();
        this.router.navigate(["/admin/listPromotions"])
      },
      err => {
        for (let i of err.json()) {

          if (i.field == "percentageReduction") {

            if (i.code == "NotNull") {
              this.percentageReductionNotNull = true;
            } else if (i.code == "Pattern" && i.rejectedValue == "") {
              this.percentageReductionNotNull = true;
            }

            if (i.code == "Pattern" && i.rejectedValue != "") {
              this.percentageReaductionPattern = true;
            }

            if(i.code =="Max"){
              this.percentageReductionMax = true;
            }
          }

          if (i.field == "promotionName") {
            if (i.code == "NotNull") {
              this.promotionNameNotNull = true;
            }
            if (i.code == "Size" && i.rejectedValue == "") {
              this.promotionNameNotNull = true;
            }
            if (i.code == "Size" && i.rejectedValue != "") {
              this.promotionNameSize = true;
            }
          }
        }
      }
    )
  }

}
