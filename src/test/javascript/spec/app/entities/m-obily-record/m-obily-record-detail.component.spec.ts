/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MobilityappTestModule } from '../../../test.module';
import { MObilyRecordDetailComponent } from 'app/entities/m-obily-record/m-obily-record-detail.component';
import { MObilyRecord } from 'app/shared/model/m-obily-record.model';

describe('Component Tests', () => {
    describe('MObilyRecord Management Detail Component', () => {
        let comp: MObilyRecordDetailComponent;
        let fixture: ComponentFixture<MObilyRecordDetailComponent>;
        const route = ({ data: of({ mObilyRecord: new MObilyRecord(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [MobilityappTestModule],
                declarations: [MObilyRecordDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(MObilyRecordDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(MObilyRecordDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.mObilyRecord).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
