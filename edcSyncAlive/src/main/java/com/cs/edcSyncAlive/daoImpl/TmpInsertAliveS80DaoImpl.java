package com.cs.edcSyncAlive.daoImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cs.edcSyncAlive.dao.ITmpInsertAliveS80Dao;
import com.cs.edcSyncAlive.entities.TmpInsertAliveS80;
import com.cs.edcSyncAlive.repositories.ITmpInsertAliveS80Repository;

@Service
public class TmpInsertAliveS80DaoImpl implements ITmpInsertAliveS80Dao{

	@Autowired
	private ITmpInsertAliveS80Repository repo;
	
	@Transactional
	@Override
	public TmpInsertAliveS80 save(TmpInsertAliveS80 s) throws Exception {
		return repo.save(s);
	}

	@Transactional
	@Override
	public void saveAll(List<TmpInsertAliveS80> list) throws Exception {
		repo.saveAll(list);
	}

	@Override
	public List<TmpInsertAliveS80> findAll() throws Exception {
		return repo.findAll();
	}

	@Override
	public TmpInsertAliveS80 findById(int id) throws Exception {
		Optional<TmpInsertAliveS80> s =  repo.findById(id);
		return s.orElse(null);
	}

	@Transactional
	@Override
	public void deleteById(Integer id) throws Exception {
		repo.deleteById(id);
	}

}
