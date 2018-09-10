import { Component, OnInit } from '@angular/core';
import {LoginService} from '../../services/LoginService/login.service';
import {Http, Headers } from '@angular/http';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  private credential = {'username' :'', 'password': ''}
  private loggedIn = false;
  private wrongCredentials:boolean;
  private forgotCredentials:boolean;
  constructor(private loginService:LoginService, private http:Http, private router:Router) { }

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

  forgotPassword(){
    this.forgotCredentials=true;
  }

  onSubmit() {
  	this.loginService.sendCredential(this.credential.username, this.credential.password).subscribe(
  		res => {
  			console.log(res);
  			localStorage.setItem("xAuthToken", res.json().token);
  			this.loggedIn = true;
        location.reload();
  		},
  		error => {
        if (error.status == 401){
          this.wrongCredentials = true;
        }
  			console.log(error);
  		}
  	);
  }
}
