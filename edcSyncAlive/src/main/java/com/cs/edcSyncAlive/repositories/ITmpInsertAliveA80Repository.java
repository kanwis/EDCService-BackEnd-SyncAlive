package com.cs.edcSyncAlive.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cs.edcSyncAlive.entities.TmpInsertAliveA80;

@Repository
public interface ITmpInsertAliveA80Repository extends JpaRepository<TmpInsertAliveA80, Integer>{

}
