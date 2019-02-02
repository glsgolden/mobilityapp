import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse, HttpEvent, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IOLTRecord } from 'app/shared/model/olt-record.model';

type EntityResponseType = HttpResponse<IOLTRecord>;
type EntityArrayResponseType = HttpResponse<IOLTRecord[]>;

@Injectable({ providedIn: 'root' })
export class OLTRecordService {
    private resourceUrl = SERVER_API_URL + 'api/olt-records';

    constructor(private http: HttpClient) {}

    create(oLTRecord: IOLTRecord): Observable<EntityResponseType> {
        return this.http.post<IOLTRecord>(this.resourceUrl, oLTRecord, { observe: 'response' });
    }

    update(oLTRecord: IOLTRecord): Observable<EntityResponseType> {
        return this.http.put<IOLTRecord>(this.resourceUrl, oLTRecord, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IOLTRecord>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IOLTRecord[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    uploadFile(file: File): Observable<EntityArrayResponseType> {
        const formdata: FormData = new FormData();
        formdata.append('file', file);

        return this.http.post<IOLTRecord[]>(`${this.resourceUrl}/file`, formdata, { observe: 'response'});
        /*
        const req = new HttpRequest('POST', `${this.resourceUrl}/file`, formdata, {
            reportProgress: true,
            responseType: 'text'
        });
        return this.http.request(req);
        */
    }
}
