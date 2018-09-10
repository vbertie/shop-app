import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ManageFlowComponent } from './manage-flow.component';

describe('ManageFlowComponent', () => {
  let component: ManageFlowComponent;
  let fixture: ComponentFixture<ManageFlowComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ManageFlowComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ManageFlowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
