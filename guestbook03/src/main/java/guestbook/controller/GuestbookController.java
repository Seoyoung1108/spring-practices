package guestbook.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import guestbook.repository.GuestbookRepository;
import guestbook.vo.GuestbookVo;

@Controller
public class GuestbookController {
	private GuestbookRepository guestbookRepository;
	
	public GuestbookController(GuestbookRepository guestbookRepository) { // null이어도 에러 안터지게
		this.guestbookRepository = guestbookRepository;
	}
	
	@RequestMapping("/")
	public String index(Model model) {
		List<GuestbookVo> list = guestbookRepository.findAll();
		model.addAttribute("list", list);
		return "index";
	}
	
	@RequestMapping("/add")
	public String add(GuestbookVo vo) {
		guestbookRepository.insert(vo);
		return "redirect:/";
	}
	
	@RequestMapping(value="/delete/{no}", method=RequestMethod.GET)
	public String delete(@PathVariable("no") Long no, Model model) {
		model.addAttribute("no", no);
		return "deleteform";
	}
	
	@RequestMapping(value="/delete/{no}", method=RequestMethod.POST)
	public String delete(@PathVariable("no") Long no, @RequestParam(value="password",required=true,defaultValue="") String password) {
		guestbookRepository.deleteByIdAndPassword(no, password);
		return "redirect:/";
	}
}
