import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { OLTRecord } from 'app/shared/model/olt-record.model';
import { OLTRecordService } from './olt-record.service';
import { OLTRecordComponent } from './olt-record.component';
import { OLTRecordDetailComponent } from './olt-record-detail.component';
import { OLTRecordUpdateComponent } from './olt-record-update.component';
import { OLTRecordUploadComponent } from './olt-record-upload.component';
import { OLTRecordDeletePopupComponent } from './olt-record-delete-dialog.component';
import { IOLTRecord } from 'app/shared/model/olt-record.model';

@Injectable({ providedIn: 'root' })
export class OLTRecordResolve implements Resolve<IOLTRecord> {
    constructor(private service: OLTRecordService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((oLTRecord: HttpResponse<OLTRecord>) => oLTRecord.body));
        }
        return of(new OLTRecord());
    }
}

export const oLTRecordRoute: Routes = [
    {
        path: 'olt-record',
        component: OLTRecordComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'OLTRecords'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'olt-record/:id/view',
        component: OLTRecordDetailComponent,
        resolve: {
            oLTRecord: OLTRecordResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'OLTRecords'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'olt-record/new',
        component: OLTRecordUploadComponent,
        resolve: {
            oLTRecord: OLTRecordResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'OLTRecords'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'olt-record/:id/edit',
        component: OLTRecordUpdateComponent,
        resolve: {
            oLTRecord: OLTRecordResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'OLTRecords'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const oLTRecordPopupRoute: Routes = [
    {
        path: 'olt-record/:id/delete',
        component: OLTRecordDeletePopupComponent,
        resolve: {
            oLTRecord: OLTRecordResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'OLTRecords'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
