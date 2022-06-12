package fr.pfe.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import fr.pfe.enumeration.EtatBoitier;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@Table(name = "BOITIER")
public class Boitier implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BOITIER_ID", length = 20)
	private long idBoitier;

	@Column(name = "LABEL", length = 100)
	private String label;

	@Column(name = "NUM_BOITIER")
	private long numBoitier;

	@Enumerated(EnumType.STRING)
	private EtatBoitier etatBoitier;

	@Column(name = "stream_id")
	private Long streamId;

	@Column(name = "rawstream_id")
	private Long rawstream_id;
	
	@Column(name = "matricule")
	private String matricule;
	
	@Column(name = "etat")
	private String Etat;
	
	@Column(name = "latitude")
	private double latitude;
	
	@Column(name = "longitude")
	private double longitude;
	
	@Column(name = "validite")
	private String validite;
	
	@Column(name = "vitesse")
	private double vitesse;
	
	@Column(name = "ignition")
	private String Ignition;
	
	@Column(name = "gps")
	private String gps;
	
	@Column(name = "gsm")
	private String gsm;
	
	@Column(name = "date_gps")
	private Date dateGps;
	
	@Column(name = "date_serveur")
	private Date dateServeur;
	
	@Column(name = "id_trajet")
	private Long idTrajet;
	
	@Column(name = "date_option")
	private Date dateOption;
	
	@Column(name = "hlr")
	private String hlr;
	
	@Column(name = "imei")
	private String imei;
	
	@Column(name = "version")
	private String version;
}
