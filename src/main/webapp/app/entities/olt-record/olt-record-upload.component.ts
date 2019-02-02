import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable, Subscription } from 'rxjs';

import { IOLTRecord } from 'app/shared/model/olt-record.model';
import { OLTRecordService } from './olt-record.service';

@Component({
    selector: 'jhi-olt-record-upload',
    templateUrl: './olt-record-upload.component.html'
})
export class OLTRecordUploadComponent implements OnInit {
    private _oLTRecord: IOLTRecord;
    isSaving: boolean;
    currentFileUpload: File;
    selectedFiles: FileList;

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

    upload() {
        this.isSaving = true;
        this.currentFileUpload = this.selectedFiles.item(0);

        this.subscribeToSaveResponse(this.oLTRecordService.uploadFile(this.currentFileUpload));
        /* this.oLTRecordService.uploadFile(this.currentFileUpload).subscribe(event => {
            if (event instanceof HttpResponse) {
                console.log('File in completely uploaded');
            }
        }); */
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IOLTRecord[]>>) {
        result.subscribe((res: HttpResponse<IOLTRecord[]>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    onFileChange(event) {
        this.selectedFiles = event.target.files;
    }
}
