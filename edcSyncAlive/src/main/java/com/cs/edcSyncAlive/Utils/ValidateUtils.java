package com.cs.edcSyncAlive.Utils;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cs.edcSyncAlive.constants.ResponseCode;
import com.cs.edcSyncAlive.exceptions.AppException;



public class ValidateUtils {

	public static Logger logger = LoggerFactory.getLogger(ValidateUtils.class);

	

	public static boolean isValidTerminalId(String terminalId) throws Exception {
//		logger.info("validate:terminalId="+terminalId);
		if (terminalId==null) {
			// terminalId is required!
			throw new AppException(ResponseCode.VALIDATE_TERMINALID_REQUIRED.getMessage(),
					ResponseCode.VALIDATE_TERMINALID_REQUIRED.getCode());
		} else if (terminalId.length() != 8) {
			// terminalId must be length equals 8
			throw new AppException(ResponseCode.VALIDATE_TERMINALID_LENGTH.getMessage(),
					ResponseCode.VALIDATE_TERMINALID_LENGTH.getCode());
		} else if (!terminalId.matches("\\d+")) {
			// terminalId must be a number
			throw new AppException(ResponseCode.VALIDATE_TERMINALID_NUMBER.getMessage(),
					ResponseCode.VALIDATE_TERMINALID_NUMBER.getCode());
		}
		return true;
	}

	public static boolean isValidTransactionId(String transactionId) throws Exception {
//		logger.info("validate:transactionId="+transactionId);
		if (transactionId.isEmpty()) {
			// transactionId is required!
			throw new AppException(ResponseCode.VALIDATE_TRANSACTION_ID_REQUIRED.getMessage(),
					ResponseCode.VALIDATE_TRANSACTION_ID_REQUIRED.getCode());
		} else if (transactionId.length() > 26) {
			// transactionId must be length equals 26
			throw new AppException(ResponseCode.VALIDATE_TRANSACTION_ID_LENGTH.getMessage(),
					ResponseCode.VALIDATE_TRANSACTION_ID_LENGTH.getCode());
		} 
		return true;
	}
	
	public static boolean isValidMerchantId(String merchantId) throws Exception {
//		logger.info("validate:merchantId="+merchantId);
		if (merchantId==null || merchantId.isBlank() || merchantId.isEmpty()) {
			// merchantId is required!
			throw new AppException(ResponseCode.VALIDATE_MERCHANTID_REQUIRED.getMessage(),
					ResponseCode.VALIDATE_MERCHANTID_REQUIRED.getCode());
		} else if (merchantId.length() != 15) {
			// merchantId must be length equals 15
			throw new AppException(ResponseCode.VALIDATE_MERCHANTID_LENGTH.getMessage(),
					ResponseCode.VALIDATE_MERCHANTID_LENGTH.getCode());
		} else if (!merchantId.matches("\\d+")) {
			// merchantId must be a number
			throw new AppException(ResponseCode.VALIDATE_MERCHANTID_NUMBER.getMessage(),
					ResponseCode.VALIDATE_MERCHANTID_NUMBER.getCode());
		}
		return true;
	}
	
}
