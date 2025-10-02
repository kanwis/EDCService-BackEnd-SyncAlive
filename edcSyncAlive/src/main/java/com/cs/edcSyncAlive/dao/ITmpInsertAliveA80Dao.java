package com.cs.edcSyncAlive.dao;

import java.util.List;

import com.cs.edcSyncAlive.entities.TmpInsertAliveA80;

public interface ITmpInsertAliveA80Dao {
	public TmpInsertAliveA80 save(TmpInsertAliveA80 a) throws Exception;
	public void saveAll(List<TmpInsertAliveA80> list) throws Exception;
	public List<TmpInsertAliveA80> findAll() throws Exception;
	public TmpInsertAliveA80 findById(int id) throws Exception;
	public void deleteById(Integer id) throws Exception;
}
