package com.cs.edcSyncAlive.services;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs.edcSyncAlive.constants.ResponseCode;
import com.cs.edcSyncAlive.dao.IMerchantDao;
import com.cs.edcSyncAlive.dao.ITerminalDao;
import com.cs.edcSyncAlive.dto.TerminalDto;
import com.cs.edcSyncAlive.entities.Merchant;
import com.cs.edcSyncAlive.entities.Terminal;
import com.cs.edcSyncAlive.exceptions.AppException;

@Service
public class EdcSyncAliveValidate {

	private Logger logger = LoggerFactory.getLogger(EdcSyncAliveValidate.class);
	
	@Autowired
	private ITerminalDao terminalDao;
	
	@Autowired
	private IMerchantDao merchantDao;
	
	public int validTerminalDB(String terminalId) throws Exception {
		Terminal terminal = terminalDao.getTerminalByTerminalId(terminalId);
		if(terminal!=null && terminal.getId()>0) {
			return terminal.getId();
		}else {
			return 0;
		}
	}
	
	public int validMerchantDB(String merchantId) throws Exception {
		Merchant merchant = merchantDao.findByMerchantId(merchantId);
		if(merchant != null && merchant.getId()>0) {
			return merchant.getId();
		}else {
			return 0;
		}
	}
	
	public void checkMerchantTerminalAssociation(int terminalId, int merchantId) throws Exception {
		List<TerminalDto> dtoList = terminalDao.findDtoByTerminalIdAndMerchantId(terminalId, merchantId);
		if(dtoList == null || dtoList.size()==0) {
			throw new AppException(ResponseCode.TERMINAL_NOTFOUND.getMessage(),ResponseCode.TERMINAL_NOTFOUND.getCode());
		}
	}
	
}
