package com.simple.mobility.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.simple.mobility.domain.MObilyRecord;
import com.simple.mobility.domain.*; // for static metamodels
import com.simple.mobility.repository.MObilyRecordRepository;
import com.simple.mobility.service.dto.MObilyRecordCriteria;


/**
 * Service for executing complex queries for MObilyRecord entities in the database.
 * The main input is a {@link MObilyRecordCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MObilyRecord} or a {@link Page} of {@link MObilyRecord} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MObilyRecordQueryService extends QueryService<MObilyRecord> {

    private final Logger log = LoggerFactory.getLogger(MObilyRecordQueryService.class);

    private final MObilyRecordRepository mObilyRecordRepository;

    public MObilyRecordQueryService(MObilyRecordRepository mObilyRecordRepository) {
        this.mObilyRecordRepository = mObilyRecordRepository;
    }

    /**
     * Return a {@link List} of {@link MObilyRecord} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MObilyRecord> findByCriteria(MObilyRecordCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MObilyRecord> specification = createSpecification(criteria);
        return mObilyRecordRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link MObilyRecord} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MObilyRecord> findByCriteria(MObilyRecordCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MObilyRecord> specification = createSpecification(criteria);
        return mObilyRecordRepository.findAll(specification, page);
    }

    /**
     * Function to convert MObilyRecordCriteria to a {@link Specification}
     */
    private Specification<MObilyRecord> createSpecification(MObilyRecordCriteria criteria) {
        Specification<MObilyRecord> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MObilyRecord_.id));
            }
            if (criteria.getOlt() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOlt(), MObilyRecord_.olt));
            }
            if (criteria.getRegion() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRegion(), MObilyRecord_.region));
            }
            if (criteria.getCity() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCity(), MObilyRecord_.city));
            }
            if (criteria.getOltType() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOltType(), MObilyRecord_.oltType));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStatus(), MObilyRecord_.status));
            }
            if (criteria.getFpp() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFpp(), MObilyRecord_.fpp));
            }
            if (criteria.getOdf() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOdf(), MObilyRecord_.odf));
            }
            if (criteria.getRemarks() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRemarks(), MObilyRecord_.remarks));
            }
        }
        return specification;
    }

}
