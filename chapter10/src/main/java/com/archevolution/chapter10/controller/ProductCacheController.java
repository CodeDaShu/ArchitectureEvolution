package com.archevolution.chapter10.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.archevolution.chapter10.dto.JsonResponse;
import com.archevolution.chapter10.dto.ProductDTO;
import com.archevolution.chapter10.dto.ResponseCode;
import com.archevolution.chapter10.model.Product;
import com.archevolution.chapter10.service.ProductCacheService;

@RestController
public class ProductCacheController {
	
	@Autowired
	ProductCacheService productCacheService ;
	
	/**
	 * 根据商品 ID 查询商品
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/queryProductByProId/{productid}")
	@ResponseBody
    public JsonResponse queryProductByProId(@PathVariable("productid") String productid){
		JsonResponse res = new JsonResponse();
		
		if("".equals(productid)){
			res.setCode(ResponseCode.PARAMETER_ERROR);
			res.setMessage("参数不正确");
			return res;
		}
		
		Product product = productCacheService.findByProductid(productid);
		//Product product = productCacheService.findByProductid2(productid);
		
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
	
	/**
	 * 根据 ID 查询商品
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/queryProductById/{id}")
	@ResponseBody
    public JsonResponse queryProductById(@PathVariable("id") int id){
		JsonResponse res = new JsonResponse();
		
		if(id < 0){
			res.setCode(ResponseCode.PARAMETER_ERROR);
			res.setMessage("参数不正确");
			return res;
		}
		
		//Product product = productCacheService.findByProductid(productid);
		Product product = productCacheService.findById(id);
		
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
