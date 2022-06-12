import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListWebAccountsComponent } from './list-web-accounts.component';

describe('ListWebAccountsComponent', () => {
  let component: ListWebAccountsComponent;
  let fixture: ComponentFixture<ListWebAccountsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListWebAccountsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListWebAccountsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
