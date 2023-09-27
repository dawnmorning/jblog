package com.poscodx.jblog.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poscodx.jblog.service.BlogService;

@Controller
@RequestMapping(value = "/{blogId:^(?!assets).*$}")
public class BlogController {
	private BlogService blogService;
	@RequestMapping({ "", "/{pathCategory}", "/{pathCategory}/{pathPost}" })
	public String blogIndex(@PathVariable String blogId, @PathVariable Optional<Long> pathCategory,
			@PathVariable Optional<Long> pathPost, Model model) {

		model.addAllAttributes(blogService.getBlogInfo(blogId, pathCategory, pathPost));

		return "blog/main";

	}


	@RequestMapping({ "/admin/basic" })
	public String adminBasic() {
		return "";
	}

//	@ResponseBody
//	@RequestMapping({"/{blogId}/admin/basic"})
//	public String adminCategory() {
//		return "BlogController.adminCategory()";
//	}
}
