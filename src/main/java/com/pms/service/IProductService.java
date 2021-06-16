package com.pms.service;

import java.util.List;

import com.pms.model.ProductDTO;

public interface IProductService {
	
	List<ProductDTO> searchAllProducts();
	
	ProductDTO searchProductById(Integer productId);
	
	List<ProductDTO> searchProductByManufacturer(String manufacturer);
	
	String addProduct(ProductDTO productDTO);
	
	ProductDTO updateProduct(ProductDTO productDTO);
	
	String deleteProductById(Integer productId);
	
	
}
