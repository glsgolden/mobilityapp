package com.simple.mobility.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.simple.mobility.domain.OLTRecord;
import com.simple.mobility.service.OLTRecordService;
import com.simple.mobility.web.rest.errors.BadRequestAlertException;
import com.simple.mobility.web.rest.util.HeaderUtil;
import com.simple.mobility.web.rest.util.PaginationUtil;
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
 * REST controller for managing OLTRecord.
 */
@RestController
@RequestMapping("/api")
public class OLTRecordResource {

    private final Logger log = LoggerFactory.getLogger(OLTRecordResource.class);

    private static final String ENTITY_NAME = "oLTRecord";

    private final OLTRecordService oLTRecordService;

    public OLTRecordResource(OLTRecordService oLTRecordService) {
        this.oLTRecordService = oLTRecordService;
    }

    /**
     * POST  /olt-records : Create a new oLTRecord.
     *
     * @param oLTRecord the oLTRecord to create
     * @return the ResponseEntity with status 201 (Created) and with body the new oLTRecord, or with status 400 (Bad Request) if the oLTRecord has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/olt-records")
    @Timed
    public ResponseEntity<OLTRecord> createOLTRecord(@Valid @RequestBody OLTRecord oLTRecord) throws URISyntaxException {
        log.debug("REST request to save OLTRecord : {}", oLTRecord);
        if (oLTRecord.getId() != null) {
            throw new BadRequestAlertException("A new oLTRecord cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OLTRecord result = oLTRecordService.save(oLTRecord);
        return ResponseEntity.created(new URI("/api/olt-records/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /olt-records : Updates an existing oLTRecord.
     *
     * @param oLTRecord the oLTRecord to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated oLTRecord,
     * or with status 400 (Bad Request) if the oLTRecord is not valid,
     * or with status 500 (Internal Server Error) if the oLTRecord couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/olt-records")
    @Timed
    public ResponseEntity<OLTRecord> updateOLTRecord(@Valid @RequestBody OLTRecord oLTRecord) throws URISyntaxException {
        log.debug("REST request to update OLTRecord : {}", oLTRecord);
        if (oLTRecord.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        OLTRecord result = oLTRecordService.save(oLTRecord);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, oLTRecord.getId().toString()))
            .body(result);
    }

    /**
     * GET  /olt-records : get all the oLTRecords.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of oLTRecords in body
     */
    @GetMapping("/olt-records")
    @Timed
    public ResponseEntity<List<OLTRecord>> getAllOLTRecords(Pageable pageable) {
        log.debug("REST request to get a page of OLTRecords");
        Page<OLTRecord> page = oLTRecordService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/olt-records");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /olt-records/:id : get the "id" oLTRecord.
     *
     * @param id the id of the oLTRecord to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the oLTRecord, or with status 404 (Not Found)
     */
    @GetMapping("/olt-records/{id}")
    @Timed
    public ResponseEntity<OLTRecord> getOLTRecord(@PathVariable Long id) {
        log.debug("REST request to get OLTRecord : {}", id);
        Optional<OLTRecord> oLTRecord = oLTRecordService.findOne(id);
        return ResponseUtil.wrapOrNotFound(oLTRecord);
    }

    /**
     * DELETE  /olt-records/:id : delete the "id" oLTRecord.
     *
     * @param id the id of the oLTRecord to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/olt-records/{id}")
    @Timed
    public ResponseEntity<Void> deleteOLTRecord(@PathVariable Long id) {
        log.debug("REST request to delete OLTRecord : {}", id);
        oLTRecordService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
