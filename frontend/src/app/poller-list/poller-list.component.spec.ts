import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PollerListComponent } from './poller-list.component';

describe('PollerListComponent', () => {
  let component: PollerListComponent;
  let fixture: ComponentFixture<PollerListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PollerListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PollerListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
