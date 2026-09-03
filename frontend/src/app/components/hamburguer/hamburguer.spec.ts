import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HamburguerComponent } from './hamburguer';

describe('HamburguerComponent', () => {
  let component: HamburguerComponent;
  let fixture: ComponentFixture<HamburguerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HamburguerComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(HamburguerComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
