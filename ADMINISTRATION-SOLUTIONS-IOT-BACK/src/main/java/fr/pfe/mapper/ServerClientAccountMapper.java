package fr.pfe.mapper;

import fr.pfe.dto.ServerClientAccountDto;
import fr.pfe.entities.ServerClientAccount;

public class ServerClientAccountMapper {

	private ServerClientAccountMapper() {
		
	}
	
	public static ServerClientAccountDto toServerClientAccountDto(ServerClientAccount serverClientAccount) {
		ServerClientAccountDto serverClientAccountDto = new ServerClientAccountDto();
		serverClientAccountDto.setIdServerClientAccount(serverClientAccount.getId());
		serverClientAccountDto.setUsername(serverClientAccount.getUsername());
		serverClientAccountDto.setPseudo(serverClientAccount.getPseudo());
		serverClientAccountDto.setPassword(serverClientAccount.getPassword());
		serverClientAccountDto.setIpAdresse(serverClientAccount.getIpAdresse());
		serverClientAccountDto.setIntervaleStart(serverClientAccount.getIntervaleStart());
		serverClientAccountDto.setIntervaleEnd(serverClientAccount.getIntervaleEnd());
		serverClientAccountDto.setEmail(serverClientAccount.getEmail());
		serverClientAccountDto.setDateExpiration(serverClientAccount.getDateExpiration());
		serverClientAccountDto.setDateCreation(serverClientAccount.getDateCreation());
		serverClientAccountDto.setRole(RoleMapper.toRoleDto(serverClientAccount.getRole()));
		return serverClientAccountDto;
	}
	
	public static ServerClientAccount toServerClientAccountBo(ServerClientAccountDto serverClientAccountDto) {
		ServerClientAccount serverClientAccount = new ServerClientAccount();
		serverClientAccount.setUsername(serverClientAccountDto.getUsername());
		serverClientAccount.setPseudo(serverClientAccountDto.getPseudo());
		serverClientAccount.setPassword(serverClientAccountDto.getPassword());
		serverClientAccount.setIpAdresse(serverClientAccountDto.getIpAdresse());
		serverClientAccount.setIntervaleStart(serverClientAccountDto.getIntervaleStart());
		serverClientAccount.setIntervaleEnd(serverClientAccountDto.getIntervaleEnd());
		serverClientAccount.setEmail(serverClientAccountDto.getEmail());
		serverClientAccount.setDateExpiration(serverClientAccountDto.getDateExpiration());
		serverClientAccount.setDateCreation(serverClientAccountDto.getDateCreation());
		return serverClientAccount;
	}
}
