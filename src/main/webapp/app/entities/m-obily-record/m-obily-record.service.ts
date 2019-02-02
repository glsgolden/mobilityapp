import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMObilyRecord } from 'app/shared/model/m-obily-record.model';

type EntityResponseType = HttpResponse<IMObilyRecord>;
type EntityArrayResponseType = HttpResponse<IMObilyRecord[]>;

@Injectable({ providedIn: 'root' })
export class MObilyRecordService {
    private resourceUrl = SERVER_API_URL + 'api/m-obily-records';

    constructor(private http: HttpClient) {}

    create(mObilyRecord: IMObilyRecord): Observable<EntityResponseType> {
        return this.http.post<IMObilyRecord>(this.resourceUrl, mObilyRecord, { observe: 'response' });
    }

    update(mObilyRecord: IMObilyRecord): Observable<EntityResponseType> {
        return this.http.put<IMObilyRecord>(this.resourceUrl, mObilyRecord, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IMObilyRecord>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IMObilyRecord[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
