export interface IOLTRecord {
    id?: number;
    olt?: string;
    region?: string;
    city?: string;
    oltType?: string;
    status?: string;
    fpp?: string;
    odf?: string;
    remarks?: string;
}

export class OLTRecord implements IOLTRecord {
    constructor(
        public id?: number,
        public olt?: string,
        public region?: string,
        public city?: string,
        public oltType?: string,
        public status?: string,
        public fpp?: string,
        public odf?: string,
        public remarks?: string
    ) {}
}
