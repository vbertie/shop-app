import {ModuleWithProviders} from '@angular/core';
import{RouterModule, Routes}   from '@angular/router';
import {LoginComponent} from './Admin/components/login/login.component';
import { AddProductComponent } from './Admin/components/add-product/add-product.component';
import { ProductListComponent } from './Admin/components/product-list/product-list.component';
import { ViewProductComponent } from './Admin/components/view-product/view-product.component';
import { EditProductComponent } from './Admin/components/edit-product/edit-product.component';
import { HomeComponent } from './UserFront/components/home/home.component';
import {MyAccountComponent} from './UserFront/components/my-account/my-account.component';
import {ProductCategoryComponent} from './UserFront/components/product-category/product-category.component';
import {UserProductListComponent} from './UserFront/components/user-product-list/user-product-list.component';
import {ProductDetailsComponent} from './UserFront/components/product-details/product-details.component';
import {ShoppingCartComponent} from './UserFront/components/shopping-cart/shopping-cart.component';
import {OrderComponent} from './UserFront/components/order/order.component';
import {OrderAdminComponent} from './Admin/components/order-admin/order-admin.component'
import {OrderViewComponent} from './Admin/components/order-view/order-view.component'
import {OrderEditComponent} from './Admin/components/order-edit/order-edit.component'
import {AddPromotionComponent} from './Admin/components/add-promotion/add-promotion.component'
import {AddCategoryComponent} from './Admin/components/add-category/add-category.component'
import {ListPromotionsComponent} from './Admin/components/list-promotions/view-promotions.component'
import {CategoryListComponent} from './Admin/components/category-list/category-list.component'
import {EditCategoryComponent} from './Admin/components/edit-category/edit-category.component'
import {ViewPromotionComponent} from './Admin/components/view-promotion/view-promotion.component';
import {ManagePromotionsComponent} from './Admin/components/manage-promotions/manage-promotions.component'
import {ManageFlowComponent} from './Admin/components/manage-promotions/manage-flow/manage-flow.component'
import {AddPromotionByCategoryComponent} from './Admin/components/manage-promotions/manage-flow/add-promotion-by-category/add-promotion-by-category.component'
import {AddPromotionToProductsComponent} from './Admin/components/manage-promotions/manage-flow/add-promotion-to-products/add-promotion-to-products.component'
import {DeletePromotionFromProductsComponent} from './Admin/components/manage-promotions/manage-flow/delete-promotion-from-products/delete-promotion-from-products.component'
import {PromotionsComponent} from './UserFront/components/promotions/promotions.component';
const appRoutes: Routes = [
  {
    path : '',
    redirectTo: '/user/home',
    pathMatch: 'full'
  },
  {
    path : 'admin',
    redirectTo: '/admin/login',
    pathMatch: 'full'
  },
  {
    path : 'user/home',
    component: HomeComponent
  },
  {
    path: 'admin/login', component:LoginComponent
  },
  {
    path: 'admin/addProduct', component:AddProductComponent
  },
  {
    path: 'admin/listProducts', component:ProductListComponent
  },
  {
    path: 'admin/viewProduct/:id', component:ViewProductComponent
  },
  {
    path: 'admin/editProduct/:id', component:EditProductComponent
  },
  {
    path: 'user/myAccount', component:MyAccountComponent
  },
  {
    path: 'user/products/:category', component:ProductCategoryComponent
  },
  {
    path: 'user/productList', component:UserProductListComponent
  },
  {
    path: 'user/product/details', component:ProductDetailsComponent
  },
  {
    path: 'user/product/details/:id', component:ProductDetailsComponent
  },
  {
    path: 'user/shoppingCart', component:ShoppingCartComponent
  },
  {
    path: 'user/order', component:OrderComponent
  },
  {
    path: 'admin/listOrders', component:OrderAdminComponent
  },
  {
    path: 'admin/viewOrder/:id', component:OrderViewComponent
  },
  {
    path: 'admin/editOrder/:id', component:OrderEditComponent
  },
  {
    path: 'admin/addPromotion', component:AddPromotionComponent
  },
  {
    path: 'admin/addCategory', component:AddCategoryComponent
  },
  {
    path: 'admin/listCategories', component:CategoryListComponent
  },
  {
    path: 'admin/editCategory/:id', component:EditCategoryComponent
  },
  {
    path: 'admin/listPromotions', component:ListPromotionsComponent
  },
  {
    path: 'admin/viewPromotion/:id', component:ViewPromotionComponent
  },
  {
    path: 'admin/managePromotions', component:ManagePromotionsComponent
  },
  {
    path: 'admin/promotionManager/manage-flow/:id', component:ManageFlowComponent
  },
  {
    path: 'admin/promotionManager/add/:id', component:AddPromotionToProductsComponent
  },
  {
    path: 'admin/promotionManager/byCategory/:id', component:AddPromotionByCategoryComponent
  },
  {
    path: 'admin/promotionManager/delete/:id', component:DeletePromotionFromProductsComponent
  },
  {
    path: 'user/productPromotions/:category', component:PromotionsComponent
  }
]

export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes);
