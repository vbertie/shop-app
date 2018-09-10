import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewPromotionComponent } from './view-promotion.component';

describe('ViewPromotionComponent', () => {
  let component: ViewPromotionComponent;
  let fixture: ComponentFixture<ViewPromotionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewPromotionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewPromotionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
