import { Component, OnInit } from '@angular/core';
import {OrderService} from '../../../UserFront/services/OrderService/order.service';
import { Router,ActivatedRoute, NavigationExtras } from '@angular/router';
import {Order} from '../../../Admin/models/order';
import {ProductService} from '../../../Admin/services/ProductService/product.service';
import {CartService} from '../../../UserFront/services/CartService/cart.service';
import {MatDialogModule} from '@angular/material/dialog'
import {MatDialog, MatDialogRef} from '@angular/material';

@Component({
  selector: 'app-order-admin',
  templateUrl: './order-admin.component.html',
  styleUrls: ['./order-admin.component.css']
})
export class OrderAdminComponent implements OnInit {

  constructor(private router: Router,
    private activatedRoute:ActivatedRoute,
    private orderService:OrderService,
    private dialog:MatDialog) { }

  private orders:Order[];
  private checked:boolean;
  private allChecked:boolean;
  private removeOrderList:Order[] = new Array();

  ngOnInit() {
    this.orderService.getAllOrders().subscribe(
      res=>{
        this.orders = res.json();
      },
      err =>{
        console.log(err);
      }
    )
  }

  viewOrder(selectedOrder:Order){
    this.router.navigate(['/admin/viewOrder', selectedOrder.id]);
  }

  editOrder(selectedOrder:Order){
    this.router.navigate(['/admin/editOrder', selectedOrder.id]);
  }

  removeOrderLists(checked:boolean, order:Order){
    if (checked){
      this.removeOrderList.push(order);
    } else {
      this.removeOrderList.splice(this.removeOrderList.indexOf(order), 1);
    }
  }

  updateSelected(checked:boolean){
    if (checked){
      this.allChecked = true;
      this.removeOrderList = this.orders.slice();
    } else {
      this.allChecked=false;
      this.removeOrderList=[];
    }
  }

  removeOrder(order:Order) {
          this.orderService.removeOrder(order.id).subscribe(
            res => {
              console.log(res);
              this.orderService.getAllOrders().subscribe(
                res=>{
                  this.orders = res.json();
                },
                err =>{
                  console.log(err);
                }
              )
            },
            err => {
              console.log(err);
            }
          );
        }

  removeSelected(){
          for (let order of this.removeOrderList){
            this.orderService.removeOrder(order.id).subscribe(
              res => {
                console.log(res);
              },
              err => {
                console.log(err);
              }
            );
          }
        location.reload();
      }

  }
