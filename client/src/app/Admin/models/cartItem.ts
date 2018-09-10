import {Product} from './product';

export class CartItem {
  public productId:number;
  public product:Product;
  public amount:number;
  public totalPrice:number;
  public quantity:number;
}
