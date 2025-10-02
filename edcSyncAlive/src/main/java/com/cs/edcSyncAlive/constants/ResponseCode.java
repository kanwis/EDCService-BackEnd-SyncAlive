package com.cs.edcSyncAlive.constants;



import java.text.MessageFormat;

public enum ResponseCode {
	EXCEPTION("9999", "{0}"), 
	SUCCESS("0001", "Success"), 
	INVALID("0002", "{0} is invalid."),
	NOT_FOUND("0003", "Not found terminalId or merchantId."), 
	CORRUPTED("0004", "File was corrupted."),
	DUPPLICATE_USERNAME_EMAIL("0005", "This Username or Email already been used, please try another"),

	// terminalId
	VALIDATE_TERMINALID_REQUIRED("0013", "terminalId is required"),
	VALIDATE_TERMINALID_LENGTH("0014", "terminalId must be length equals 8"),
	VALIDATE_TERMINALID_NUMBER("0015", "terminalId must be a number"),

	// merchantId
	VALIDATE_MERCHANTID_REQUIRED("0016", "merchantId is required"),
	VALIDATE_MERCHANTID_LENGTH("0017", "merchantId must be length equals 15"),
	VALIDATE_MERCHANTID_NUMBER("0018", "merchantId must be a number"),

	// transactionId
	VALIDATE_TRANSACTION_ID_REQUIRED("0019", "transactionId is required"),
	VALIDATE_TRANSACTION_ID_LENGTH("0020", "transactionId maximum length is 26"),

	// terminalParam
	VALIDATE_TERMINAL_PARAM_REQUIRED("0021", "terminalParam is required"),
	VALIDATE_TERMINAL_PARAM_LENGTH("0022", "terminalParam maximum length is 12"),
	VALIDATE_TERMINAL_PARAM_NUMBER("0023", "terminalParam must be a number"),

	TERMINAL_NOTFOUND("0008", "Not found terminalId"),
	// registerDate
//	VALIDATE_REGISTER_DATE_REQUIRED("0068", "registerDate is required"),
//	VALIDATE_REGISTER_DATE_LENGTH("0069", "registerDate maximum length is 19"),
//	VALIDATE_REGISTER_DATE_FORMAT("0070", "registerDate must be format YYYY-MM-DD"),// hh:mm:ss

	// appType
	VALIDATE_APP_TYPE_REQUIRED("0068", "appType is required"),
	VALIDATE_APP_TYPE_LENGTH("0069", "appType maximum length is 2"),
	VALIDATE_APP_TYPE_NUMBER("0070", "appType must be a number"),
	
	VALIDATE_DATA_REQUIRED("0071", "dataUsage is required"),
	VALIDATE_FUNCTION_NAME_REQUIRED("0072", "function_name is required"),
	VALIDATE_COUNT_USAGE_REQUIRED("0073", "count_usage is required"),
	VALIDATE_COUNT_USAGE_NUMBER("0074", "count_usage must be a number"),
	
	VALIDATE_USAGE_DATE_REQUIRED("0075", "usage_date is required"),
	VALIDATE_FORMAT_USAGE_NUMBER("0076", "format count_usage is invalid"),
	VALIDATE_DATE_USAGE_NUMBER("0077", "{0}"),
	
	VALIDATE_FUNCTION_NAME_MATCH("0005", "Not match EDC function name"),

	PERMISSION("1003", "You don’t have permission to access this resource."),
	CONNECT("2000", "{0} or Unable to connect to the database."),
	UNAVAILABLE("2001", "The database service is currently unavailable."), 
	DB_EXCEPTION("2002", "{0}"),
	REQUEST_NOT_FOUND("4004", "The request is not found."),
	SERVER_TIMEOUT("5004", "The server took too long to process the request.");

	private ResponseCode(String code, String message) {
		this.code = code;
		this.message = message;
	}

	private final String code;
	private final String message;

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public String formatMessage(Object... args) {
		return MessageFormat.format(message, args);
	}
}
