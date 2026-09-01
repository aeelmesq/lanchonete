import { TestBed } from '@angular/core/testing';
import { BebidaService } from './bebida';

describe('BebidaService', () => {
  let service: BebidaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BebidaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
