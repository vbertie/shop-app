import { Component, OnInit } from '@angular/core';
import { LoginService } from '../../../Admin/services/LoginService/login.service';
import { Router, NavigationExtras } from '@angular/router';
import {Product} from '../../../Admin/models/product';
import {UserService} from '../../services/UserService/user.service';
import {User} from '../../../Admin/models/user';
import {Category} from '../../../Admin/models/category';
import {ProductService} from '../../../Admin/services/ProductService/product.service';
import {CartService} from '../../../UserFront/services/CartService/cart.service';
import {CategoryService} from '../../../Admin/services/CategoryService/category.service';
@Component({
  selector: 'app-nav-bar-user',
  templateUrl: './nav-bar-user.component.html',
  styleUrls: ['./nav-bar-user.component.css']
})
export class NavBarUserComponent implements OnInit {

  private loggedIn = false;
  private keyword: string;
  private productList:Product[];
  private notFound:boolean;
  private categories:Category[];

  constructor(private loginService: LoginService,
    private productService:ProductService,
    private router: Router,
    private cartService:CartService,
    private categoryService:CategoryService) { }

  ngOnInit() {
    if (localStorage.getItem("xAuthToken") == null){
      this.loginService.getSession().subscribe(
        res=>{
          console.log(res.text());
          localStorage.setItem("xAuthToken", res.json().sessionId);

          this.cartService.getShoppingCart(localStorage.getItem("xAuthToken")).subscribe(
            res => {
                console.log(".")
            },
            err => {
              if (err.json().message == "Cart not found"){
                this.cartService.createShoppingCart(localStorage.getItem("xAuthToken")).subscribe(
                  res=>{
                      this.cartService.getShoppingCart(localStorage.getItem("xAuthToken")).subscribe(
                        res=>{
                          console.log(res)
                        }
                      )
                    console.log(res);
                  }
                )
              }
            }
          );
        }
      )
    } else {
      this.cartService.getShoppingCart(localStorage.getItem("xAuthToken")).subscribe(
        res => {
            console.log(".")
        },
        err => {
          if (err.json().message == "Cart not found"){
            this.cartService.createShoppingCart(localStorage.getItem("xAuthToken")).subscribe(
              res=>{
                this.cartService.getShoppingCart(localStorage.getItem("xAuthToken")).subscribe(
                  res=>{
                    console.log(res)
                  }
                )
              }
            )
          }
        }
      );
    }

    this.categoryService.getCategories().subscribe(
      res=>{
        this.categories = res.json()
      },
      err=>{
        console.log(err);
      }
    )
}

      logout() {
      	this.loginService.logout().subscribe(
      		res => {
            localStorage.clear();
            window.localStorage.clear();
      			location.reload();
            this.router.navigate(['/admin']);
      		},
      		err => {
      			console.log(err);
      		}
      	);
      }

      onClick(category:string){
        location.reload()
        this.router.navigate(['/user/productPromotions', category])
      }
      onSearch(){
        this.productService.searchByName(this.keyword).subscribe(
          res=>{
            this.productList = res.json();
            let navigationExtras:NavigationExtras = {
              queryParams: {
                "productList" : JSON.stringify(this.productList)
              }
            };
              this.router.navigate(['/user/productList'], navigationExtras);
          },
          err=>{
            console.log(err);
          }
        );
      }
}
