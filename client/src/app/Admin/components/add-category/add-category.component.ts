import { Component, OnInit } from '@angular/core';
import { Product } from '../../models/product';
import { Category } from '../../models/category';
import {Params, ActivatedRoute, Router} from '@angular/router';
import {CategoryService} from '../../../Admin/services/CategoryService/category.service'

@Component({
  selector: 'app-add-category',
  templateUrl: './add-category.component.html',
  styleUrls: ['./add-category.component.css']
})
export class AddCategoryComponent implements OnInit {

  private category:Category= new Category();
  private name:string;
  private categoryNotNull:boolean;
  private categorySize:boolean;

  constructor(private router:Router,
              private categoryService:CategoryService) { }

  ngOnInit() {

  }

  onSubmit(category:Category){
    this.categorySize = false;
    this.categoryNotNull = false;
      this.categoryService.addCategory(category).subscribe(
        res=>{
          console.log(res)
          location.reload();
          this.router.navigate(['/admin/listCategories'])
        },
        err=>{

          for(let i of err.json()){
                if(i.code == "NotNull"){
                  this.categoryNotNull = true;
                }
                if(i.code == "Size" && i.rejectedValue==""){
                  this.categoryNotNull = true;
                }
                if (i.code == "Size" && i.rejectedValue != ""){
                  this.categorySize = true;
                }
          }
        }
      )
  }

}
