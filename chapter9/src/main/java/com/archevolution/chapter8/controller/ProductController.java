package com.archevolution.chapter8.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.archevolution.chapter8.dto.JsonResponse;
import com.archevolution.chapter8.dto.ProductDTO;
import com.archevolution.chapter8.dto.ResponseCode;
import com.archevolution.chapter8.model.Product;
import com.archevolution.chapter8.service.ProductService;

@RestController
public class ProductController {
	
	@Autowired
	ProductService productService ;
	
	/**
	 * 根据商品 ID 查询商品
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/queryProduct/{id}")
	@ResponseBody
    public JsonResponse queryProductById(@PathVariable("id") int id){
		JsonResponse res = new JsonResponse();
		
		if(id < 1){
			res.setCode(ResponseCode.PARAMETER_ERROR);
			res.setMessage("参数不正确");
			return res;
		}
		
		Product product = productService.findOneById(id);
		
		if(product != null){
			res.setCode(ResponseCode.SUCCESS);
			
			ProductDTO productDTO = new ProductDTO();
			
			productDTO.setProductid(product.getProductid());
			productDTO.setProductname(product.getProductname());
			productDTO.setType(product.getType());
			productDTO.setPrice(product.getPrice());
			
			res.setData(product);;
		}else{
			res.setCode(ResponseCode.SUCCESS_NULL);
			res.setMessage("查询不到数据");
		}
		
        return res;
    }
}
