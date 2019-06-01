package it.polito.tdp.extflightdelays.model;

import java.util.Comparator;

public class Evento implements Comparator<Evento>{
	
	public enum TIPO {
		SPOSTAMENTO_TURISTI,
		
	}
	
	private int giorno;
	
	private String stato;
	
	private int numTuristi;

	public int getGiorno() {
		return giorno;
	}

	public String getStato() {
		return stato;
	}

	public int getNumTuristi() {
		return numTuristi;
	}

	public Evento(int giorno, String stato, int numTuristi) {
		super();
		this.giorno = giorno;
		this.stato = stato;
		this.numTuristi = numTuristi;
	}

	@Override
	public int compare(Evento e1, Evento e2) {
		// TODO Auto-generated method stub
		return e1.getGiorno()-e2.getGiorno();
	}
	
	

}
