import { Component, OnInit } from '@angular/core';
import { Cart } from '../../../Admin/models/cart';
import { Router, Params, ActivatedRoute, NavigationExtras } from '@angular/router';
import { CartItem } from '../../../Admin/models/cartItem';
import { OrderService } from '../../../UserFront/services/OrderService/order.service';
import { Product } from '../../../Admin/models/product';
import { Order } from '../../../Admin/models/order';
import { Customer } from '../../../Admin/models/customer';
import { CartService } from '../../../UserFront/services/CartService/cart.service';
import { AppConst } from '../../../UserFront/constants/app-const';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {

  constructor(private orderService: OrderService,
    private router: Router,
    private route: ActivatedRoute,
    private cartService: CartService) { }

  private cart: Cart = new Cart();
  private customer: Customer = new Customer();
  private order: Order = new Order();
  private total: number;
  private items: CartItem[];
  private selectedTab: number;
  private isOrderPlaced: boolean;
  private serverPath: string;

  private firstNameNotNull: boolean;
  private firstNameSize: boolean;
  private lastNameNotNull:boolean;
  private lastNameSize:boolean;
  private emailNotNull:boolean;
  private emailSize:boolean;
  private phonePattern:boolean;
  private phoneNotNull:boolean;
  private zipCodePattern:boolean
  private zipCodeNotNull:boolean;
  private townNotNull:boolean;
  private provinceNotNull:boolean;
  private streetNotNull:boolean;
  private streetSize:boolean;
  private emailSyntax:boolean;

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      this.cart = JSON.parse(params["cart"])
    });
    this.items = [];
    let evilResponseProps = Object.values(this.cart.cartItems);
    for (var prop of evilResponseProps) {
      this.items.push(prop);
    }
    this.serverPath = AppConst.serverPath;
  }

  goToAddressDelivery() {
    this.selectedTab = 1;
  }

  goToPersonal() {
    this.selectedTab = 0;
  }

  goToSummary() {
    this.selectedTab = 2;
  }


  onSubmit() {
    this.order.cart = this.cart;
    this.order.customer = this.customer
    this.order.isDelivered = false;
    this.order.isPaid = false;

    this.firstNameNotNull = false;
    this.firstNameSize = false;

    this.orderService.processOrder(this.order).subscribe(
      res => {
        this.isOrderPlaced = true;
        setTimeout(() => {
          this.router.navigate(['/user/home'])
        }, 4000);

        this.orderService.sendEmail(this.order).subscribe(
          res=>{
            console.log(res)
          },
          err=>{
            console.log(err)
          }
        )

        this.cartService.removeAllCartItems().subscribe(
          res => {
            console.log(res)
          },
          err => {
            console.log(err)
          }
        )
      },
      err => {

        this.firstNameNotNull = false;
        this.firstNameSize = false;
        this.lastNameNotNull = false;
        this.lastNameSize = false;
        this.emailSize = false;
        this.emailNotNull = false;
        this.phonePattern = false;
        this.phoneNotNull = false;
        this.zipCodePattern = false;
        this.zipCodeNotNull = false;
        this.townNotNull = false;
        this.provinceNotNull = false;
        this.streetSize = false;
        this.streetNotNull = false;
        this.emailSyntax = false;

        for (let i of err.json()) {

          //this way of violation is kinda bad, i'll need to fix it soon

          //first tab
          if (i.field == "customer.firstName" || i.field == "customer.lastName"
            || i.field == "customer.email" || i.field == "customer.phoneNumber") {

            if (i.field == "customer.firstName") {

              if (i.code == "NotNull") {
                this.firstNameNotNull = true;
              }
              if (i.code == "Size" && i.rejectedValue == "") {
                this.firstNameNotNull = true;
              }
              if (i.code == "Size" && i.rejectedValue != "") {
                this.firstNameSize = true;
              }
            }

            if (i.field == "customer.lastName") {

              if (i.code == "NotNull") {
                this.lastNameNotNull = true;
              }
              if (i.code == "Size" && i.rejectedValue == "") {
                this.lastNameNotNull = true;
              }
              if (i.code == "Size" && i.rejectedValue != "") {
                this.lastNameSize = true;
              }
            }

            if (i.field == "customer.email") {

              if (i.code == "Email"){
                this.emailSyntax = true;
              }
              if (i.code == "NotNull") {
                this.emailNotNull = true;
              }
              if (i.code == "Size" && i.rejectedValue == "") {
                this.emailNotNull = true;
              }
              if (i.code == "Size" && i.rejectedValue != "") {
                this.emailSize = true;
              }
            }

            if (i.field == "customer.phoneNumber") {

              if (i.code == "NotBlank") {
                this.phoneNotNull = true;
              } else if (i.code == "Pattern" && i.rejectedValue == "") {
                this.phoneNotNull = true;
              }

              if (i.code == "Pattern" && i.rejectedValue != "") {
                this.phonePattern = true;
              }
            }
              this.selectedTab = 0;
          }

          //second tab

          if (i.field == "customer.town" || i.field == "customer.street" ||
          i.field == "customer.zipCode" || i.field == "customer.province"){

            if (i.field == "customer.town") {

              if (i.code == "NotNull") {

                this.townNotNull = true;
              }
              if (i.code == "Size" && i.rejectedValue == "") {
                this.townNotNull = true;
              }
            }

            if (i.field == "customer.street") {

              if (i.code == "NotNull") {
                this.streetNotNull = true;
              }
              if (i.code == "Size" && i.rejectedValue == "") {
                this.streetNotNull = true;
              }
              if (i.code == "Size" && i.rejectedValue != "") {
                this.streetSize = true;
              }
            }

            if (i.field == "customer.zipCode") {

              if (i.code == "NotNull") {
                this.zipCodeNotNull = true;
              } else if (i.code == "Pattern" && i.rejectedValue == "") {
                this.zipCodeNotNull = true;
              }

              if (i.code == "Pattern" && i.rejectedValue != "") {
                this.zipCodePattern = true;
              }
            }

            if (i.field == "customer.province") {

              if (i.code == "NotNull") {
                this.provinceNotNull = true;
              } else if (i.code == "Size" && i.rejectedValue == "") {
                this.provinceNotNull = true;
              }
            }
            this.selectedTab = 1;
          }
        }
      }
    )
  }

}
