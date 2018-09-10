import { Component, OnInit } from '@angular/core';
import { Product } from '../../models/product';
import { Category } from '../../models/category';
import {Params, ActivatedRoute, Router} from '@angular/router';
import {CategoryService} from '../../../Admin/services/CategoryService/category.service'

@Component({
  selector: 'app-category-list',
  templateUrl: './category-list.component.html',
  styleUrls: ['./category-list.component.css']
})
export class CategoryListComponent implements OnInit {

  constructor(private router:Router,
              private categoryService:CategoryService) { }

  private categories:Category[];
  private checked:boolean;
  private allChecked:boolean;
  private removeCategoryList:Category[] = new Array();

  ngOnInit() {
      this.categoryService.getCategories().subscribe(
        res=>{
          this.categories = res.json();
        },
        err=>{
          console.log(err)
        }
      )
  }

  editCategory(category:Category){
    this.router.navigate(['/admin/editCategory', category.id]);
  }

  removeCategoryLists(checked:boolean, category:Category){
    if (checked){
      this.removeCategoryList.push(category);
    } else {
      this.removeCategoryList.splice(this.removeCategoryList.indexOf(category), 1);
    }
  }

  updateSelected(checked:boolean){
    if (checked){
      this.allChecked = true;
      this.removeCategoryList = this.categories.slice();
    } else {
      this.allChecked=false;
      this.removeCategoryList=[];
    }
  }

  removeCategory(category:Category) {
          this.categoryService.removeCategory(category.id).subscribe(
            res => {
              console.log(res);
              this.categoryService.getCategories().subscribe(
                res=>{
                  this.categories = res.json();
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
                  location.reload();
        }

  removeSelected(){
          for (let category of this.removeCategoryList){
            this.categoryService.removeCategory(category.id).subscribe(
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
