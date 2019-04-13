package com.additives.service;

import com.additives.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl {
    @Autowired
    private ProductRepository productRepository;

}