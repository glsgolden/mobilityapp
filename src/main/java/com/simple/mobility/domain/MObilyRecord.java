package com.simple.mobility.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MObilyRecord.
 */
@Entity
@Table(name = "mobily_record")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MObilyRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "olt", nullable = false)
    private String olt;

    @Column(name = "region")
    private String region;

    @Column(name = "city")
    private String city;

    @Column(name = "olt_type")
    private String oltType;

    @Column(name = "status")
    private String status;

    @Column(name = "fpp")
    private String fpp;

    @Column(name = "odf")
    private String odf;

    @Column(name = "remarks")
    private String remarks;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOlt() {
        return olt;
    }

    public MObilyRecord olt(String olt) {
        this.olt = olt;
        return this;
    }

    public void setOlt(String olt) {
        this.olt = olt;
    }

    public String getRegion() {
        return region;
    }

    public MObilyRecord region(String region) {
        this.region = region;
        return this;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public MObilyRecord city(String city) {
        this.city = city;
        return this;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getOltType() {
        return oltType;
    }

    public MObilyRecord oltType(String oltType) {
        this.oltType = oltType;
        return this;
    }

    public void setOltType(String oltType) {
        this.oltType = oltType;
    }

    public String getStatus() {
        return status;
    }

    public MObilyRecord status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFpp() {
        return fpp;
    }

    public MObilyRecord fpp(String fpp) {
        this.fpp = fpp;
        return this;
    }

    public void setFpp(String fpp) {
        this.fpp = fpp;
    }

    public String getOdf() {
        return odf;
    }

    public MObilyRecord odf(String odf) {
        this.odf = odf;
        return this;
    }

    public void setOdf(String odf) {
        this.odf = odf;
    }

    public String getRemarks() {
        return remarks;
    }

    public MObilyRecord remarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MObilyRecord mObilyRecord = (MObilyRecord) o;
        if (mObilyRecord.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mObilyRecord.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MObilyRecord{" +
            "id=" + getId() +
            ", olt='" + getOlt() + "'" +
            ", region='" + getRegion() + "'" +
            ", city='" + getCity() + "'" +
            ", oltType='" + getOltType() + "'" +
            ", status='" + getStatus() + "'" +
            ", fpp='" + getFpp() + "'" +
            ", odf='" + getOdf() + "'" +
            ", remarks='" + getRemarks() + "'" +
            "}";
    }
}
