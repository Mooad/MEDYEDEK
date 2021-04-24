import { TestBed } from '@angular/core/testing';

import { ErrorDialogService } from '../services/errordialog.service';

describe('ErrordialogService', () => {
  let service: ErrorDialogService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ErrorDialogService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
