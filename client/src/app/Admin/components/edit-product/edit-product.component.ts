import { Component, OnInit } from '@angular/core';
import {ProductService} from '../../services/ProductService/product.service';
import {Product} from '../../models/product';
import {Params, ActivatedRoute, Router} from '@angular/router';
import {LoginService} from '../../services/LoginService/login.service';
import {CategoryService} from '../../services/CategoryService/category.service'
import {Category} from '../../models/category'

@Component({
  selector: 'app-edit-product',
  templateUrl: './edit-product.component.html',
  styleUrls: ['./edit-product.component.css']
})
export class EditProductComponent implements OnInit {

  private productId:number;
  private product:Product = new Product();
  private productUpdated:boolean;
  private categories:Category[];
  private nameNotBlank: boolean;
  private priceNotBlank: boolean;
  private categoryNotBlank: boolean;
  private inStockNumberNotBlank: boolean;
  private inStockNumberPattern: boolean;
  private pricePattern: boolean
  private productSize: boolean;
  private descriptionSize: boolean;

  constructor(private productServcie:ProductService,
    private loginService:LoginService,
  	private route:ActivatedRoute,
    private router:Router,
    private categoryService:CategoryService) { }

  ngOnInit() {
    this.route.params.forEach((params: Params) => {
    this.productId = Number.parseInt(params['id']);

    this.categoryService.getCategories().subscribe(
      res=>{
          this.categories=  res.json();
      },
      err=>{
        console.log(err);
      }
    )
  });

  this.productServcie.showProduct(this.productId).subscribe(
  		res => {
  			this.product = res.json();
  		},
  		error => console.log(error)
  	)
  }

onSubmit() {
  this.nameNotBlank = false;
  this.priceNotBlank = false;
  this.categoryNotBlank = false;
  this.inStockNumberNotBlank = false;
  this.inStockNumberPattern = false;
  this.pricePattern = false
  this.productSize = false;

  this.productServcie.createdProduct(this.product).subscribe(
    data => {
      this.productUpdated=true;
      this.router.navigate(['/admin/listProducts'])
    },
    err=>{

      for (let i of err.json()) {
        if (i.code == "NotNull" && i.field == "name") {
          this.nameNotBlank = true;
        } else if (i.code == "Size" && i.field == "name") {
          this.productSize = true;
        } else if (i.field == "price") {

          if (i.code == "NotNull") {
            this.priceNotBlank = true;
          } else if (i.code == "Pattern" && i.rejectedValue == "") {
            this.priceNotBlank = true;
          }

          if (i.code == "Pattern" && i.rejectedValue != "") {
            this.pricePattern = true;
          }

        } else if (i.field == "inStockNumber") {

          if (i.code == "NotNull") {
            this.inStockNumberNotBlank = true;
          } else if (i.code == "Pattern" && i.rejectedValue == "") {
            this.inStockNumberNotBlank = true;
          }

          if (i.code == "Pattern" && i.rejectedValue != "") {
            this.inStockNumberPattern = true;
          }

        } else if (i.code == "NotNull" && i.field == "type") {
          this.categoryNotBlank = true;
        } else if (i.code == "Size" && i.field == "description") {
          this.descriptionSize = true;
        }
      }
    }
  );
}

}
