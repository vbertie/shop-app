import { Component, OnInit } from '@angular/core';
import {LoginService} from '../../services/LoginService/login.service';
import {Http, Headers } from '@angular/http';
import {Router} from '@angular/router';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {

  constructor(private loginService:LoginService, private http:Http, private router:Router) { }
  private loggedIn:boolean = false;

  ngOnInit() {
    this.checkSess();
  }

  checkSess(){
    this.loginService.verifySession().subscribe(
      response =>{
        this.loggedIn=true;
      },
      error =>{
        this.loggedIn=false;
      }
    );
  }

  logout(){
    this.loginService.logout().subscribe(
        res => {
              localStorage.clear();
              location.reload();
              this.router.navigate(["/admin"]);
        },
        err => {
          console.log(err);
        }
      );
  }
}
