package com.cs.edcSyncAlive.daoImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cs.edcSyncAlive.dao.ITerminalDao;
import com.cs.edcSyncAlive.dto.RequestParams;
import com.cs.edcSyncAlive.dto.TerminalDto;
import com.cs.edcSyncAlive.entities.Terminal;
import com.cs.edcSyncAlive.repositories.ITerminalRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class TerminalDaoImpl implements ITerminalDao{
	
	@Autowired
	private ITerminalRepository repo;
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Terminal getTerminalByTerminalId(String terminalId) {
		return repo.getTerminalByTerminalId(terminalId);
	}

//	@Transactional
//	@Override
//	public void updateTerminal(String terminalId, Date updated) {
//		repo.updateTerminal(terminalId,updated);
//	}
	
	
	@Transactional
	@Override
    public void updateLastAlive(List<RequestParams> list) {
        int batchSize = 10;
        int count = 0;

        for (RequestParams t : list) {
        	Terminal entity = entityManager.find(Terminal.class, t.getTerminalIdPk());

            if (entity != null) {
                entity.setLastTxDate(t.getCreatedDate());
                entityManager.merge(entity);
                count++;
                System.out.println("--->>update id="+t.getTerminalIdPk()+":"+t.getTerminalId());
                if (count % batchSize == 0) {
                    entityManager.flush();
                    entityManager.clear(); // clear memory/persistence context
                   
                }
            }
        }

        // flush ค้างไว้รอบสุดท้าย ถ้าไม่ครบ batch
        entityManager.flush();
        entityManager.clear();
    }

	@Override
	public List<TerminalDto> findDtoByTerminalIdAndMerchantId(int terminalId, int merchantId) {
		return repo.findDtoByTerminalIdAndMerchantId(terminalId, merchantId);
	}

}
