package fr.pfe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.pfe.dto.BoitierDto;
import fr.pfe.service.BoitierService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user/boitier")
public class BoitierController {
	
	@Autowired
	private BoitierService boitierService;
	
	@GetMapping("/all")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SERVER')")
	public Page<BoitierDto> getAllBoitiers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size){
		Pageable paging = PageRequest.of(page, size);
		return boitierService.getAllBoitiers(paging);
	}
	@PostMapping("/add")
	@PreAuthorize("hasRole('ROLE_SERVER')")
	public void addBoitier(@RequestBody BoitierDto boitierDto) {
		boitierService.addBoitier(boitierDto);
	}

}
