import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DeletePromotionFromProductsComponent } from './delete-promotion-from-products.component';

describe('DeletePromotionFromProductsComponent', () => {
  let component: DeletePromotionFromProductsComponent;
  let fixture: ComponentFixture<DeletePromotionFromProductsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DeletePromotionFromProductsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DeletePromotionFromProductsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
