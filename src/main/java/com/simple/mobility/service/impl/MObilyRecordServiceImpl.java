package com.simple.mobility.service.impl;

import com.simple.mobility.service.MObilyRecordService;
import com.simple.mobility.domain.MObilyRecord;
import com.simple.mobility.repository.MObilyRecordRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing MObilyRecord.
 */
@Service
@Transactional
public class MObilyRecordServiceImpl implements MObilyRecordService {

    private final Logger log = LoggerFactory.getLogger(MObilyRecordServiceImpl.class);

    private final MObilyRecordRepository mObilyRecordRepository;

    public MObilyRecordServiceImpl(MObilyRecordRepository mObilyRecordRepository) {
        this.mObilyRecordRepository = mObilyRecordRepository;
    }

    /**
     * Save a mObilyRecord.
     *
     * @param mObilyRecord the entity to save
     * @return the persisted entity
     */
    @Override
    public MObilyRecord save(MObilyRecord mObilyRecord) {
        log.debug("Request to save MObilyRecord : {}", mObilyRecord);        return mObilyRecordRepository.save(mObilyRecord);
    }

    /**
     * Get all the mObilyRecords.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MObilyRecord> findAll(Pageable pageable) {
        log.debug("Request to get all MObilyRecords");
        return mObilyRecordRepository.findAll(pageable);
    }


    /**
     * Get one mObilyRecord by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<MObilyRecord> findOne(Long id) {
        log.debug("Request to get MObilyRecord : {}", id);
        return mObilyRecordRepository.findById(id);
    }

    /**
     * Delete the mObilyRecord by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MObilyRecord : {}", id);
        mObilyRecordRepository.deleteById(id);
    }
}
