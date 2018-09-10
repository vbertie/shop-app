import { Component, OnInit } from '@angular/core';
import { Product } from '../../models/product';
import { Category } from '../../models/category';
import {Params, ActivatedRoute, Router} from '@angular/router';
import {CategoryService} from '../../../Admin/services/CategoryService/category.service'

@Component({
  selector: 'app-edit-category',
  templateUrl: './edit-category.component.html',
  styleUrls: ['./edit-category.component.css']
})
export class EditCategoryComponent implements OnInit {

  constructor(private router: Router,
    private activatedRoute:ActivatedRoute,
    private categoryService:CategoryService) { }

  private categoryId:number;
  private category:Category;
  private categoryNotNull:boolean;
  private categorySize:boolean;

  ngOnInit() {
    this.activatedRoute.params.forEach((params: Params) => {
    this.categoryId = Number.parseInt(params['id']);
  });

  this.categoryService.getCategory(this.categoryId).subscribe(
    res=>{
      this.category = res.json();
    },
    err=>{
      console.log(err)
    }
  )
  }

  onSubmit(category:Category){
    this.categorySize = false;
    this.categoryNotNull = false;

    this.categoryService.updateCategory(category).subscribe(
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
