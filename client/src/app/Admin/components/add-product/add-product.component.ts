import { Component, OnInit } from '@angular/core';
import { Product } from '../../models/product';
import { Params, ActivatedRoute, Router } from '@angular/router';
import { ProductService } from '../../services/ProductService/product.service';
import { UploadImageService } from '../../services/UploadImageService/upload-image.service';
import { LoginService } from '../../services/LoginService/login.service';
import { Category } from '../../models/category';
import { CategoryService } from '../../../Admin/services/CategoryService/category.service'

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css']
})
export class AddProductComponent implements OnInit {

  private createdProduct: Product = new Product();
  private nameNotBlank: boolean;
  private priceNotBlank: boolean;
  private categoryNotBlank: boolean;
  private inStockNumberNotBlank: boolean;
  private inStockNumberPattern: boolean;
  private pricePattern: boolean
  private productSize: boolean;
  private descriptionSize: boolean;
  private categories: Category[];

  constructor(private productService: ProductService,
    private loginService: LoginService,
    private router: Router,
    private uploadImageService: UploadImageService,
    private categoryService: CategoryService) { }

  onSubmit() {
    this.nameNotBlank = false;
    this.priceNotBlank = false;
    this.categoryNotBlank = false;
    this.inStockNumberNotBlank = false;
    this.inStockNumberPattern = false;
    this.pricePattern = false
    this.productSize = false;

    this.productService.createdProduct(this.createdProduct).subscribe(
      response => {
        this.uploadImageService.upload(JSON.parse(JSON.parse(JSON.stringify(response))._body).id);
        location.reload();
        this.router.navigate(['/admin/listProducts'])
      }, err => {

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

  ngOnInit() {
    this.categoryService.getCategories().subscribe(
      res => {
        this.categories = res.json();
      },
      err => {
        console.log(err);
      }
    )
    this.createdProduct.active = true;
  }

  logout() {
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
