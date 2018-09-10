import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ManagePromotionsComponent } from './manage-promotions.component';

describe('ManagePromotionsComponent', () => {
  let component: ManagePromotionsComponent;
  let fixture: ComponentFixture<ManagePromotionsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ManagePromotionsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ManagePromotionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
