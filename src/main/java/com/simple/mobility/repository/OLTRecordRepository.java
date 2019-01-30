package com.simple.mobility.repository;

import com.simple.mobility.domain.OLTRecord;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the OLTRecord entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OLTRecordRepository extends JpaRepository<OLTRecord, Long> {

}
