/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MobilityappTestModule } from '../../../test.module';
import { OLTRecordDetailComponent } from 'app/entities/olt-record/olt-record-detail.component';
import { OLTRecord } from 'app/shared/model/olt-record.model';

describe('Component Tests', () => {
    describe('OLTRecord Management Detail Component', () => {
        let comp: OLTRecordDetailComponent;
        let fixture: ComponentFixture<OLTRecordDetailComponent>;
        const route = ({ data: of({ oLTRecord: new OLTRecord(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [MobilityappTestModule],
                declarations: [OLTRecordDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(OLTRecordDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(OLTRecordDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.oLTRecord).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
