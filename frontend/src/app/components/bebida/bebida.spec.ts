import { ComponentFixture, TestBed } from '@angular/core/testing';
import { BebidaComponent } from './bebida';

describe('BebidaComponent', () => {
  let component: BebidaComponent;
  let fixture: ComponentFixture<BebidaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BebidaComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(BebidaComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
