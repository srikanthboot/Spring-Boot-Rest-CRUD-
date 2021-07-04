 package com.pms.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.entity.Product;
import com.pms.model.ProductDTO;
import com.pms.repository.ProductRepository;
import com.pms.service.IProductService;


@Service
public class ProductServiceImpl implements IProductService {
	@Autowired
	ProductRepository repository;

	@Override
	public List<ProductDTO> searchAllProducts() {
		List<Product>productList=repository.findAll();

		List<ProductDTO> productDTOList=new ArrayList<>();
		productList.forEach(product->{
			ProductDTO productDTO=new ProductDTO();
			BeanUtils.copyProperties(product, productDTO);
			productDTOList.add(productDTO);
		});
		return productDTOList;
	}

	@Override
	public ProductDTO searchProductById(Integer productId) {
		Optional<Product>opt=repository.findById(productId);
		if(opt.isPresent()) {
			Product product=opt.get();
			ProductDTO productDTO=new ProductDTO();
			BeanUtils.copyProperties(product,productDTO);
			return productDTO;
		}
		return null;
	}

	@Override
	public List<ProductDTO> searchProductByManufacturer(String manufacturer) {
		List<Product> productList=repository.findByManufacturer(manufacturer);
		List<ProductDTO>productDTOList=new ArrayList<>();
		productList.forEach(product->{
			ProductDTO productDTO =new ProductDTO();
			BeanUtils.copyProperties(product, productDTO);
			productDTOList.add(productDTO);
		});
		return productDTOList;
	}

	@Override
	public String addProduct(ProductDTO productDTO) {
		Product product=new Product();
		BeanUtils.copyProperties(productDTO, product);
		if(repository.existsById(product.getProductId())) {
			return "Product already exist";
		}else {
			repository.save(product);
			return "Product is added to Database";
		}
	}

	@Override
	public ProductDTO updateProduct(ProductDTO productDTO) {
		Product product=new Product();
		BeanUtils.copyProperties(productDTO, product);
		if(repository.existsById(product.getProductId())) {
			repository.save(product);
			return productDTO;
		}else {
			return null;
		}
	}

	@Override
	public String deleteProductById(Integer productId) {
		if(repository.existsById(productId)) {
			repository.deleteById(productId);
			return "Product is deleted"+productId;
		}else {
			return "Product does not exist";
		}
	}
}


