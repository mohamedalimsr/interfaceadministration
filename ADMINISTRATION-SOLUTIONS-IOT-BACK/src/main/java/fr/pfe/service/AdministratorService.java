package fr.pfe.service;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.pfe.dto.ServerClientAccountDto;
import fr.pfe.dto.WebClientAccountDto;
import fr.pfe.entities.Role;
import fr.pfe.entities.ServerClientAccount;
import fr.pfe.entities.WebClientAccount;
import fr.pfe.mapper.ServerClientAccountMapper;
import fr.pfe.mapper.WebClientAccountMapper;
import fr.pfe.repositories.RoleRepository;
import fr.pfe.repositories.ServerClientRepository;
import fr.pfe.repositories.WebClientRepository;

@Service
public class AdministratorService {

	@Autowired
	private WebClientRepository webClientRepository;
	
	@Autowired
	PasswordEncoder encoder;

	@Autowired
	private ServerClientRepository serverClientRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	public Page<WebClientAccountDto> getAllWebClientAccount(Pageable pageable) {
		return toPageWebAccountDto(webClientRepository.findAll(pageable));
	}
	private Page<WebClientAccountDto> toPageWebAccountDto(Page<WebClientAccount> objects) {
	    Page<WebClientAccountDto> dtos  = objects.map(this::convertToWebAccountDto);
	    return dtos;
	}
	private WebClientAccountDto convertToWebAccountDto(WebClientAccount o) {
	    return WebClientAccountMapper.toWebClientAccountDto(o);
	}

	public WebClientAccountDto addWebClientAccount(WebClientAccountDto webClientAccountDto) {
		Role role = roleRepository.findByName(webClientAccountDto.getRole().getName())
				.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
		WebClientAccount webClientAccount = WebClientAccountMapper.toWebClientAccount(webClientAccountDto);
		webClientAccount.setRole(role);
		webClientAccount.setPassword(encoder.encode(webClientAccountDto.getPassword()));
		webClientAccount.setSereServerClientAccount(webClientAccountDto.getServerClientAccount());
		webClientAccount.setAdministratorAccount(webClientAccountDto.getAdministratorCompte());
		return WebClientAccountMapper.toWebClientAccountDto(webClientRepository.save(webClientAccount));
	}
	public void deleteWebClientAccount(Long id) {
		webClientRepository.deleteById(id);
	}

	@Transactional
	public WebClientAccountDto updateWebClientAccount(Long id, WebClientAccountDto webClientAccountRequest) {
		WebClientAccount webClientAccount = getWebClientAccountEntityById(id);
		webClientAccount.setTelephone(webClientAccountRequest.getTelephone());
		webClientAccount.setSimCardFeePerMonth(webClientAccountRequest.getSimCardFeePerMonth());
		webClientAccount.setRawPassword(webClientAccountRequest.getRawPassword());
		webClientAccount.setPool(webClientAccountRequest.getPool());
		webClientAccount.setNotificationSubquery(webClientAccountRequest.getNotificationSubquery());
		webClientAccount.setAccountFeeByMonth(webClientAccountRequest.getAccountFeeByMonth());
		webClientAccount.setArea(webClientAccountRequest.getArea());
		webClientAccount.setDate_creation(webClientAccountRequest.getDate_creation());
		webClientAccount.setDate_expiration(webClientAccountRequest.getDate_expiration());
		webClientAccount.setDeviceFeeByDay(webClientAccountRequest.getDeviceFeeByDay());
		webClientAccount.setDeviceFeePerMonth(webClientAccountRequest.getDeviceFeePerMonth());
		return WebClientAccountMapper.toWebClientAccountDto(webClientAccount);
	}

	public WebClientAccount getWebClientAccountEntityById(Long id) {
		return webClientRepository.findById(id).get();
	}

	public WebClientAccountDto getWebClientAccountById(Long id) {
		return WebClientAccountMapper.toWebClientAccountDto(getWebClientAccountEntityById(id));
	}
	
	public Page<ServerClientAccountDto> getAllServerAccount(Pageable pageable) {
		return toPageObjectDto(serverClientRepository.findAll(pageable));
	}
	private Page<ServerClientAccountDto> toPageObjectDto(Page<ServerClientAccount> objects) {
	    Page<ServerClientAccountDto> dtos  = objects.map(this::convertToObjectDto);
	    return dtos;
	}
	private ServerClientAccountDto convertToObjectDto(ServerClientAccount o) {
	    return ServerClientAccountMapper.toServerClientAccountDto(o);
	}

	@Transactional
	public ServerClientAccountDto updateServerClientAccount(Long id,
			ServerClientAccountDto serverClientAccountRequest) {
		ServerClientAccount serverClientAccount = getServerClientAccountEntityById(id);
		serverClientAccount.setUsername(serverClientAccountRequest.getUsername());
		serverClientAccount.setPseudo(serverClientAccountRequest.getPseudo());
		serverClientAccount.setPassword(serverClientAccountRequest.getPassword());
		serverClientAccount.setIpAdresse(serverClientAccountRequest.getIpAdresse());
		serverClientAccount.setIntervaleStart(serverClientAccountRequest.getIntervaleStart());
		serverClientAccount.setIntervaleEnd(serverClientAccountRequest.getIntervaleEnd());
		serverClientAccount.setEmail(serverClientAccountRequest.getEmail());
		serverClientAccount.setDateExpiration(serverClientAccountRequest.getDateExpiration());
		serverClientAccount.setDateCreation(serverClientAccountRequest.getDateCreation());
		return ServerClientAccountMapper.toServerClientAccountDto(serverClientAccount);
	}

	public ServerClientAccount getServerClientAccountEntityById(Long id) {
		return serverClientRepository.findById(id).get();
	}

	public ServerClientAccountDto getServerClientAccountById(Long id) {
		return ServerClientAccountMapper.toServerClientAccountDto(getServerClientAccountEntityById(id));
	}

	public ServerClientAccountDto addServerClientAccount(ServerClientAccountDto serverClientAccountDto) {
		Role role = roleRepository.findByName(serverClientAccountDto.getRole().getName())
				.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
		ServerClientAccount serverClientAccount = ServerClientAccountMapper.toServerClientAccountBo(serverClientAccountDto);
		serverClientAccount.setRole(role);
		serverClientAccount.setPassword(encoder.encode(serverClientAccountDto.getPassword()));
		serverClientAccount.setAdministratorCompte(serverClientAccountDto.getAdministratorCompte());
		return ServerClientAccountMapper.toServerClientAccountDto(serverClientRepository.save(serverClientAccount));
	}
	
	public void deleteServerClientAccount(Long id) {
		serverClientRepository.deleteById(id);
	}
}
