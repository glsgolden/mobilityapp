import { NgModule } from '@angular/core';

import { MobilityappSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
    imports: [MobilityappSharedLibsModule],
    declarations: [JhiAlertComponent, JhiAlertErrorComponent],
    exports: [MobilityappSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class MobilityappSharedCommonModule {}
