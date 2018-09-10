import {Customer} from './customer';
import {Cart} from './cart';
import {CartItem} from './cartItem';
import {Product} from './product';

export class Order {
  public id:number;
  public isDelivered:boolean;
  public isPaid:boolean;
  public customer:Customer;
  public cart:Cart;
  public items:CartItem[];
}
