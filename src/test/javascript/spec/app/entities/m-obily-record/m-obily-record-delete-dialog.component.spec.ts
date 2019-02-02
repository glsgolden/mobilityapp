/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { MobilityappTestModule } from '../../../test.module';
import { MObilyRecordDeleteDialogComponent } from 'app/entities/m-obily-record/m-obily-record-delete-dialog.component';
import { MObilyRecordService } from 'app/entities/m-obily-record/m-obily-record.service';

describe('Component Tests', () => {
    describe('MObilyRecord Management Delete Component', () => {
        let comp: MObilyRecordDeleteDialogComponent;
        let fixture: ComponentFixture<MObilyRecordDeleteDialogComponent>;
        let service: MObilyRecordService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [MobilityappTestModule],
                declarations: [MObilyRecordDeleteDialogComponent]
            })
                .overrideTemplate(MObilyRecordDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(MObilyRecordDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MObilyRecordService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
