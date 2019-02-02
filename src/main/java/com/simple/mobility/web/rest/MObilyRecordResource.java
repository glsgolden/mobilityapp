package com.simple.mobility.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.simple.mobility.domain.MObilyRecord;
import com.simple.mobility.service.MObilyRecordService;
import com.simple.mobility.web.rest.errors.BadRequestAlertException;
import com.simple.mobility.web.rest.util.HeaderUtil;
import com.simple.mobility.web.rest.util.PaginationUtil;
import com.simple.mobility.service.dto.MObilyRecordCriteria;
import com.simple.mobility.service.MObilyRecordQueryService;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing MObilyRecord.
 */
@RestController
@RequestMapping("/api")
public class MObilyRecordResource {

    private final Logger log = LoggerFactory.getLogger(MObilyRecordResource.class);

    private static final String ENTITY_NAME = "mObilyRecord";

    private final MObilyRecordService mObilyRecordService;

    private final MObilyRecordQueryService mObilyRecordQueryService;

    public MObilyRecordResource(MObilyRecordService mObilyRecordService, MObilyRecordQueryService mObilyRecordQueryService) {
        this.mObilyRecordService = mObilyRecordService;
        this.mObilyRecordQueryService = mObilyRecordQueryService;
    }

    /**
     * POST  /m-obily-records : Create a new mObilyRecord.
     *
     * @param mObilyRecord the mObilyRecord to create
     * @return the ResponseEntity with status 201 (Created) and with body the new mObilyRecord, or with status 400 (Bad Request) if the mObilyRecord has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/m-obily-records")
    @Timed
    public ResponseEntity<MObilyRecord> createMObilyRecord(@Valid @RequestBody MObilyRecord mObilyRecord) throws URISyntaxException {
        log.debug("REST request to save MObilyRecord : {}", mObilyRecord);
        if (mObilyRecord.getId() != null) {
            throw new BadRequestAlertException("A new mObilyRecord cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MObilyRecord result = mObilyRecordService.save(mObilyRecord);
        return ResponseEntity.created(new URI("/api/m-obily-records/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /m-obily-records : Updates an existing mObilyRecord.
     *
     * @param mObilyRecord the mObilyRecord to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated mObilyRecord,
     * or with status 400 (Bad Request) if the mObilyRecord is not valid,
     * or with status 500 (Internal Server Error) if the mObilyRecord couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/m-obily-records")
    @Timed
    public ResponseEntity<MObilyRecord> updateMObilyRecord(@Valid @RequestBody MObilyRecord mObilyRecord) throws URISyntaxException {
        log.debug("REST request to update MObilyRecord : {}", mObilyRecord);
        if (mObilyRecord.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MObilyRecord result = mObilyRecordService.save(mObilyRecord);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, mObilyRecord.getId().toString()))
            .body(result);
    }

    /**
     * GET  /m-obily-records : get all the mObilyRecords.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of mObilyRecords in body
     */
    @GetMapping("/m-obily-records")
    @Timed
    public ResponseEntity<List<MObilyRecord>> getAllMObilyRecords(MObilyRecordCriteria criteria, Pageable pageable) {
        log.debug("REST request to get MObilyRecords by criteria: {}", criteria);
        Page<MObilyRecord> page = mObilyRecordQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/m-obily-records");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /m-obily-records/:id : get the "id" mObilyRecord.
     *
     * @param id the id of the mObilyRecord to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the mObilyRecord, or with status 404 (Not Found)
     */
    @GetMapping("/m-obily-records/{id}")
    @Timed
    public ResponseEntity<MObilyRecord> getMObilyRecord(@PathVariable Long id) {
        log.debug("REST request to get MObilyRecord : {}", id);
        Optional<MObilyRecord> mObilyRecord = mObilyRecordService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mObilyRecord);
    }

    /**
     * DELETE  /m-obily-records/:id : delete the "id" mObilyRecord.
     *
     * @param id the id of the mObilyRecord to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/m-obily-records/{id}")
    @Timed
    public ResponseEntity<Void> deleteMObilyRecord(@PathVariable Long id) {
        log.debug("REST request to delete MObilyRecord : {}", id);
        mObilyRecordService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
