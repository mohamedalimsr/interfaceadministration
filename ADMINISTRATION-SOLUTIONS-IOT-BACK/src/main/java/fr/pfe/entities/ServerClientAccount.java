package fr.pfe.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@PrimaryKeyJoinColumn(name = "COMPTE_SERVER_ID")
public class ServerClientAccount extends User implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Column(name = "PSEUDO", unique = true, length = 25)
	private String pseudo;

	@Column(name = "IP_ADRESSE")
	private String ipAdresse;

	@Column(name = "INTERVALE_START")
	private long intervaleStart;

	@Column(name = "INTERVALE_END")
	private long intervaleEnd;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_AT")
	private Date dateCreation;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "EXPIRATION_DATE")
	private Date dateExpiration;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_admin")
	private AdministratorAccount administratorCompte;
	
	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.REMOVE,
			CascadeType.MERGE }, fetch = FetchType.LAZY, orphanRemoval = false)
	@OrderBy("idBoitier ASC")
	@JoinTable(name = "COMPTE_SERVER_BOITIERS", joinColumns = @JoinColumn(name = "COMPTE_SERVER_ID"), inverseJoinColumns = @JoinColumn(name = "BOITIER_ID"))
	private Set<Boitier> boitiers = new HashSet<Boitier>();
	
	public ServerClientAccount(@NotBlank @Size(max = 20) String username, @NotBlank @Size(max = 50) @Email String email,
			@NotBlank @Size(max = 120) String password) {
		super(username,email,password);
	}

}
