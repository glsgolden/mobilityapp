import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MobilityappSharedModule } from 'app/shared';
import {
    OLTRecordComponent,
    OLTRecordDetailComponent,
    OLTRecordUpdateComponent,
    OLTRecordUploadComponent,
    OLTRecordDeletePopupComponent,
    OLTRecordDeleteDialogComponent,
    oLTRecordRoute,
    oLTRecordPopupRoute
} from './';

const ENTITY_STATES = [...oLTRecordRoute, ...oLTRecordPopupRoute];

@NgModule({
    imports: [MobilityappSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        OLTRecordComponent,
        OLTRecordDetailComponent,
        OLTRecordUpdateComponent,
        OLTRecordUploadComponent,
        OLTRecordDeleteDialogComponent,
        OLTRecordDeletePopupComponent
    ],
    entryComponents: [OLTRecordComponent, OLTRecordUpdateComponent, OLTRecordDeleteDialogComponent, OLTRecordDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MobilityappOLTRecordModule {}
