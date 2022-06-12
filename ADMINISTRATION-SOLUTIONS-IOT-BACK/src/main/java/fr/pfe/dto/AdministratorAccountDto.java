package fr.pfe.dto;


import lombok.Data;

@Data
public class AdministratorAccountDto {

	private Long idAdministratorAccount;
	private long numLastBoitierAvailable;
	private boolean useFcm;
	private String fcmApikey;
	private String fcmPrefix;
	private double deviceCostByDay;
	private double accountFreePerMonth;
	private double transctionFee;

	public AdministratorAccountDto() {
		super();
	}

	public AdministratorAccountDto(Long idAdministratorAccount, long numLastBoitierAvailable, boolean useFcm,
			String fcmApikey, String fcmPrefix, double deviceCostByDay, double accountFreePerMonth,
			double transctionFee) {
		super();
		this.idAdministratorAccount = idAdministratorAccount;
		this.numLastBoitierAvailable = numLastBoitierAvailable;
		this.useFcm = useFcm;
		this.fcmApikey = fcmApikey;
		this.fcmPrefix = fcmPrefix;
		this.deviceCostByDay = deviceCostByDay;
		this.accountFreePerMonth = accountFreePerMonth;
		this.transctionFee = transctionFee;
	}

	
}
