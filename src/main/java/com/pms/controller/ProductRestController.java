package com.pms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pms.entity.Product;
import com.pms.model.ProductDTO;
import com.pms.service.IProductService;

@RestController
//@CrossOrigin(origins = "*")
public class ProductRestController {
	@Autowired
	IProductService service;

	@GetMapping(value="/allProducts",produces = "application/json")
	public ResponseEntity<List<ProductDTO>> findAllProducts(){
		List<ProductDTO> productDTOList=service.searchAllProducts();
		return new ResponseEntity<List<ProductDTO>>(productDTOList,HttpStatus.OK);
	}
	//@CrossOrigin(origins = "*")
	@GetMapping(value="/{productId}",produces = "application/json")
	public ProductDTO findProductById(@PathVariable Integer productId) {
		return service.searchProductById(productId);
	}
	@GetMapping(value="/products/{manufacturer}",produces="application/json")
	public List<ProductDTO> findByManufacturer(@PathVariable String manufacturer){
		return service.searchProductByManufacturer(manufacturer);
	}
	@PostMapping(value="/product/addProduct",consumes="application/json")
	public String addProduct(@RequestBody ProductDTO productDTO) {
		return service.addProduct(productDTO);
	}
	@PutMapping(value="/product/updateProduct",consumes="application/json")
	public String updateProduct(@RequestBody ProductDTO productDTO) {
		ProductDTO dto= service.updateProduct(productDTO);
		if(dto==null) 
		{
			return "product does not exist";
		}else 
		{
			return "product is updated";
		}
	}

	@DeleteMapping("/product/delete/{productId}")
	public String deleteProductById(@PathVariable Integer productId) {
		return service.deleteProductById(productId);
	}

}
