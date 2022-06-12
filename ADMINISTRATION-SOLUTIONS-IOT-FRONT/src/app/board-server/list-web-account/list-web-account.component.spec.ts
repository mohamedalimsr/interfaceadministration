import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListWebAccountComponent } from './list-web-account.component';

describe('ListWebAccountComponent', () => {
  let component: ListWebAccountComponent;
  let fixture: ComponentFixture<ListWebAccountComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListWebAccountComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListWebAccountComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
