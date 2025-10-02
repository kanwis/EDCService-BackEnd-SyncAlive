package com.cs.edcSyncAlive.dao;

import java.util.List;

import com.cs.edcSyncAlive.entities.TmpInsertAliveS80;

public interface ITmpInsertAliveS80Dao {
	public TmpInsertAliveS80 save(TmpInsertAliveS80 s) throws Exception;
	public void saveAll(List<TmpInsertAliveS80> list) throws Exception;
	public List<TmpInsertAliveS80> findAll() throws Exception;
	public TmpInsertAliveS80 findById(int id) throws Exception;
	public void deleteById(Integer id) throws Exception;

}
