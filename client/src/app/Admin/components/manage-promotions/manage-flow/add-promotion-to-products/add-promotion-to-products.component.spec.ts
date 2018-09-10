import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddPromotionToProductsComponent } from './add-promotion-to-products.component';

describe('AddPromotionToProductsComponent', () => {
  let component: AddPromotionToProductsComponent;
  let fixture: ComponentFixture<AddPromotionToProductsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddPromotionToProductsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddPromotionToProductsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
