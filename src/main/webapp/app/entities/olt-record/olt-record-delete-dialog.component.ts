import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IOLTRecord } from 'app/shared/model/olt-record.model';
import { OLTRecordService } from './olt-record.service';

@Component({
    selector: 'jhi-olt-record-delete-dialog',
    templateUrl: './olt-record-delete-dialog.component.html'
})
export class OLTRecordDeleteDialogComponent {
    oLTRecord: IOLTRecord;

    constructor(private oLTRecordService: OLTRecordService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.oLTRecordService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'oLTRecordListModification',
                content: 'Deleted an oLTRecord'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-olt-record-delete-popup',
    template: ''
})
export class OLTRecordDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ oLTRecord }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(OLTRecordDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.oLTRecord = oLTRecord;
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
