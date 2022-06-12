import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BoardServerComponent } from './board-server.component';

describe('BoardServerComponent', () => {
  let component: BoardServerComponent;
  let fixture: ComponentFixture<BoardServerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BoardServerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BoardServerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
