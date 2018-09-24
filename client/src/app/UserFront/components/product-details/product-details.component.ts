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
  private productId:number;
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

      if (this.product.id == null){
        this.route.params.forEach((params: Params) => {
        this.productId = Number.parseInt(params['id']);
      });

          this.productService.showProduct(this.productId).subscribe(
            res=>{
              this.product = res.json();
              if (res.json().promotionId != null){
                this.isAtPromotion = true;
              }
            },
            err=>{
              console.log(err);
            }
          )
      }

    this.serverPath = AppConst.serverPath;
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
