package com.cs.edcSyncAlive.dao;

import java.util.List;

import com.cs.edcSyncAlive.dto.RequestParams;
import com.cs.edcSyncAlive.dto.TerminalDto;
import com.cs.edcSyncAlive.entities.Terminal;

public interface ITerminalDao {
	
	public Terminal getTerminalByTerminalId(String terminalId);
//	public void updateTerminal(String terminalId, Date updated);
	 public void updateLastAlive(List<RequestParams> list);
	 public List<TerminalDto> findDtoByTerminalIdAndMerchantId(int terminalId, int merchantId);

}
