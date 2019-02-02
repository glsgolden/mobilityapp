package com.simple.mobility.service;

import com.simple.mobility.domain.MObilyRecord;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing MObilyRecord.
 */
public interface MObilyRecordService {

    /**
     * Save a mObilyRecord.
     *
     * @param mObilyRecord the entity to save
     * @return the persisted entity
     */
    MObilyRecord save(MObilyRecord mObilyRecord);

    /**
     * Get all the mObilyRecords.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<MObilyRecord> findAll(Pageable pageable);


    /**
     * Get the "id" mObilyRecord.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<MObilyRecord> findOne(Long id);

    /**
     * Delete the "id" mObilyRecord.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
