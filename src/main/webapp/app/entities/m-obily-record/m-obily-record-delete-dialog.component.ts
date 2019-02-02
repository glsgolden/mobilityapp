import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMObilyRecord } from 'app/shared/model/m-obily-record.model';
import { MObilyRecordService } from './m-obily-record.service';

@Component({
    selector: 'jhi-m-obily-record-delete-dialog',
    templateUrl: './m-obily-record-delete-dialog.component.html'
})
export class MObilyRecordDeleteDialogComponent {
    mObilyRecord: IMObilyRecord;

    constructor(
        private mObilyRecordService: MObilyRecordService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.mObilyRecordService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'mObilyRecordListModification',
                content: 'Deleted an mObilyRecord'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-m-obily-record-delete-popup',
    template: ''
})
export class MObilyRecordDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ mObilyRecord }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(MObilyRecordDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.mObilyRecord = mObilyRecord;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
