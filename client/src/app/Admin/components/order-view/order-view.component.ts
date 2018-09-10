import { Component, OnInit } from '@angular/core';
import {OrderService} from '../../../UserFront/services/OrderService/order.service';
import { Params, Router,ActivatedRoute, NavigationExtras } from '@angular/router';
import {Order} from '../../../Admin/models/order';
import {ProductService} from '../../../Admin/services/ProductService/product.service';
import {CartService} from '../../../UserFront/services/CartService/cart.service';
import {AppConst} from '../../../UserFront/constants/app-const';
import {Customer} from '../../../Admin/models/customer';
import {CartItem} from '../../../Admin/models/cartItem';
import {Product} from '../../../Admin/models/product';

@Component({
  selector: 'app-order-view',
  templateUrl: './order-view.component.html',
  styleUrls: ['./order-view.component.css']
})
export class OrderViewComponent implements OnInit {

  private order:Order;
  private customer:Customer;
  private orderId:number;
  private serverPath:string;
  private cartItems:CartItem[];
  private productQuantityMultiplication:number;

  constructor(private productService:ProductService,
    private router: Router,
    private activatedRoute:ActivatedRoute,
    private orderService:OrderService,) { }

  ngOnInit() {
    this.activatedRoute.params.forEach((params: Params) => {
    this.orderId = Number.parseInt(params['id']);
  });

    this.orderService.getOrder(this.orderId).subscribe(
      res => {
        this.order = res.json();
        this.customer = this.order.customer;
        this.cartItems = [];

        let evilResponseProps = Object.values(this.order.items);

        for (let prop of evilResponseProps)
        {
           this.cartItems.push(prop);
        }
      },
       err => {
        console.log(err.text());
      }
    )
    this.serverPath = AppConst.serverPath;
  }

}
