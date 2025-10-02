package com.cs.edcSyncAlive.daoImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cs.edcSyncAlive.dao.ITmpInsertAliveA80Dao;
import com.cs.edcSyncAlive.entities.TmpInsertAliveA80;
import com.cs.edcSyncAlive.repositories.ITmpInsertAliveA80Repository;

@Service
public class TmpInsertAliveA80DaoImpl implements ITmpInsertAliveA80Dao{
	
	@Autowired
	private ITmpInsertAliveA80Repository repo;

	@Transactional
	@Override
	public TmpInsertAliveA80 save(TmpInsertAliveA80 a) throws Exception {
		return repo.save(a);
	}

	@Transactional
	@Override
	public void saveAll(List<TmpInsertAliveA80> list) throws Exception {
		repo.saveAll(list);
	}

	@Override
	public List<TmpInsertAliveA80> findAll() throws Exception {
		return repo.findAll();
	}

	@Override
	public TmpInsertAliveA80 findById(int id) throws Exception {
		Optional<TmpInsertAliveA80> data = repo.findById(id);
		return data.orElse(null);
	}

	@Transactional
	@Override
	public void deleteById(Integer id) throws Exception {
		repo.deleteById(id);
	}

}
