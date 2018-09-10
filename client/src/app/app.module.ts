import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {HttpClientModule} from '@angular/common/http';
import {routing} from './app.routing';
import {HttpModule} from '@angular/http';
import { FormsModule} from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatSlideToggleModule} from '@angular/material/slide-toggle';
import {MatListModule} from '@angular/material/list';
import {MatDialogModule} from '@angular/material/dialog'
import {MatDialog, MatDialogRef} from '@angular/material';
import {AppConst} from './UserFront/constants/app-const';

import 'hammerjs';

import {
  MatButtonModule,
  MatMenuModule,
  MatToolbarModule,
  MatIconModule,
  MatCardModule,
  MatGridListModule,
  MatInputModule,
  MatFormFieldModule,
  MatSelectModule,
  MatProgressBarModule,
  MatPaginatorModule,
  MatAutocompleteModule,
  MatTableModule,
  MatCheckboxModule,
  MatTabsModule
} from '@angular/material';

import { LoginService } from './Admin/services/LoginService/login.service';
import {ProductService} from './Admin/services/ProductService/product.service';
import {CartService} from './UserFront/services/CartService/cart.service';
import {OrderService} from './UserFront/services/OrderService/order.service'

import { LoginComponent } from './Admin/components/login/login.component';
import { AppComponent } from './app.component';
import { AddProductComponent } from './Admin/components/add-product/add-product.component';
import { ProductListComponent } from './Admin/components/product-list/product-list.component';
import { DialogResultExampleDialog } from './Admin/components/product-list/product-list.component';
import { ViewProductComponent } from './Admin/components/view-product/view-product.component';
import { EditProductComponent } from './Admin/components/edit-product/edit-product.component';
import { HomeComponent } from './UserFront/components/home/home.component';
import { MyAccountComponent } from './UserFront/components/my-account/my-account.component';
import { ProductCategoryComponent } from './UserFront/components/product-category/product-category.component';
import { NavBarComponent } from './Admin/components/nav-bar/nav-bar.component';
import { NavBarUserComponent } from './UserFront/components/nav-bar-user/nav-bar-user.component';
import { UserProductListComponent } from './UserFront/components/user-product-list/user-product-list.component';
import { ProductDetailsComponent } from './UserFront/components/product-details/product-details.component';
import { ShoppingCartComponent } from './UserFront/components/shopping-cart/shopping-cart.component';
import { OrderComponent } from './UserFront/components/order/order.component';
import { OrderAdminComponent } from './Admin/components/order-admin/order-admin.component';
import { OrderViewComponent } from './Admin/components/order-view/order-view.component';
import { OrderEditComponent } from './Admin/components/order-edit/order-edit.component';
import { AddPromotionComponent } from './Admin/components/add-promotion/add-promotion.component';
import { ListPromotionsComponent } from './Admin/components/list-promotions/view-promotions.component';
import { ManagePromotionsComponent } from './Admin/components/manage-promotions/manage-promotions.component';
import { AddCategoryComponent } from './Admin/components/add-category/add-category.component';
import { CategoryListComponent } from './Admin/components/category-list/category-list.component';
import { EditCategoryComponent } from './Admin/components/edit-category/edit-category.component';
import { ViewPromotionComponent } from './Admin/components/view-promotion/view-promotion.component';
import { AddPromotionToProductsComponent } from './Admin/components/manage-promotions/manage-flow/add-promotion-to-products/add-promotion-to-products.component';
import {DeletePromotionFromProductsComponent} from './Admin/components/manage-promotions/manage-flow/delete-promotion-from-products/delete-promotion-from-products.component'
import { AddPromotionByCategoryComponent } from './Admin/components/manage-promotions/manage-flow/add-promotion-by-category/add-promotion-by-category.component';
import { ManageFlowComponent } from './Admin/components/manage-promotions/manage-flow/manage-flow.component';
import { PromotionsComponent } from './UserFront/components/promotions/promotions.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    AddProductComponent,
    ProductListComponent,
    DialogResultExampleDialog,
    ViewProductComponent,
    EditProductComponent,
    HomeComponent,
    MyAccountComponent,
    ProductCategoryComponent,
    NavBarComponent,
    NavBarUserComponent,
    UserProductListComponent,
    ProductDetailsComponent,
    ShoppingCartComponent,
    OrderComponent,
    OrderAdminComponent,
    OrderViewComponent,
    OrderEditComponent,
    AddPromotionComponent,
    ListPromotionsComponent,
    ManagePromotionsComponent,
    AddCategoryComponent,
    CategoryListComponent,
    EditCategoryComponent,
    ViewPromotionComponent,
    AddPromotionToProductsComponent,
    DeletePromotionFromProductsComponent,
    AddPromotionByCategoryComponent,
    ManageFlowComponent,
    PromotionsComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    HttpModule,
    MatButtonModule,
    MatMenuModule,
    MatToolbarModule,
    MatIconModule,
    MatCardModule,
    MatGridListModule,
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatProgressBarModule,
    MatPaginatorModule,
    BrowserAnimationsModule,
    MatSelectModule,
    MatAutocompleteModule,
    MatSlideToggleModule,
    MatListModule,
    MatDialogModule,
    MatTableModule,
    MatCheckboxModule,
    MatTabsModule,
    routing
  ],
  providers: [LoginService,ProductService,CartService,OrderService],
  bootstrap: [AppComponent, DialogResultExampleDialog]
})
export class AppModule { }
