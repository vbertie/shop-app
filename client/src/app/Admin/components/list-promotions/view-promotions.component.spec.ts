import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListPromotionsComponent } from './view-promotions.component';

describe('ListPromotionsComponent', () => {
  let component: ListPromotionsComponent;
  let fixture: ComponentFixture<ListPromotionsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListPromotionsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListPromotionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
