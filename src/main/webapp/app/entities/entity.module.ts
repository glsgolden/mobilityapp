import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { MobilityappOLTRecordModule } from './olt-record/olt-record.module';
import { MobilityappMObilyRecordModule } from './m-obily-record/m-obily-record.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        MobilityappOLTRecordModule,
        MobilityappMObilyRecordModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MobilityappEntityModule {}
