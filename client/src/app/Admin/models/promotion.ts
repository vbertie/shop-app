import {Product} from './product';

export class Promotion {
  public id:number;
  public promotionName:string;
  public percentageReduction:number;
  public products:Product[];
}
