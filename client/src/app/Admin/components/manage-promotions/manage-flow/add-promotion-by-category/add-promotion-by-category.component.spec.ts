import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddPromotionByCategoryComponent } from './add-promotion-by-category.component';

describe('AddPromotionByCategoryComponent', () => {
  let component: AddPromotionByCategoryComponent;
  let fixture: ComponentFixture<AddPromotionByCategoryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddPromotionByCategoryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddPromotionByCategoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
