package com.medicare.controller;

	import java.io.IOException;

	import java.nio.file.Files;
	import java.nio.file.Path;
	import java.nio.file.Paths;
	import java.util.Optional;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Controller;
	import org.springframework.ui.Model;
	import org.springframework.web.bind.annotation.GetMapping;
	import org.springframework.web.bind.annotation.ModelAttribute;
	import org.springframework.web.bind.annotation.PathVariable;
	import org.springframework.web.bind.annotation.PostMapping;
	import org.springframework.web.bind.annotation.RequestParam;
	import org.springframework.web.multipart.MultipartFile;

	import com.medicare.dto.ProductDTO;
	import com.medicare.model.Category;
	import com.medicare.model.Product;
	import com.medicare.service.CategoryService;
	import com.medicare.service.ProductService;
		
	@Controller
	public class AdminController{
		
		public static String uploadDir=System.getProperty("user,dir")+"/src/main/resources/static/productImages";
		@Autowired
		CategoryService categoryService;
		@Autowired
		ProductService productService;
		
		@GetMapping("/admin")
		public String adminHome() {
			return "adminHome";
		}
		
		@GetMapping("/admin/categories")
		public String getCat(Model model) {
			model.addAttribute("categories",categoryService.getAllCategory());
			return "categories";
		}
		
		@GetMapping("/admin/categories/add")
		public String getCatAdd(Model model) {
			model.addAttribute("category",new Category());
			return "categoriesAdd";
		}
		
		@PostMapping("/admin/categories/add")
		public String postCatAdd(@ModelAttribute("category")Category category) {
			categoryService.addCategory(category);
			return "redirect:/admin/categories";
		}
		
		@GetMapping("/admin/categories/delete/{id}")
		public String deleteCat(@PathVariable int id) {
			categoryService.removeCategoryById(id);
			return "redirect:/admin/categories";
		}
		@GetMapping("/admin/categories/update/{id}")
		public String updateCat(@PathVariable int id,Model model) {
			Optional<Category>category =categoryService.getCategoryById(id);
			if(category.isPresent()) {
				model.addAttribute("category",category.get());
				return "categoriesAdd";
			}else
				return "404";
		}
		
		
		@GetMapping("/admin/products")
		public String products(Model model) {
			model.addAttribute("products",productService.getAllProduct());
			return "products";
		}
		
		
		
	}



