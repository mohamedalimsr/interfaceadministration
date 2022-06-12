import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListServerAccountsComponent } from './list-server-accounts.component';

describe('ListServerAccountsComponent', () => {
  let component: ListServerAccountsComponent;
  let fixture: ComponentFixture<ListServerAccountsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListServerAccountsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListServerAccountsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
