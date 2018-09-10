import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NavBarUserComponent } from './nav-bar-user.component';

describe('NavBarUserComponent', () => {
  let component: NavBarUserComponent;
  let fixture: ComponentFixture<NavBarUserComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NavBarUserComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NavBarUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
