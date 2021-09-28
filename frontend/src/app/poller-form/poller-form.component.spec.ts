import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PollerFormComponent } from './poller-form.component';

describe('PollerFormComponent', () => {
  let component: PollerFormComponent;
  let fixture: ComponentFixture<PollerFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PollerFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PollerFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
