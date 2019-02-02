package com.simple.mobility.service;

import com.simple.mobility.domain.OLTRecord;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing OLTRecord.
 */
public interface OLTRecordService {

    /**
     * Save a oLTRecord.
     *
     * @param oLTRecord the entity to save
     * @return the persisted entity
     */
    OLTRecord save(OLTRecord oLTRecord);
    
    /**
     * Save a oLTRecords.
     *
     * @param listof oLTRecord entity to save
     * @return the list of persisted entity
     */
    List<OLTRecord> saveAll(List<OLTRecord> records);

    /**
     * Get all the oLTRecords.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<OLTRecord> findAll(Pageable pageable);


    /**
     * Get the "id" oLTRecord.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<OLTRecord> findOne(Long id);

    /**
     * Delete the "id" oLTRecord.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
