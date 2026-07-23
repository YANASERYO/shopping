package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dao.ProductDAO;
import com.example.demo.model.Product;

@Service
public class ProductService {
	private final ProductDAO productDAO;
	
	
	public ProductService(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}
	
//	全件取得
	public List<Product> getAllProducts(){
		return productDAO.getAllProducts();
	}
	
//	IDによる取得
	public Product findById(Long productId) {
		return productDAO.findById(productId);
	}
	
//	更新処理
	public boolean update(Product product) {
		return productDAO.update(product);
	}
	
//	adminによる登録処理
	public boolean create(Product product) {
		return productDAO.insert(product);
	}
	
// 商品の論理削除
	public boolean delete(Long productId) {
		return productDAO.delete(productId);
	}
	
// 販売中の商品一覧を取得
	public List<Product> getActiveProducts() {
		return productDAO.getActiveProducts();
	}
	
}
