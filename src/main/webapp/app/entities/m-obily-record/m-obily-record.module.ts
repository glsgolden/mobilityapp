import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MobilityappSharedModule } from 'app/shared';
import {
    MObilyRecordComponent,
    MObilyRecordDetailComponent,
    MObilyRecordUpdateComponent,
    MObilyRecordDeletePopupComponent,
    MObilyRecordDeleteDialogComponent,
    mObilyRecordRoute,
    mObilyRecordPopupRoute
} from './';

const ENTITY_STATES = [...mObilyRecordRoute, ...mObilyRecordPopupRoute];

@NgModule({
    imports: [MobilityappSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        MObilyRecordComponent,
        MObilyRecordDetailComponent,
        MObilyRecordUpdateComponent,
        MObilyRecordDeleteDialogComponent,
        MObilyRecordDeletePopupComponent
    ],
    entryComponents: [
        MObilyRecordComponent,
        MObilyRecordUpdateComponent,
        MObilyRecordDeleteDialogComponent,
        MObilyRecordDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MobilityappMObilyRecordModule {}
