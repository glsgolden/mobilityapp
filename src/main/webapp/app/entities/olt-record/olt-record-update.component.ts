import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IOLTRecord } from 'app/shared/model/olt-record.model';
import { OLTRecordService } from './olt-record.service';

@Component({
    selector: 'jhi-olt-record-update',
    templateUrl: './olt-record-update.component.html'
})
export class OLTRecordUpdateComponent implements OnInit {
    private _oLTRecord: IOLTRecord;
    isSaving: boolean;

    constructor(private oLTRecordService: OLTRecordService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ oLTRecord }) => {
            this.oLTRecord = oLTRecord;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.oLTRecord.id !== undefined) {
            this.subscribeToSaveResponse(this.oLTRecordService.update(this.oLTRecord));
        } else {
            this.subscribeToSaveResponse(this.oLTRecordService.create(this.oLTRecord));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IOLTRecord>>) {
        result.subscribe((res: HttpResponse<IOLTRecord>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get oLTRecord() {
        return this._oLTRecord;
    }

    set oLTRecord(oLTRecord: IOLTRecord) {
        this._oLTRecord = oLTRecord;
    }
}
