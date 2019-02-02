package com.simple.mobility.service.impl;

import com.simple.mobility.service.OLTRecordService;
import com.simple.mobility.domain.OLTRecord;
import com.simple.mobility.repository.OLTRecordRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
/**
 * Service Implementation for managing OLTRecord.
 */
@Service
@Transactional
public class OLTRecordServiceImpl implements OLTRecordService {

    private final Logger log = LoggerFactory.getLogger(OLTRecordServiceImpl.class);

    private final OLTRecordRepository oLTRecordRepository;

    public OLTRecordServiceImpl(OLTRecordRepository oLTRecordRepository) {
        this.oLTRecordRepository = oLTRecordRepository;
    }

    /**
     * Save a oLTRecord.
     *
     * @param oLTRecord the entity to save
     * @return the persisted entity
     */
    @Override
    public OLTRecord save(OLTRecord oLTRecord) {
        log.debug("Request to save OLTRecord : {}", oLTRecord);        return oLTRecordRepository.save(oLTRecord);
    }
    
    /**
     * Save a oLTRecord.
     *
     * @param oLTRecord the entity to save
     * @return the persisted entity
     */
    @Override
    public List<OLTRecord> saveAll(List<OLTRecord> records) {
        log.debug("Request to save number of OLTRecord : {}", records.size());
        oLTRecordRepository.deleteAll();
        
        return oLTRecordRepository.saveAll(records);
    }

    /**
     * Get all the oLTRecords.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<OLTRecord> findAll(Pageable pageable) {
        log.debug("Request to get all OLTRecords");
        return oLTRecordRepository.findAll(pageable);
    }


    /**
     * Get one oLTRecord by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<OLTRecord> findOne(Long id) {
        log.debug("Request to get OLTRecord : {}", id);
        return oLTRecordRepository.findById(id);
    }

    /**
     * Delete the oLTRecord by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete OLTRecord : {}", id);
        oLTRecordRepository.deleteById(id);
    }
}
