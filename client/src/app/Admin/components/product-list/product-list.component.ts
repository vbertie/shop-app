import { Component, OnInit, ViewChild} from '@angular/core';
import {MatPaginator, MatTableDataSource} from '@angular/material';
import {Product} from '../../models/product';
import {Router} from '@angular/router';
import {LoginService} from '../../services/LoginService/login.service';
import {ProductService} from '../../services/ProductService/product.service';
import {MatDialogModule} from '@angular/material/dialog'
import {MatDialog, MatDialogRef} from '@angular/material';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {
  private dataSource;
  private productList:Product[];
  private selectedProduct:Product;
  private checked:boolean;
  private allChecked:boolean;
  private removeProductList:Product[] = new Array();

  constructor
    (private ProductService:ProductService,
    private router:Router,
    private loginService:LoginService,
    private dialog:MatDialog) { }

  ngOnInit() {
    console.log(this.router.url);
    this.getProductList();
  }

  onSelect(product:Product) {
  this.selectedProduct=product;
  this.router.navigate(['/admin/viewProduct', this.selectedProduct.id]);
}

updateRemoveProductList(checked:boolean, product:Product){
  if (checked){
    this.removeProductList.push(product);
  } else {
    this.removeProductList.splice(this.removeProductList.indexOf(product), 1);
  }
}

updateSelected(checked:boolean){
  if (checked){
    this.allChecked = true;
    this.removeProductList = this.productList.slice();
  } else {
    this.allChecked=false;
    this.removeProductList=[];
  }
}

openDialog(product:Product) {
  let dialogRef = this.dialog.open(DialogResultExampleDialog);
  dialogRef.afterClosed().subscribe(
    result => {
      console.log(result);
      if(result=="yes") {
        this.ProductService.removeProduct(product.id).subscribe(
          res => {
            console.log(res);
            this.getProductList();
          },
          err => {
            console.log(err);
          }
        );
      }
    }
  );
}

removeSelectedProducts(){
  let dialogRef = this.dialog.open(DialogResultExampleDialog);
  dialogRef.afterClosed().subscribe(
    result => {
      console.log(result);
      if(result=="yes") {
        for (let  product of this.removeProductList){
          this.ProductService.removeProduct(product.id).subscribe(
            res => {
              console.log(res);
            },
            err => {
              console.log(err);
            }
          );
        }
      }
      location.reload();
    }
  );

  for (let  product of this.removeProductList){
    this.ProductService.removeProduct(product.id).subscribe(
      res => {
        console.log(res);
      },
      err => {
        console.log(err);
      }
    );
  }
}


  getProductList() {
  this.ProductService.getProductList().subscribe(
    res => {
      console.log(res.json());
      this.productList = res.json();
      this.dataSource = new MatTableDataSource(res.json());
    },
    error => {
      console.log(error);
    }
  );
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

@Component({
  selector: 'dialog-result-example-dialog',
  templateUrl: './dialog-result-example-dialog.html'
})
export class DialogResultExampleDialog {
  constructor(private dialogRef:MatDialogRef <DialogResultExampleDialog>) {}
}
