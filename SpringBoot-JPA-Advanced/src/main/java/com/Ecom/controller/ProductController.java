package com.Ecom.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Ecom.entity.Product;
import com.Ecom.repository.ProductRepository;


@Controller
public class ProductController {

	@Autowired
	private ProductRepository productRepository;

	
	
	// List All Products

	@GetMapping("/listOfProducts")
	public String listOfProducts(ModelMap model) {

		List<Product> listOfProducts = productRepository.findAll();

		model.addAttribute("prodlist", listOfProducts);

		return "listofProducts";
	}

	
	
	///  Find All by Name JPA
	
	
	@GetMapping("/listProductsByName")
	public String listProductsByName(ModelMap model, String name) {
	    List<Product> listOfProducts = productRepository.findAllByName(name);

	    if (!listOfProducts.isEmpty()) {
	        model.addAttribute("prodlist", listOfProducts);
	        return "listofProducts";
	    } else {
	    	
	    	model.addAttribute("name", name);
	        return "product-not-found-byName";
	    }
	}

	
	/// Find All by Price JPA
	
	@GetMapping("/listProductsByPrice")
	public String listProductsByPrice(ModelMap model, double price) {
	    List<Product> listOfProducts = productRepository.findAllByPrice(price);

	        model.addAttribute("prodlist", listOfProducts);
	        return "listofProducts";
	}
	
	
	
	
	// Add Product
	// Forms

	@GetMapping("/addProduct")
	public String showNewProductForm(ModelMap model) {

		Product product = new Product();
		model.addAttribute("product", product);

		return "new-product";
	}

	@PostMapping("/addProduct")
	public String addNewProduct(ModelMap model, @ModelAttribute("product") Product product) {

		System.out.println(product.getDateAdded());

		Product savedProduct = productRepository.save(product);
		model.addAttribute("id", savedProduct.getId());

		return "add-product-success";
	}

	
	
	// Edit Product

	@GetMapping("/editProduct")
	public String editProductForm(ModelMap model, @RequestParam long id) {
		model.addAttribute("id", id);

		Optional<Product> eProductFromRepo = productRepository.findById(id);

		if (eProductFromRepo.isPresent()) {

			Product product = eProductFromRepo.get();
			model.addAttribute("product", product);

			return "edit-product-form";
		} else {
			return "product-not-found";
		}
	}

	@PostMapping("/editProduct")
	public String updateProduct(ModelMap model, @ModelAttribute("product") Product product) {

		Product savedProduct = productRepository.save(product);
		model.addAttribute("id", savedProduct.getId());

		return "edit-product-success";
	}

	
	
	
	// Delete Product

	@GetMapping("/deleteProduct")
	public String deleteProduct(ModelMap model, @RequestParam long id) {

		Optional<Product> eProductFromRepo = productRepository.findById(id);

		model.addAttribute("id", id);

		if (eProductFromRepo.isPresent()) {

			productRepository.deleteById(id);

			return "delete-product-success";
		} else {
			return "delete-product-failed";
		}
	}
}
