import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EtatBoitiersComponent } from './etat-boitiers.component';

describe('EtatBoitiersComponent', () => {
  let component: EtatBoitiersComponent;
  let fixture: ComponentFixture<EtatBoitiersComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EtatBoitiersComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EtatBoitiersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
