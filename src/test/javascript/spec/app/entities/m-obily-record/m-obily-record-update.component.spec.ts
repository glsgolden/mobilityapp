/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { MobilityappTestModule } from '../../../test.module';
import { MObilyRecordUpdateComponent } from 'app/entities/m-obily-record/m-obily-record-update.component';
import { MObilyRecordService } from 'app/entities/m-obily-record/m-obily-record.service';
import { MObilyRecord } from 'app/shared/model/m-obily-record.model';

describe('Component Tests', () => {
    describe('MObilyRecord Management Update Component', () => {
        let comp: MObilyRecordUpdateComponent;
        let fixture: ComponentFixture<MObilyRecordUpdateComponent>;
        let service: MObilyRecordService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [MobilityappTestModule],
                declarations: [MObilyRecordUpdateComponent]
            })
                .overrideTemplate(MObilyRecordUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(MObilyRecordUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MObilyRecordService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new MObilyRecord(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.mObilyRecord = entity;
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
                    const entity = new MObilyRecord();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.mObilyRecord = entity;
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
