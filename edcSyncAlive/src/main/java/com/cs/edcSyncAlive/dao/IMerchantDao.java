package com.cs.edcSyncAlive.dao;

import com.cs.edcSyncAlive.entities.Merchant;

public interface IMerchantDao {
	
	public Merchant findByMerchantId(String merchantId) throws Exception;

}
