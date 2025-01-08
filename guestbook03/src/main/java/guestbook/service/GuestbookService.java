package guestbook.service;

import java.util.List;

import org.springframework.stereotype.Service;

import guestbook.repository.GuestbookLogRepository;
import guestbook.repository.GuestbookRepository;
import guestbook.vo.GuestbookVo;

@Service
public class GuestbookService {
	private final GuestbookRepository guestbookRepository;
	private final GuestbookLogRepository guestbookLogRepository;
	
	public GuestbookService(GuestbookRepository guestbookRepository,GuestbookLogRepository guestbookLogRepository) {
		this.guestbookLogRepository=guestbookLogRepository;
		this.guestbookRepository=guestbookRepository;
	}
	
	public List<GuestbookVo> getContentsList() {
		List<GuestbookVo> list = guestbookRepository.findAll();
		
		return list;
	}

	public void deleteContents(Long id, String password) {
		GuestbookVo vo = guestbookRepository.findById(id);
		if(vo==null) {
			return;
		}
		
		
		int count = guestbookRepository.deleteByIdAndPassword(id, password);	
		if(count ==1) {
			guestbookLogRepository.update(vo.getRegDate());
		}
	}

	public void addContents(GuestbookVo vo) {
		int count = guestbookLogRepository.update();
		if(count==0) {
			guestbookLogRepository.insert();
		}
		guestbookRepository.insert(vo);
	}
}