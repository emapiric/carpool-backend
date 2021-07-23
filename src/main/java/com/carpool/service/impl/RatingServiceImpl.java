package com.carpool.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carpool.dto.RatingDto;
import com.carpool.mapper.RatingEntityDtoMapper;
import com.carpool.repository.RatingRepository;
import com.carpool.service.RatingService;
@Service
@Transactional
public class RatingServiceImpl implements RatingService{
	
	private RatingRepository ratingRepository;
	private RatingEntityDtoMapper ratingMapper;
	
	@Autowired
	public RatingServiceImpl(RatingRepository ratingRepository,RatingEntityDtoMapper ratingMapper) {
		super();
		this.ratingRepository = ratingRepository;
		this.ratingMapper = ratingMapper;
	}
	
	@Override
	public RatingDto save(RatingDto ratingDto) throws Exception {
		ratingRepository.save(ratingMapper.toEntity(ratingDto));
		return ratingDto;
	}
	

}
