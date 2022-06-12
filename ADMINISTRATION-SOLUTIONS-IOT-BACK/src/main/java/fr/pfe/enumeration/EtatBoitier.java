package fr.pfe.enumeration;

public enum EtatBoitier {
	
	INSTALLED(0), NOT_INSTALLED(1);

	private int value;

	private EtatBoitier(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
