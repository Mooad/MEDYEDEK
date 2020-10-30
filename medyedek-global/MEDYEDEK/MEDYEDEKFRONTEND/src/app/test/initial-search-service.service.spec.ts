import { TestBed } from '@angular/core/testing';

import { InitialSearchServiceService } from './initial-search-service.service';

describe('InitialSearchServiceService', () => {
  let service: InitialSearchServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(InitialSearchServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
