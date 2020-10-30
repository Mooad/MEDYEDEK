import { TestBed } from '@angular/core/testing';
import { LoginproxyService } from '../services/loginproxy.service';


describe('LoginproxyService', () => {
  let service: LoginproxyService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LoginproxyService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
