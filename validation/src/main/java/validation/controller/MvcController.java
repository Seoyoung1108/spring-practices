package validation.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import validation.domain.User;

@Controller
public class MvcController {
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/result")
	public String result() {
		return "result";
	}
	
	@GetMapping("/ex01")
	public String ex01() {
		return "form/ex01";
	}
	
	@PostMapping("/ex01")
	public String ex01(@ModelAttribute @Valid User user, BindingResult result, Model model) {
		if(result.hasErrors()) {
			/*
			List<ObjectError> errors = result.getAllErrors();
			for(ObjectError error: errors) {
				System.out.println(error);
			}
			*/
			
			Map<String,Object> map = result.getModel();
			/*
			Set<String> s = map.keySet();
			for(String key:s) {
				System.out.println(key);
				//model.addAttribute(key,map.get(key));
			}
			*/
			model.addAllAttributes(map); // key로 풀어서 보내줌.
			
			return "form/ex01";
		}
		System.out.println(user);
		return "redirect:/result";
	}
	
	@GetMapping("/ex02")
	public String ex02() {
		return "form/ex02";
	}
	
	@PostMapping("/ex02")
	public String ex02(@ModelAttribute @Valid User user, BindingResult result, Model model) {
		if(result.hasErrors()) {
			Map<String,Object> map = result.getModel();
			model.addAllAttributes(map); // key로 풀어서 보내줌.
			
			return "form/ex02";
		}
		return "redirect:/result";
	}
	
	@GetMapping("/ex03")
	public String ex03(@ModelAttribute User user) { // null 방지
		return "form/ex03";
	}
	
	@PostMapping("/ex03")
	public String ex03(@ModelAttribute @Valid User user, BindingResult result, Model model) {
		if(result.hasErrors()) {
			Map<String,Object> map = result.getModel();
			model.addAllAttributes(map); // key로 풀어서 보내줌.
			
			return "form/ex03";
		}
		return "redirect:/result";
	}
}
