/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { MobilityappTestModule } from '../../../test.module';
import { OLTRecordUpdateComponent } from 'app/entities/olt-record/olt-record-update.component';
import { OLTRecordService } from 'app/entities/olt-record/olt-record.service';
import { OLTRecord } from 'app/shared/model/olt-record.model';

describe('Component Tests', () => {
    describe('OLTRecord Management Update Component', () => {
        let comp: OLTRecordUpdateComponent;
        let fixture: ComponentFixture<OLTRecordUpdateComponent>;
        let service: OLTRecordService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [MobilityappTestModule],
                declarations: [OLTRecordUpdateComponent]
            })
                .overrideTemplate(OLTRecordUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(OLTRecordUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(OLTRecordService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new OLTRecord(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.oLTRecord = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new OLTRecord();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.oLTRecord = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
