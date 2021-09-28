import { TestBed, inject } from '@angular/core/testing';

import { PollerServiceService } from './poller-service.service';

describe('PollerServiceService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [PollerServiceService]
    });
  });

  it('should be created', inject([PollerServiceService], (service: PollerServiceService) => {
    expect(service).toBeTruthy();
  }));
});
