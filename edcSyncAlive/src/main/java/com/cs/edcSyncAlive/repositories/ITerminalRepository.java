package com.cs.edcSyncAlive.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cs.edcSyncAlive.dto.TerminalDto;
import com.cs.edcSyncAlive.entities.Terminal;

@Repository
public interface ITerminalRepository extends JpaRepository<Terminal, Integer>{
	
	@Query("SELECT t FROM Terminal t where t.terminalId = :terminalId order by t.updatedDate desc limit 1")
	public Terminal getTerminalByTerminalId(@Param("terminalId") String terminalId);

	
//	@Query("Update Terminal t where set t.terminalId = :terminalId ,t.lastAlive= :updatedDate where t.id= :id ")
//	public void updateTerminal(@Param("terminalId") String terminalId, @Param("updatedDate") Date updated, @Param("id") int id);
	
	final String dtoColumn = "	new com.cs.edcSyncAlive.dto.TerminalDto(t.id,"
			+ "	t.merchantId,"
			+ "	t.terminalId,"
			+ "	t.terminalModelId ,"
			+ "	t.posNo,"
			+ "	t.serialId,"
			+ "	t.parameterCurrentVer,"
			+ "	t.parameterExpectVer,"
			+ "	t.operationFlag,"
			+ "	t.rejectReason,"
			+ "	t.applicationStatus,"
			+ "	t.startDate,"
			+ "	t.endDate,"
			+ "	t.lastTxDate,"
			+ "	t.importType,"
			+ "	t.logFlag,"
			+ "	t.approveById,"
			+ "	t.approveDate,"
			+ "	t.actionForm,"
			+ "	t.createdById,"
			+ "	t.createdDate,"
			+ "	t.updatedById,"
			+ "	t.updatedDate,"
			+ " t.firstInit,"
			+ " t.updatedFirstInitName,"
			+ " t.updatedFirstInitDate"
			+ ") ";
	@Query("SELECT "
			+ dtoColumn
			+ " FROM Terminal t "
			+ " LEFT JOIN Merchant m ON m.id = t.merchantId  "
			+ " WHERE t.id = :terminalId "
			+ " AND m.id = :merchantId ")
	List<TerminalDto> findDtoByTerminalIdAndMerchantId(@Param("terminalId") int terminalId, @Param("merchantId") int merchantId);
}
