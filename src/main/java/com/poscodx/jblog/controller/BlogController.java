package com.poscodx.jblog.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poscodx.jblog.service.BlogService;
import com.poscodx.jblog.vo.BlogVo;
import com.poscodx.jblog.vo.CategoryVo;
import com.poscodx.jblog.vo.PostVo;

@Controller
@RequestMapping(value = "/{blogId:^(?!assets).*$}")
public class BlogController {

	private BlogService blogService;

	@RequestMapping({ "", "/{pathCategory}", "/{pathCategory}/{pathPost}" })
	public String blogIndex(@PathVariable String blogId, @PathVariable Optional<Long> categoryIdx,
			@PathVariable Optional<Long> postIdx, Model model) {

		BlogVo blogVo = blogService.getBlog(blogId);

		List<CategoryVo> categoryList = blogService.getCategoriesById(blogId);

		List<PostVo> postList = null;
		
		if(categoryIdx.isPresent()) {
			postList = blogService.getPostsByCategory(categoryIdx.get());
		}
		else {
			if(categoryList.size() > 0) {
				categoryIdx = Optional.of(1L);
				postList = blogService.getPostsByCategory(categoryList.get(0).getNo());
			}
		}
		
		if(postIdx.isPresent()) {
			PostVo post = blogService.getPostByNo(postIdx.get());
			model.addAttribute("post", post);
		}
		else {
			// PostNo 없을 때 default Post 지정
			if (postList != null && postList.size() > 0) {
				PostVo post = blogService.getPostByNo(postList.get(0).getNo());
				model.addAttribute("post", post);
			}
		}
		model.addAttribute("categoryNo", categoryIdx.get());
		model.addAttribute("postList", postList);
		model.addAttribute("blog", blogVo);
		model.addAttribute("categoryList", categoryList);
		
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
