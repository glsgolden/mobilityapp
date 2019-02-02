import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMObilyRecord } from 'app/shared/model/m-obily-record.model';

@Component({
    selector: 'jhi-m-obily-record-detail',
    templateUrl: './m-obily-record-detail.component.html'
})
export class MObilyRecordDetailComponent implements OnInit {
    mObilyRecord: IMObilyRecord;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ mObilyRecord }) => {
            this.mObilyRecord = mObilyRecord;
        });
    }

    previousState() {
        window.history.back();
    }
}
