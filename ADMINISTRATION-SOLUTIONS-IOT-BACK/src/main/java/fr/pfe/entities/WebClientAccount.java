package fr.pfe.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@PrimaryKeyJoinColumn(name = "COMPTE_WEB_ID")
public class WebClientAccount extends User implements Serializable{
	private static final long serialVersionUID = 1L;

	@Column(name = "RAW_PASSWORD", length = 400)
	private String rawPassword;

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATED_AT")
	private Date date_creation;

	@Temporal(TemporalType.DATE)
	@Column(name = "EXPIRATION_DATE")
	private Date date_expiration;

	@Column(name = "CODE_PAYS", length = 25)
	private String code_pays;

	@Column(name = "POOL")
	private int pool;

	@Column(name = "telephone")
	private int telephone;

	@Column(name = "area")
	private String area;

	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH,
			CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "COMPTE_SERVER_ID")
	private ServerClientAccount sereServerClientAccount;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_admin")
	private AdministratorAccount administratorAccount;

	@Column(name = "notification_subquery")
	private String notificationSubquery;

	@Column(name = "mobile_notif")
	private boolean mobileNotif;

	@Column(name = "device_fee_by_day")
	private double deviceFeeByDay;

	@Column(name = "account_fee_by_month")
	private double accountFeeByMonth;

	@Column(name = "device_fee_per_month")
	private double deviceFeePerMonth;

	@Column(name = "sim_card_fee_per_month")
	private double simCardFeePerMonth;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_server")
	private ServerClientAccount serverClientAccount;
	
	public WebClientAccount(@NotBlank @Size(max = 20) String username, @NotBlank @Size(max = 50) @Email String email,
			@NotBlank @Size(max = 120) String password) {
		super(username,email,password);
	}
}
