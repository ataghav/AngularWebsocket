import { TestBed } from '@angular/core/testing';

import { InterCommService } from './inter-comm.service';

describe('InterCommService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: InterCommService = TestBed.get(InterCommService);
    expect(service).toBeTruthy();
  });
});
