package hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 3.
 * @RequestMapping 클래스 + 메소드 단독 매핑
 * 
 */

@Controller
@RequestMapping("/user")
public class UserController {
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join() {
		return "/WEB-INF/views/join.jsp";
	}
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(UserVo vo) { // 위와 이름 동일하게
		System.out.println(vo);
		return "redirect:/";
	}
	
	@ResponseBody
	@RequestMapping("/update")
	public String update(@RequestParam("n") Long no) {
		// 만일, n이라는 파라미터 이름이 없거나 값이 올바르지 못하면 400 bad request error가 발생한다.
		return "UseController: update("+no+")";
	}
	
	@ResponseBody
	@RequestMapping("/update2")
	public String update2(@RequestParam(value="n",required=false) Long no) { // n은 null로 적용
		return "UseController: update2("+no+")";
	}
	
	@ResponseBody
	@RequestMapping("/update3")
	public String update3(@RequestParam(value="n",required=true,defaultValue="") String name) { // 기본값 설정
		return "UseController: update3("+name+")";
	}
	
	@ResponseBody
	@RequestMapping("/list")
	public String list(@RequestParam(value="p",required=true,defaultValue="1") int pageNo) { // 기본값 설정
		return "UseController: list("+pageNo+")";
	}
}


