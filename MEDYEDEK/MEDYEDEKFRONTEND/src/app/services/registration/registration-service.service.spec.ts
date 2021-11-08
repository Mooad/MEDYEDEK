import { TestBed } from '@angular/core/testing';

import { InscriptionService } from './registration-service.service';

describe('RegistrationServiceService', () => {
  let service: InscriptionService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(InscriptionService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
