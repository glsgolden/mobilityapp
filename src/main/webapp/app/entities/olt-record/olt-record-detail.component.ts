import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IOLTRecord } from 'app/shared/model/olt-record.model';

@Component({
    selector: 'jhi-olt-record-detail',
    templateUrl: './olt-record-detail.component.html'
})
export class OLTRecordDetailComponent implements OnInit {
    oLTRecord: IOLTRecord;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ oLTRecord }) => {
            this.oLTRecord = oLTRecord;
        });
    }

    previousState() {
        window.history.back();
    }
}
