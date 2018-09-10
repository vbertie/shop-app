import { Component } from '@angular/core';
import {Params, ActivatedRoute, NavigationStart, Router} from '@angular/router';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'app';

  public currentUrlPath:string;


    constructor(public router:Router,
    public route:ActivatedRoute) {
  this.router.events
  .subscribe((event) => {
    if (event instanceof NavigationStart) {
      // Could add more chars url:path?=;other possible
      const urlDelimitators = new RegExp(/[?//,;&:#$+=]/);
      this.currentUrlPath = event.url.slice(1).split(urlDelimitators)[0];
      console.log('URL_PATH: ', this.currentUrlPath);
    }
  });
}
  ngOnInit() {
  }
}
