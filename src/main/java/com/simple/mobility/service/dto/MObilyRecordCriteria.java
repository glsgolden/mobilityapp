package com.simple.mobility.service.dto;

import java.io.Serializable;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;






/**
 * Criteria class for the MObilyRecord entity. This class is used in MObilyRecordResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /m-obily-records?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MObilyRecordCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private StringFilter olt;

    private StringFilter region;

    private StringFilter city;

    private StringFilter oltType;

    private StringFilter status;

    private StringFilter fpp;

    private StringFilter odf;

    private StringFilter remarks;

    public MObilyRecordCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getOlt() {
        return olt;
    }

    public void setOlt(StringFilter olt) {
        this.olt = olt;
    }

    public StringFilter getRegion() {
        return region;
    }

    public void setRegion(StringFilter region) {
        this.region = region;
    }

    public StringFilter getCity() {
        return city;
    }

    public void setCity(StringFilter city) {
        this.city = city;
    }

    public StringFilter getOltType() {
        return oltType;
    }

    public void setOltType(StringFilter oltType) {
        this.oltType = oltType;
    }

    public StringFilter getStatus() {
        return status;
    }

    public void setStatus(StringFilter status) {
        this.status = status;
    }

    public StringFilter getFpp() {
        return fpp;
    }

    public void setFpp(StringFilter fpp) {
        this.fpp = fpp;
    }

    public StringFilter getOdf() {
        return odf;
    }

    public void setOdf(StringFilter odf) {
        this.odf = odf;
    }

    public StringFilter getRemarks() {
        return remarks;
    }

    public void setRemarks(StringFilter remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "MObilyRecordCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (olt != null ? "olt=" + olt + ", " : "") +
                (region != null ? "region=" + region + ", " : "") +
                (city != null ? "city=" + city + ", " : "") +
                (oltType != null ? "oltType=" + oltType + ", " : "") +
                (status != null ? "status=" + status + ", " : "") +
                (fpp != null ? "fpp=" + fpp + ", " : "") +
                (odf != null ? "odf=" + odf + ", " : "") +
                (remarks != null ? "remarks=" + remarks + ", " : "") +
            "}";
    }

}
