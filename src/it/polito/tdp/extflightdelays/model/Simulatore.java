package it.polito.tdp.extflightdelays.model;

import java.beans.DesignMode;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;

public class Simulatore {
	//coda degli eventi
	
	PriorityQueue<Evento> queue ;
	
	//parametri di simulazione
	private int T;
	private String STATOINIZIALE;
	private int G;
	private Graph grafo;
	private List<String> destinazioni;
	
	private Model model;
	
	//statistiche
	Map<String,Integer> MappaSituazioneTuristi;
	
	
	
	
	
	
	
	public void init(int T,int G,String STATOINIZIALE,Graph grafo,Model model) {
		queue = new PriorityQueue<>();
		queue.clear();
		this.setT(T);
		this.setG(G);
		this.setSTATOINIZIALE(STATOINIZIALE);
		this.model=model;
		this.grafo=grafo;
		this.destinazioni=model.getStatiVicini(STATOINIZIALE);
		//inizializzo la mappa;
		for(String stato : destinazioni) {
			MappaSituazioneTuristi.put(stato, 0);
		}
		
		MappaSituazioneTuristi.put(STATOINIZIALE, T);
		
		queue.add(new Evento(G, STATOINIZIALE, T));
		
		
		
		
	}
	
	public void simulazione(){
		
		
		while(queue.isEmpty()==false) {
			
			//estraggo l'evento
			
			Evento e = queue.poll();
			
			if(e.getGiorno()==G) {//se sono all'ultimo giorno non creerò + eventi
				
				
				
			}else {
				
				String statoattuale = e.getStato();
				
				destinazioni.clear();
				
				destinazioni = model.getStatiVicini(statoattuale);
				
				
				
				
				
				
				
			}
			
		}
		
	}

	public int getT() {
		return T;
	}

	public void setT(int t) {
		T = t;
	}

	public String getSTATOINIZIALE() {
		return STATOINIZIALE;
	}

	public void setSTATOINIZIALE(String sTATOINIZIALE) {
		STATOINIZIALE = sTATOINIZIALE;
	}

	public int getG() {
		return G;
	}

	public void setG(int g) {
		G = g;
	}
	public int calcolaProb(String state) {
		
		int sommapesi=0;
		int pesoStato;
		int probabilita=0;
		
		List<DefaultWeightedEdge> archi = new LinkedList<>();
		Map<String,Integer> mappapesi = new HashMap<>();
		
		archi.addAll(grafo.outgoingEdgesOf(state));
		
		for(DefaultWeightedEdge e : archi) {
			sommapesi+=grafo.getEdgeWeight(e);
			mappapesi.put((String) grafo.getEdgeTarget(e), (int) grafo.getEdgeWeight(e));
		}
		
		probabilita=(mappapesi.get(state))/sommapesi;
		
		return probabilita;
		
		
		
		
	}
	
	
	

}
