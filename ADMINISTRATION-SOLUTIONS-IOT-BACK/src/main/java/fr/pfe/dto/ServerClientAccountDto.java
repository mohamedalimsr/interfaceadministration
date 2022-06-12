package fr.pfe.dto;

import java.util.Date;

import fr.pfe.entities.AdministratorAccount;
import lombok.Data;

@Data
public class ServerClientAccountDto {

	private Long idServerClientAccount;
	private String username;
	private String email;
	private String password;
	private String pseudo;
	private String ipAdresse;
	private long intervaleStart;
	private long intervaleEnd;
	private Date dateCreation;
	private Date dateExpiration;
	private RoleDto role;
	private AdministratorAccount administratorCompte;

	public ServerClientAccountDto() {
		super();
	}

	public ServerClientAccountDto(Long idServerClientAccount, String pseudo, String ipAdresse, long intervaleStart,
			long intervaleEnd, Date dateCreation, Date dateExpiration, AdministratorAccount administratorCompte,
			RoleDto role) {
		super();
		this.idServerClientAccount = idServerClientAccount;
		this.pseudo = pseudo;
		this.ipAdresse = ipAdresse;
		this.intervaleStart = intervaleStart;
		this.intervaleEnd = intervaleEnd;
		this.dateCreation = dateCreation;
		this.dateExpiration = dateExpiration;
		this.administratorCompte = administratorCompte;
		this.role = role;
	}

}
