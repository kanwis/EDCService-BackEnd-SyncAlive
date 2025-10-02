package com.cs.edcSyncAlive.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cs.edcSyncAlive.entities.Merchant;


@Repository
public interface IMerchantRepository extends JpaRepository<Merchant, Integer>{

	@Query("SELECT t  FROM Merchant t where t.merchantId = :merchantId order by id asc limit 1")
	public Merchant findByMerchantId(@Param("merchantId") String merchantId);
}
