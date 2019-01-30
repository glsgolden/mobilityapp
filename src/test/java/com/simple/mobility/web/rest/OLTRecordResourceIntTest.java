package com.simple.mobility.web.rest;

import com.simple.mobility.MobilityappApp;

import com.simple.mobility.domain.OLTRecord;
import com.simple.mobility.repository.OLTRecordRepository;
import com.simple.mobility.service.OLTRecordService;
import com.simple.mobility.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;


import static com.simple.mobility.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the OLTRecordResource REST controller.
 *
 * @see OLTRecordResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MobilityappApp.class)
public class OLTRecordResourceIntTest {

    private static final String DEFAULT_OLT = "AAAAAAAAAA";
    private static final String UPDATED_OLT = "BBBBBBBBBB";

    private static final String DEFAULT_REGION = "AAAAAAAAAA";
    private static final String UPDATED_REGION = "BBBBBBBBBB";

    private static final String DEFAULT_CITY = "AAAAAAAAAA";
    private static final String UPDATED_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_OLT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_OLT_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_FPP = "AAAAAAAAAA";
    private static final String UPDATED_FPP = "BBBBBBBBBB";

    private static final String DEFAULT_ODF = "AAAAAAAAAA";
    private static final String UPDATED_ODF = "BBBBBBBBBB";

    private static final String DEFAULT_REMARKS = "AAAAAAAAAA";
    private static final String UPDATED_REMARKS = "BBBBBBBBBB";

    @Autowired
    private OLTRecordRepository oLTRecordRepository;

    

    @Autowired
    private OLTRecordService oLTRecordService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restOLTRecordMockMvc;

