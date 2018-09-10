import { Component, OnInit } from '@angular/core';
import {OrderService} from '../../../UserFront/services/OrderService/order.service';
import { Params, Router,ActivatedRoute, NavigationExtras } from '@angular/router';
import {Order} from '../../../Admin/models/order';
import {ProductService} from '../../../Admin/services/ProductService/product.service';
import {CartService} from '../../../UserFront/services/CartService/cart.service';

@Component({
  selector: 'app-order-edit',
  templateUrl: './order-edit.component.html',
  styleUrls: ['./order-edit.component.css']
})
export class OrderEditComponent implements OnInit {

  private order:Order;
  private orderId:number;
  constructor(private router: Router,
    private activatedRoute:ActivatedRoute,
    private orderService:OrderService) { }

    ngOnInit() {
      this.activatedRoute.params.forEach((params: Params) => {
      this.orderId = Number.parseInt(params['id']);
    });

      this.orderService.getOrder(this.orderId).subscribe(
        res=>{
          this.order = res.json();
        },
        err=>{
          console.log(err);
        }
      )

    }

    submit(order:Order){
      this.orderService.editOrder(order).subscribe(
        res=>{
          console.log(res)
        } ,
        err=>{
          console.log(err)
        }
      )
      location.reload();
      this.router.navigate(["/admin/listOrders"])
    }



}
