import { Component, OnInit } from '@angular/core';
import { LoginService } from '../../../Admin/services/LoginService/login.service';
import { Router,ActivatedRoute, NavigationExtras } from '@angular/router';
import {Product} from '../../../Admin/models/product';
import {ProductService} from '../../../Admin/services/ProductService/product.service';
import {CartService} from '../../../UserFront/services/CartService/cart.service';
import {Category} from '../../../Admin/models/category'
import {CategoryService} from '../../../Admin/services/CategoryService/category.service'

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  private loggedIn = false;
  private categories:Category[];
  private predatorId:number
  private canonId:number
  private iphoneXId:number

  constructor(private loginService: LoginService,
    private productService:ProductService,
    private router: Router,
    private activatedRoute:ActivatedRoute,
    private cartService:CartService,
    private categoryService:CategoryService) { }

  ngOnInit() {
    this.predatorId = 1;
    this.canonId = 5;
    this.iphoneXId = 3;
    //cover the location.reload with method categories/search by keyword in navbar/promotions in navbar, to be more safe with shopping cart
    this.categoryService.getCategories().subscribe(
      res=>{
        this.categories = res.json();
      },
      err=>{
        console.log(err);
      }
    )
  }

  predator(){
    this.router.navigate(['/user/product/details', 1])
  }
}
