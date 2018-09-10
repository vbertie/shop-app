import { Component, OnInit } from '@angular/core';
import {Cart} from '../../../Admin/models/cart';
import { Router,ActivatedRoute, NavigationExtras } from '@angular/router';
import {CartItem} from '../../../Admin/models/cartItem';
import {CartService} from '../../../UserFront/services/CartService/cart.service';
import {Product} from '../../../Admin/models/product';
import {AppConst} from '../../../UserFront/constants/app-const';

@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.css']
})
export class ShoppingCartComponent implements OnInit {

private cart:Cart= new Cart();
private items:CartItem[];
private total:number;
private serverPath:string;
private cartIsEmpty:boolean;

  constructor(private cartService:CartService,
              private router: Router,
              private activatedRoute:ActivatedRoute) { }

  ngOnInit() {
    this.cartService.getShoppingCart(localStorage.getItem("xAuthToken")).subscribe(
      res => {
        this.cart = res.json();
        this.items = [];
        let evilResponseProps = Object.values(this.cart.cartItems);

        for (var prop of evilResponseProps)
        {
           this.items.push(prop);
        }
      },
      err =>{
        console.log(err.text());
      }
    )
    this.serverPath = AppConst.serverPath;
  }

  removeCartItem(id:number){
    this.cartService.removeCartItem(id).subscribe(
      res=>{
        location.reload();
      },
      err=>{
        console.log(err);
      }
    )
  }

  emptyCart(){
    this.cartIsEmpty=true;
  }
  onSelect(cart:Cart){
    let navigationExtras: NavigationExtras = {
              queryParams: {
                  "cart": JSON.stringify(cart)
              }
          };
          this.router.navigate(["user/order"], navigationExtras);
}
}
