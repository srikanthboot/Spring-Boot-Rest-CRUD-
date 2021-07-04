package com.pms.test;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;



@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestPropertySource("classpath:test-application.properties")
public class TestProductRestController {
	@Autowired
	MockMvc mockMvc;
	/*
	 * @Mock IProductService service;
	 * 
	 * @InjectMocks private ProductRestController controller;
	 */

	
	@Test
	@Disabled
	public void testFindAllProducts() throws Exception {
		//1. create Dummy Request
				MockHttpServletRequestBuilder request = 
						MockMvcRequestBuilders.get("/allProducts");

				//2. execute request and get result
				MvcResult result = mockMvc.perform(request).andReturn();

				//3. Read Response
				MockHttpServletResponse response = result.getResponse();

				//4. Test using assert Method
				//is status code is -200
				assertEquals(HttpStatus.OK.value(), response.getStatus());
				assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getContentType());
				if(response.getContentAsString().length()<=0) {
					fail("No Data Found");
				}
		/*
		 * ProductDTO dto = new ProductDTO(); dto.setProductId(101);
		 * dto.setProductName("SONY 5G MOBILE"); dto.setPrice(25000.0);
		 * dto.setManufacturer("SONY");
		 * 
		 * List<ProductDTO> listDto = new ArrayList<>(); listDto.add(dto);
		 * ResponseEntity<List<ProductDTO>> responseEntity =
		 * controller.findAllProducts();
		 * 
		 * MockHttpServletResponse response =
		 * mockMvc.perform(get("/allProducts").accept(MediaType.APPLICATION_JSON))
		 * .andReturn().getResponse();
		 * 
		 * // then assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
		 * assertThat(responseEntity.getBody());
		 */
	}
	 
	@Test
	@Disabled
	public void testFindProductById() throws Exception {
		
		//Integer productId=controller.findProductById(101).getProductId();
		MockHttpServletRequestBuilder request =
				MockMvcRequestBuilders.get("/101");
		
		//2. execute request and get result
		MvcResult result = mockMvc.perform(request).andReturn();

		//3. Read Response
		MockHttpServletResponse response = result.getResponse();
		
		//4. Test using assert Method
		//is status code is -200
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getContentType());
		
	}
	@Test
	@Disabled
	public void testFindByManufacturer() throws Exception{
		/*
		 * ProductDTO dto = new ProductDTO(); dto.setProductId(101);
		 * dto.setProductName("SONY 5G MOBILE"); dto.setPrice(25000.0);
		 * dto.setManufacturer("SONY");
		 * 
		 * List<ProductDTO> listDto = new ArrayList<>(); listDto.add(dto);
		 * ResponseEntity<List<ProductDTO>>
		 * responseEntity=(ResponseEntity<List<ProductDTO>>)
		 * controller.findByManufacturer("SONY");
		 */
		MockHttpServletRequestBuilder request =
				MockMvcRequestBuilders.get("/products/SONY");
		
		//2. execute request and get result
		MvcResult result = mockMvc.perform(request).andReturn();

		//3. Read Response
		MockHttpServletResponse response = result.getResponse();
		
		//4. Test using assert Method
		//is status code is -200
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getContentType());
	
	}
	@Test
	@Disabled
	public void testAddProduct() throws Exception{
		
		MockHttpServletRequestBuilder request =
				MockMvcRequestBuilders
				.post("/product/addProduct")
				.contentType(MediaType.APPLICATION_JSON)
				//.content("{\"empName\":\"JAI\",\"empSal\":6500.0}");
				.content("{\"productId\":102,\"productName\":\"Airtel Sim\",\"price\":300.2,\"manufacturer\":\"airtel\"}");

		//2. execute request and get result
		MvcResult result = mockMvc.perform(request).andReturn();

		//3. Read Response
		MockHttpServletResponse response = result.getResponse();

		//4. Test using assert Method
		//is status code is -200
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		if(!response.getContentAsString().contains("Product is added to Database")) {
			fail("SAVE EMPLOYEE NOT WORKING!! ");
		}
	}
	@Test
	@Disabled
	public void testUpdateProduct() throws Exception {
		//1. create Dummy Request
		MockHttpServletRequestBuilder request =
				MockMvcRequestBuilders.put("/product/updateProduct")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"productId\":102,\"productName\":\"Airtel Sim\",\"price\":400.2,\"manufacturer\":\"airtel\"}");
		//2. execute request and get result
				MvcResult result = mockMvc.perform(request).andReturn();
				
				//3. Read Response
				MockHttpServletResponse response = result.getResponse();
				
				//4. Test using assert Method --status-200
				assertEquals(HttpStatus.OK.value(), response.getStatus());
				assertEquals("product is updated", response.getContentAsString());
			}
	@Test
	//@Disabled
	public void testDeleteEmployee() throws Exception {
		//1. create Dummy Request
		MockHttpServletRequestBuilder request =
		MockMvcRequestBuilders.delete("/product/delete/106");
		
		//2. execute request and get result
		MvcResult result = mockMvc.perform(request).andReturn();
		
		//3. Read Response
		MockHttpServletResponse response = result.getResponse();
		
		//4. Test using assert Method --status-200
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertEquals("Product is deleted", response.getContentAsString());
	}
	
}