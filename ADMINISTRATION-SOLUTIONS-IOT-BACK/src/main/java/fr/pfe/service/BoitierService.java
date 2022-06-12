package fr.pfe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fr.pfe.dto.BoitierDto;
import fr.pfe.entities.Boitier;
import fr.pfe.mapper.BoitierMapper;
import fr.pfe.repositories.BoitierRepository;

@Service
public class BoitierService {
		
	@Autowired
	private BoitierRepository boitierRepository;
	
	public Page<BoitierDto> getAllBoitiers(Pageable pageable){
		return toPageBoitierDto(boitierRepository.findAll(pageable));
	}
	private Page<BoitierDto> toPageBoitierDto(Page<Boitier> objects) {
	    Page<BoitierDto> dtos  = objects.map(this::convertToBoitierDto);
	    return dtos;
	}
	private BoitierDto convertToBoitierDto(Boitier o) {
	    return BoitierMapper.toBoitierDto(o);
	}
	
	public void addBoitier(BoitierDto boitierDto) {
		boitierRepository.save(BoitierMapper.toBoitier(boitierDto));
	}
	
}
