package it.polito.tdp.extflightdelays.model;

public class Collegamento {
	
	

	public Collegamento(String statoPartenza, String statoArrivo) {
		super();
		this.statoPartenza = statoPartenza;
		this.statoArrivo = statoArrivo;
	}

	private String statoPartenza;
	
	private String statoArrivo;

	public String getStatoPartenza() {
		return statoPartenza;
	}

	public String getStatoArrivo() {
		return statoArrivo;
	}
	

}
