import { TestBed } from '@angular/core/testing';

import { TypePostServiceService } from '../type-post-service.service';

describe('TypePostServiceService', () => {
  let service: TypePostServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TypePostServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
