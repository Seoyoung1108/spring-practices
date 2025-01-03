package fileupload.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import fileupload.service.FileuploadService;

@Controller
public class FileuploadController {
	private FileuploadService fileuploadService;
	
	public FileuploadController(FileuploadService fileuploadService) {
		this.fileuploadService=fileuploadService;
	}
	@RequestMapping({"/","/form"})
	public String form() {
		return "form";
	}
	
	@RequestMapping("/upload")
	public String upload(Model model, @RequestParam("email") String email, @RequestParam("file") MultipartFile[] files) {
		String url = fileuploadService.restore(files[0]);
		model.addAttribute("url", url);
		/*
		try {
			System.out.println(email);
			System.out.println(file.getOriginalFilename());
			System.out.println(file.getSize());
			System.out.println(file.getBytes().length);
		} catch(IOException e) {
			throw new RuntimeException(e);
		}*/
		return "result";
	}
}
