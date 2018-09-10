import { Component, OnInit } from '@angular/core';
import {Params, ActivatedRoute, Router} from '@angular/router';
import {ProductService} from '../../../Admin/services/ProductService/product.service';
import {Product} from '../../../Admin/models/product';
import {CartService} from '../../../UserFront/services/CartService/cart.service';
import {CartItem} from '../../../Admin/models/cartItem';
import {AppConst} from '../../../UserFront/constants/app-const';

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent implements OnInit {

  private product:Product = new Product();
  private numberList: number[] = [1,2,3,4,5,6,7,8,9];
	private qty: number;
  private addProductSuccess: boolean = false;
  private notEnoughInStock:boolean = false;
  private cartItem:CartItem = new CartItem;
  private serverPath:string;

  private productMinQuantity:boolean;
  private added:boolean;
  private tooBig:boolean;
  private isAtPromotion:boolean;

  constructor(private route:ActivatedRoute,
              private productService:ProductService,
              private router:Router,
              private cartService:CartService) { }

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
          this.product = JSON.parse(params['product']);
      });
      console.log(this.product.name)
    this.serverPath = AppConst.serverPath;

    if ((this.product.price != this.product.oldPrice) && this.product.oldPrice !=null){
      this.isAtPromotion = true;
    }
  }

  addToCart(){
      this.cartItem.productId = this.product.id;
      this.cartItem.amount = this.qty;
      this.tooBig = false;
      this.productMinQuantity = false;

      this.cartService.addCartItem(this.cartItem).subscribe(
        res => {
          this.added = true;
          this.tooBig = false;
          this.router.navigate(['/user/shoppingCart']);
        },
        err => {
          if (err.json().message == "Quantity is bigger"){
            this.tooBig = true;
            this.added = false;
          }

          for (let i of err.json()){
            if (i.field == "amount"){
              if (i.code == "Min"){
                this.productMinQuantity = true;
              }
            }
          }
        }
      )
  }
}
