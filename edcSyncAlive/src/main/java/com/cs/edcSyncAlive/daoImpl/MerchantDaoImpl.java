package com.cs.edcSyncAlive.daoImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs.edcSyncAlive.dao.IMerchantDao;
import com.cs.edcSyncAlive.entities.Merchant;
import com.cs.edcSyncAlive.repositories.IMerchantRepository;


@Service
public class MerchantDaoImpl implements IMerchantDao{

	@Autowired
	private IMerchantRepository repo;
	
	@Override
	public Merchant findByMerchantId(String merchantId) throws Exception {
		return repo.findByMerchantId(merchantId);
	}

}
