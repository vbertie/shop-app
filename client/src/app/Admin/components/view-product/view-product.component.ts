import { Component, OnInit } from '@angular/core';
import {ProductService} from '../../services/ProductService/product.service';
import {Product} from '../../models/product';
import {Params, ActivatedRoute, Router} from '@angular/router';
import {LoginService} from '../../services/LoginService/login.service';
import {AppConst} from '../../../UserFront/constants/app-const';

@Component({
  selector: 'app-view-product',
  templateUrl: './view-product.component.html',
  styleUrls: ['./view-product.component.css']
})
export class ViewProductComponent implements OnInit {
  private product:Product = new Product();
  private productId: number;
  private serverPath:string;

  constructor(private productServcie:ProductService,
  	private route:ActivatedRoute, private router:Router,
    private loginService:LoginService, ) { }

    onSelect(product:Product) {
      this.router.navigate(['/admin/editProduct', this.product.id]);
    }

  ngOnInit() {
    console.log(this.router.url);
    this.route.params.forEach((params: Params) => {
    this.productId = Number.parseInt(params['id']);
  });

  this.productServcie.showProduct(this.productId).subscribe(
    res => {
      this.product = res.json();
    },
    error => {
      console.log(error);
    }
  );

  this.serverPath = AppConst.serverPath;
  }
  logout(){
    this.loginService.logout().subscribe(
        res => {
        localStorage.clear();
        location.reload();
        this.router.navigate(['/admin']);
        },
        err => {
          console.log(err);
        }
      );
  }

}
