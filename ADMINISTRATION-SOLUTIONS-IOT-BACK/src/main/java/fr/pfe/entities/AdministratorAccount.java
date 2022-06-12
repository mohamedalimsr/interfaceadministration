package fr.pfe.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@PrimaryKeyJoinColumn(name = "COMPTE_ADMIN_ID")
public class AdministratorAccount extends User implements Serializable{
	private static final long serialVersionUID = 1L;

	@Column(name = "LAST_BOITIER")
	private long numLastBoitierAvailable;

	@OneToMany(mappedBy = "administratorAccount", fetch = FetchType.LAZY)
	private Set<WebClientAccount> comptesClientWeb;

	@OneToMany(mappedBy = "administratorCompte", fetch = FetchType.LAZY)
	private Set<ServerClientAccount> comptesClientServer;

	@Column(name = "use_fcm")
	private boolean useFcm;

	@Column(name = "fcm_apikey")
	private String fcmApikey;

	@Column(name = "fcm_prefix")
	private String fcmPrefix;

	@Column(name = "device_cost_by_day")
	private double deviceCostByDay;

	@Column(name = "account_free_per_month")
	private double accountFreePerMonth;

	@Column(name = "transction_fee")
	private double transctionFee;
	
	public AdministratorAccount(@NotBlank @Size(max = 20) String username, @NotBlank @Size(max = 50) @Email String email,
			@NotBlank @Size(max = 120) String password) {
		super(username,email,password);
	}
}
