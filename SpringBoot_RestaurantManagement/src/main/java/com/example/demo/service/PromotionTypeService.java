package com.example.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.PromotionRepository;
import com.example.demo.models.PromotionType;

@Service
@Transactional

public class PromotionTypeService {
	@Autowired
	private PromotionRepository pr;
	
	public List<PromotionType> listAll(){
		return pr.findAll();
		
	}
	public PromotionType save (PromotionType promotionType) {
		 return pr.save(promotionType);
	}
	public PromotionType get (long id)
	{
		return pr.findById(id).get();
		
	}
	public void delete (long id) {
		pr.deleteById(id);
	}
}
