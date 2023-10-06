package com.poscodx.jblog.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.poscodx.jblog.service.BlogService;
import com.poscodx.jblog.service.CategoryService;
import com.poscodx.jblog.service.PostService;
import com.poscodx.jblog.service.fileUploadService;
import com.poscodx.jblog.vo.BlogVo;
import com.poscodx.jblog.vo.CategoryVo;
import com.poscodx.jblog.vo.PostVo;

@Controller
@RequestMapping(value = "/{blogId:^(?!assets).*$}")
public class BlogController {
	@Autowired
	private BlogService blogService;
	@Autowired
	private fileUploadService fileUploadService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private PostService postService;

	@RequestMapping({ "", "/{categoryNo}", "/{categoryNo}/{postNo}" })
	public String index(@PathVariable("blogId") String blogId, @PathVariable Optional<Long> categoryNo,
			@PathVariable Optional<Long> postNo, Model model) {

		BlogVo blogVo = blogService.getBlog(blogId);

		List<CategoryVo> categoryList = categoryService.getCategoriesById(blogId);

		List<PostVo> postList = null;

		if (categoryNo.isPresent()) {
			postList = postService.getPostsByCategory(categoryNo.get());
		} else {
			if (categoryList.size() > 0) {
				categoryNo = Optional.of(categoryList.get(0).getNo());
				postList = postService.getPostsByCategory(categoryList.get(0).getNo());
			}
		}

		if (postNo.isPresent()) {
			PostVo post = postService.getPostByNo(postNo.get());
			model.addAttribute("post", post);
		} else {
			if (postList != null && postList.size() > 0) {
				PostVo post = postService.getPostByNo(postList.get(0).getNo());
				model.addAttribute("post", post);
			}
		}
		model.addAttribute("categoryNo", categoryNo.get());
		model.addAttribute("postList", postList);
		model.addAttribute("blogVo", blogVo);
		model.addAttribute("categoryList", categoryList);

		return "blog/main";
	}

	@RequestMapping(value = "/admin/basic", method = RequestMethod.GET)
	public String adminBasic(@PathVariable("blogId") String blogId, Model model) {

		BlogVo blogVo = blogService.getBlog(blogId);
		model.addAttribute("blogVo", blogVo);

		return "blog/admin-basic";
	}

	@RequestMapping(value = "/admin/basic", method = RequestMethod.POST)
	public String adminBasic(@PathVariable("blogId") String blogId, BlogVo blogVo, MultipartFile file) {

		blogVo.setBlogId(blogId);
		String image = fileUploadService.restore(file);
		if (image != null) {
			blogVo.setImage(image);
		}
		blogService.update(blogVo);

		return "redirect:/" + blogId;
	}

	@RequestMapping(value = "/admin/category", method = RequestMethod.GET)
	public String adminCategory(@PathVariable("blogId") String blogId, Model model) {
		BlogVo blogVo = blogService.getBlog(blogId);
		model.addAttribute("blogVo", blogVo);

		List<CategoryVo> categoryVo = categoryService.getCategoriesById(blogId);
		System.out.println(categoryVo);
		model.addAttribute("categoryVo", categoryVo);
		return "blog/admin-category";
	}

	@RequestMapping(value = "/admin/category", method = RequestMethod.POST)
	public String adminCategory(@PathVariable("blogId") String blogId, CategoryVo categoryVo) {

		categoryVo.setBlogId(blogId);
		categoryService.add(categoryVo);

		return "redirect:/" + blogId + "/admin/category";
	}
	
	@RequestMapping(value="/admin/write", method = RequestMethod.GET)
	public String adminWrite(@PathVariable("blogId") String blogId, Model model) {
		List<CategoryVo> categoryVo = categoryService.getCategoriesById(blogId);
		model.addAttribute("categoryVo", categoryVo);
		
		BlogVo blogVo = blogService.getBlog(blogId);
		model.addAttribute("blogVo", blogVo);
		
		return "blog/admin-write";
	}
	
	@RequestMapping(value="/admin/write", method=RequestMethod.POST)
	public String adminWrite(@PathVariable("blogId") String blogId, PostVo postVo) {
		postService.addPost(postVo);
		return "redirect:/"+blogId;
	}
	
}
