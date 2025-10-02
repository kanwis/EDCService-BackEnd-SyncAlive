package com.cs.edcSyncAlive.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cs.edcSyncAlive.entities.TmpInsertAliveS80;

@Repository
public interface ITmpInsertAliveS80Repository extends JpaRepository<TmpInsertAliveS80, Integer>{

}
