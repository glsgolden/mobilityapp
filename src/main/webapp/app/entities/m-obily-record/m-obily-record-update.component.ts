import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IMObilyRecord } from 'app/shared/model/m-obily-record.model';
import { MObilyRecordService } from './m-obily-record.service';

@Component({
    selector: 'jhi-m-obily-record-update',
    templateUrl: './m-obily-record-update.component.html'
})
export class MObilyRecordUpdateComponent implements OnInit {
    private _mObilyRecord: IMObilyRecord;
    isSaving: boolean;

    constructor(private mObilyRecordService: MObilyRecordService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ mObilyRecord }) => {
            this.mObilyRecord = mObilyRecord;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.mObilyRecord.id !== undefined) {
            this.subscribeToSaveResponse(this.mObilyRecordService.update(this.mObilyRecord));
        } else {
            this.subscribeToSaveResponse(this.mObilyRecordService.create(this.mObilyRecord));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IMObilyRecord>>) {
        result.subscribe((res: HttpResponse<IMObilyRecord>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get mObilyRecord() {
        return this._mObilyRecord;
    }

    set mObilyRecord(mObilyRecord: IMObilyRecord) {
        this._mObilyRecord = mObilyRecord;
    }
}
