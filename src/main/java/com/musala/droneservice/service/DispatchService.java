package com.musala.droneservice.service;


import com.musala.droneservice.utils.exceptions.ServiceException;
import com.musala.droneservice.utils.request.CreateDroneRequest;
import com.musala.droneservice.utils.request.UpdateDroneRequest;
import com.musala.droneservice.utils.response.ApiResponse;

import java.util.Locale;

public interface DispatchService {

    ApiResponse<?> create(CreateDroneRequest createDroneRequest, Locale locale) throws Exception;

    ApiResponse<?> edit(Long id, UpdateDroneRequest updateDroneRequest, Locale locale) throws ServiceException;

//    ListApiResponse<?> search(SearchCriteriaRequest productCriteriaRequest, Locale locale) throws ServiceException;
//
//    ApiResponse<?> delete (Long id,Locale locale) throws ServiceException;


//    @GetMapping("/products")
//    public ResponseEntity<ApiResponse<ProductListResponse>> getAllProducts() {
//        log.info(">>> request to fetch Products");
//        List<ProductResponse> fetchedProducts = productService.getAllProducts();
//        ProductListResponse response = new ProductListResponse();
//        response.setProducts(fetchedProducts);
//        log.info(">>> Products fetched successfully");
//        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(true, "200", "Products fetched successfully", response));
//    }

}
