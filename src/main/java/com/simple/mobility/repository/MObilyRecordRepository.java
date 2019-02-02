package com.simple.mobility.repository;

import com.simple.mobility.domain.MObilyRecord;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MObilyRecord entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MObilyRecordRepository extends JpaRepository<MObilyRecord, Long>, JpaSpecificationExecutor<MObilyRecord> {

}