    private OLTRecord oLTRecord;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final OLTRecordResource oLTRecordResource = new OLTRecordResource(oLTRecordService);
        this.restOLTRecordMockMvc = MockMvcBuilders.standaloneSetup(oLTRecordResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OLTRecord createEntity(EntityManager em) {
        OLTRecord oLTRecord = new OLTRecord()
            .olt(DEFAULT_OLT)
            .region(DEFAULT_REGION)
            .city(DEFAULT_CITY)
            .oltType(DEFAULT_OLT_TYPE)
            .status(DEFAULT_STATUS)
            .fpp(DEFAULT_FPP)
            .odf(DEFAULT_ODF)
            .remarks(DEFAULT_REMARKS);
        return oLTRecord;
    }

    @Before
    public void initTest() {
        oLTRecord = createEntity(em);
    }

    @Test
    @Transactional
    public void createOLTRecord() throws Exception {
        int databaseSizeBeforeCreate = oLTRecordRepository.findAll().size();

        // Create the OLTRecord
        restOLTRecordMockMvc.perform(post("/api/olt-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(oLTRecord)))
            .andExpect(status().isCreated());

        // Validate the OLTRecord in the database
        List<OLTRecord> oLTRecordList = oLTRecordRepository.findAll();
        assertThat(oLTRecordList).hasSize(databaseSizeBeforeCreate + 1);
        OLTRecord testOLTRecord = oLTRecordList.get(oLTRecordList.size() - 1);
        assertThat(testOLTRecord.getOlt()).isEqualTo(DEFAULT_OLT);
        assertThat(testOLTRecord.getRegion()).isEqualTo(DEFAULT_REGION);
        assertThat(testOLTRecord.getCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testOLTRecord.getOltType()).isEqualTo(DEFAULT_OLT_TYPE);
        assertThat(testOLTRecord.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testOLTRecord.getFpp()).isEqualTo(DEFAULT_FPP);
        assertThat(testOLTRecord.getOdf()).isEqualTo(DEFAULT_ODF);
        assertThat(testOLTRecord.getRemarks()).isEqualTo(DEFAULT_REMARKS);
    }

    @Test
    @Transactional
    public void createOLTRecordWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = oLTRecordRepository.findAll().size();

        // Create the OLTRecord with an existing ID
        oLTRecord.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOLTRecordMockMvc.perform(post("/api/olt-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(oLTRecord)))
            .andExpect(status().isBadRequest());

        // Validate the OLTRecord in the database
        List<OLTRecord> oLTRecordList = oLTRecordRepository.findAll();
        assertThat(oLTRecordList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkOltIsRequired() throws Exception {
        int databaseSizeBeforeTest = oLTRecordRepository.findAll().size();
        // set the field null
        oLTRecord.setOlt(null);

        // Create the OLTRecord, which fails.

        restOLTRecordMockMvc.perform(post("/api/olt-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(oLTRecord)))
            .andExpect(status().isBadRequest());

        List<OLTRecord> oLTRecordList = oLTRecordRepository.findAll();
        assertThat(oLTRecordList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllOLTRecords() throws Exception {
        // Initialize the database
        oLTRecordRepository.saveAndFlush(oLTRecord);

        // Get all the oLTRecordList
        restOLTRecordMockMvc.perform(get("/api/olt-records?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(oLTRecord.getId().intValue())))
            .andExpect(jsonPath("$.[*].olt").value(hasItem(DEFAULT_OLT.toString())))
            .andExpect(jsonPath("$.[*].region").value(hasItem(DEFAULT_REGION.toString())))
            .andExpect(jsonPath("$.[*].city").value(hasItem(DEFAULT_CITY.toString())))
            .andExpect(jsonPath("$.[*].oltType").value(hasItem(DEFAULT_OLT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].fpp").value(hasItem(DEFAULT_FPP.toString())))
            .andExpect(jsonPath("$.[*].odf").value(hasItem(DEFAULT_ODF.toString())))
            .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS.toString())));
    }
    

    @Test
    @Transactional
    public void getOLTRecord() throws Exception {
        // Initialize the database
        oLTRecordRepository.saveAndFlush(oLTRecord);

        // Get the oLTRecord
        restOLTRecordMockMvc.perform(get("/api/olt-records/{id}", oLTRecord.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(oLTRecord.getId().intValue()))
            .andExpect(jsonPath("$.olt").value(DEFAULT_OLT.toString()))
            .andExpect(jsonPath("$.region").value(DEFAULT_REGION.toString()))
            .andExpect(jsonPath("$.city").value(DEFAULT_CITY.toString()))
            .andExpect(jsonPath("$.oltType").value(DEFAULT_OLT_TYPE.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.fpp").value(DEFAULT_FPP.toString()))
            .andExpect(jsonPath("$.odf").value(DEFAULT_ODF.toString()))
            .andExpect(jsonPath("$.remarks").value(DEFAULT_REMARKS.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingOLTRecord() throws Exception {
        // Get the oLTRecord
        restOLTRecordMockMvc.perform(get("/api/olt-records/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOLTRecord() throws Exception {
        // Initialize the database
        oLTRecordService.save(oLTRecord);

        int databaseSizeBeforeUpdate = oLTRecordRepository.findAll().size();

        // Update the oLTRecord
        OLTRecord updatedOLTRecord = oLTRecordRepository.findById(oLTRecord.getId()).get();
        // Disconnect from session so that the updates on updatedOLTRecord are not directly saved in db
        em.detach(updatedOLTRecord);
        updatedOLTRecord
            .olt(UPDATED_OLT)
            .region(UPDATED_REGION)
            .city(UPDATED_CITY)
            .oltType(UPDATED_OLT_TYPE)
            .status(UPDATED_STATUS)
            .fpp(UPDATED_FPP)
            .odf(UPDATED_ODF)
            .remarks(UPDATED_REMARKS);

        restOLTRecordMockMvc.perform(put("/api/olt-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedOLTRecord)))
            .andExpect(status().isOk());

        // Validate the OLTRecord in the database
        List<OLTRecord> oLTRecordList = oLTRecordRepository.findAll();
        assertThat(oLTRecordList).hasSize(databaseSizeBeforeUpdate);
        OLTRecord testOLTRecord = oLTRecordList.get(oLTRecordList.size() - 1);
        assertThat(testOLTRecord.getOlt()).isEqualTo(UPDATED_OLT);
        assertThat(testOLTRecord.getRegion()).isEqualTo(UPDATED_REGION);
        assertThat(testOLTRecord.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testOLTRecord.getOltType()).isEqualTo(UPDATED_OLT_TYPE);
        assertThat(testOLTRecord.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testOLTRecord.getFpp()).isEqualTo(UPDATED_FPP);
        assertThat(testOLTRecord.getOdf()).isEqualTo(UPDATED_ODF);
        assertThat(testOLTRecord.getRemarks()).isEqualTo(UPDATED_REMARKS);
    }

    @Test
    @Transactional
    public void updateNonExistingOLTRecord() throws Exception {
        int databaseSizeBeforeUpdate = oLTRecordRepository.findAll().size();

        // Create the OLTRecord

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restOLTRecordMockMvc.perform(put("/api/olt-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(oLTRecord)))
            .andExpect(status().isBadRequest());

        // Validate the OLTRecord in the database
        List<OLTRecord> oLTRecordList = oLTRecordRepository.findAll();
        assertThat(oLTRecordList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOLTRecord() throws Exception {
        // Initialize the database
        oLTRecordService.save(oLTRecord);

        int databaseSizeBeforeDelete = oLTRecordRepository.findAll().size();

        // Get the oLTRecord
        restOLTRecordMockMvc.perform(delete("/api/olt-records/{id}", oLTRecord.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<OLTRecord> oLTRecordList = oLTRecordRepository.findAll();
        assertThat(oLTRecordList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OLTRecord.class);
        OLTRecord oLTRecord1 = new OLTRecord();
        oLTRecord1.setId(1L);
        OLTRecord oLTRecord2 = new OLTRecord();
        oLTRecord2.setId(oLTRecord1.getId());
        assertThat(oLTRecord1).isEqualTo(oLTRecord2);
        oLTRecord2.setId(2L);
        assertThat(oLTRecord1).isNotEqualTo(oLTRecord2);
        oLTRecord1.setId(null);
        assertThat(oLTRecord1).isNotEqualTo(oLTRecord2);
    }
}
