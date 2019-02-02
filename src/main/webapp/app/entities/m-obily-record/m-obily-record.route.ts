import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { MObilyRecord } from 'app/shared/model/m-obily-record.model';
import { MObilyRecordService } from './m-obily-record.service';
import { MObilyRecordComponent } from './m-obily-record.component';
import { MObilyRecordDetailComponent } from './m-obily-record-detail.component';
import { MObilyRecordUpdateComponent } from './m-obily-record-update.component';
import { MObilyRecordDeletePopupComponent } from './m-obily-record-delete-dialog.component';
import { IMObilyRecord } from 'app/shared/model/m-obily-record.model';

@Injectable({ providedIn: 'root' })
export class MObilyRecordResolve implements Resolve<IMObilyRecord> {
    constructor(private service: MObilyRecordService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((mObilyRecord: HttpResponse<MObilyRecord>) => mObilyRecord.body));
        }
        return of(new MObilyRecord());
    }
}

export const mObilyRecordRoute: Routes = [
    {
        path: 'm-obily-record',
        component: MObilyRecordComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'MObilyRecords'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'm-obily-record/:id/view',
        component: MObilyRecordDetailComponent,
        resolve: {
            mObilyRecord: MObilyRecordResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'MObilyRecords'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'm-obily-record/new',
        component: MObilyRecordUpdateComponent,
        resolve: {
            mObilyRecord: MObilyRecordResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'MObilyRecords'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'm-obily-record/:id/edit',
        component: MObilyRecordUpdateComponent,
        resolve: {
            mObilyRecord: MObilyRecordResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'MObilyRecords'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const mObilyRecordPopupRoute: Routes = [
    {
        path: 'm-obily-record/:id/delete',
        component: MObilyRecordDeletePopupComponent,
        resolve: {
            mObilyRecord: MObilyRecordResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'MObilyRecords'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
