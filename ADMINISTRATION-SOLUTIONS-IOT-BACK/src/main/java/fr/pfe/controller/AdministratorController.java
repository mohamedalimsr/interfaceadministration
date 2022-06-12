package fr.pfe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.pfe.dto.ServerClientAccountDto;
import fr.pfe.dto.WebClientAccountDto;
import fr.pfe.service.AdministratorService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/admin")
public class AdministratorController {

	@Autowired
	private AdministratorService administratorService;

	@GetMapping("/listeServerClientAccount")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Page<ServerClientAccountDto> getAllServerClientAccount(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size) {
        Pageable paging = PageRequest.of(page, size);
		return administratorService.getAllServerAccount(paging);
	}

	@GetMapping("/listeServerClientAccount/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ServerClientAccountDto getServerClientAccount(@PathVariable(name = "id") Long id) {
		return administratorService.getServerClientAccountById(id);
	}

	@PutMapping("/listeServerClientAccount/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ServerClientAccountDto updateServerClientAccount(@PathVariable(name = "id") Long id,
			@RequestBody ServerClientAccountDto serverClientAccountDto) {
		return administratorService.updateServerClientAccount(id, serverClientAccountDto);
	}

	@RequestMapping(path = "/listeServerClientAccount", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ServerClientAccountDto addServerClientAccount(@RequestBody ServerClientAccountDto serverClientAccountDto) {
		return administratorService.addServerClientAccount(serverClientAccountDto);
	}
	@DeleteMapping("/listeServerClientAccount/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void deleteServerClientAccount(@PathVariable(name = "id") long id) {
		administratorService.deleteServerClientAccount(id);
	}
	
	@GetMapping("/listeWebClientAccount")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SERVER')")
	public Page<WebClientAccountDto> getAllWebClientAccount(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size) {
        Pageable paging = PageRequest.of(page, size);
		return administratorService.getAllWebClientAccount(paging);
	}

	@GetMapping("/listeWebClientAccount/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SERVER')")
	public WebClientAccountDto getWebClientAccount(@PathVariable(name = "id") Long id) {
		return administratorService.getWebClientAccountById(id);
	}

	@PostMapping("/listeWebClientAccount/{id}")
	public WebClientAccountDto updateWebClientAccount(@PathVariable(name = "id") Long id,
			@RequestBody WebClientAccountDto webClientAccountDto) {
		return administratorService.updateWebClientAccount(id, webClientAccountDto);
	}
	@RequestMapping(path = "/listeWebClientAccount", method = RequestMethod.POST,  consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_SERVER') or hasRole('ROLE_ADMIN')")
	public WebClientAccountDto addWebClientAccount(@RequestBody WebClientAccountDto webClientAccountDto) {
		return administratorService.addWebClientAccount(webClientAccountDto);
	}
	@DeleteMapping("/listeWebClientAccount/{id}")
	@PreAuthorize("hasRole('ROLE_SERVER') or hasRole('ROLE_ADMIN')")
	public void deleteWebClientAccount(@PathVariable(name = "id") long id) {
		administratorService.deleteWebClientAccount(id);
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_WEB')")
	public WebClientAccountDto getWebClientAccountById(@PathVariable Long id) {
		return administratorService.getWebClientAccountById(id);
	}

}
