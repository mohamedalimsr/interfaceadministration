package fr.pfe.dto;

import java.util.Date;

import fr.pfe.enumeration.EtatBoitier;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class BoitierDto {

	private long idBoitier;
	private String label;
	private long numBoitier;
	private EtatBoitier etatBoitier;
	private Long streamId;
	private Long rawstream_id;
	private String matricule;	
	private String Etat;
	private double latitude;	
	private double longitude;	
	private String validite;	
	private double vitesse;	
	private String Ignition;	
	private String gps;	
	private String gsm;	
	private Date dateGps;	
	private Date dateServeur;	
	private Long idTrajet;	
	private Date dateOption;	
	private String hlr;	
	private String imei;	
	private String version;
}
