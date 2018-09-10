import { Component, OnInit } from '@angular/core';
import { LoginService } from '../../../Admin/services/LoginService/login.service';
import { Router, NavigationExtras } from '@angular/router';
import {Product} from '../../../Admin/models/product';
import {UserService} from '../../services/UserService/user.service';
import {User} from '../../../Admin/models/user';
import {ProductService} from '../../../Admin/services/ProductService/product.service';
@Component({
  selector: 'app-my-account',
  templateUrl: './my-account.component.html',
  styleUrls: ['./my-account.component.css']
})
export class MyAccountComponent implements OnInit {
  private loginError:boolean = false;
  private loggedIn = false;
  private credential = {'username':'', 'password':''};
  private user:User = new User();
  private usernameExists:boolean;
  private emailExists:boolean;
  private recoverEmail:string;
  private emailNotExists:boolean = false;
  private userCreated:boolean;
  private emailSent:boolean;

  constructor(
    private loginService:LoginService,
    private router:Router,
    private userService:UserService,
    private productServcie:ProductService
  ) { }

  ngOnInit() {
  }

  onNewAccount(){
  this.usernameExists=false;
  this.emailExists=false;
  this.userService.createAccount(this.user).subscribe(
    res =>{
      console.log(res);
      this.userCreated=true;
    },
    error =>{
        console.log(error.text());
        let errorMessage= error.text();
        if (errorMessage==="Username exist") this.usernameExists=true;
        if (errorMessage==="Email exist") this.emailExists =true;
    }
  )
}

onLogin(){

  this.loginService.getSession().subscribe(
    res=>{
        localStorage.setItem("xAuthToken", res.json().token);
        this.loggedIn = true;
        location.reload();
        this.router.navigate(['/home']);
    },
    err=>{
      console.log(err.text())
      this.loggedIn = false;
      this.loginError=true;
    }
  )
}

onForgetPassword(){

  this.userService.passwordRecovery(this.recoverEmail).subscribe(
    res=>{
      this.emailSent=true;
    },
    err=>{
      console.log(err.text());
    }
  );
}

}
