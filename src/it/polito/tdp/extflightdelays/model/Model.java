package it.polito.tdp.extflightdelays.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import it.polito.tdp.extflightdelays.db.ExtFlightDelaysDAO;

public class Model {
	
	
	Graph<String,DefaultWeightedEdge> grafo = new DefaultDirectedWeightedGraph<>(DefaultWeightedEdge.class);
	
	
	
	
	public void creaGrafo() {
		ExtFlightDelaysDAO dao = new ExtFlightDelaysDAO();
		
		//ho aggiunto tutti i vertici del grafo
		Graphs.addAllVertices(grafo,dao.loadAllStates());
		System.out.println("creai vertici del grafo"+grafo.vertexSet());
		
		/*List<Collegamento> listaarchi = new ArrayList(dao.loadAllArchi());
		
		for(Collegamento c : listaarchi) {
			grafo.addEdge(c.getStatoPartenza(), c.getStatoArrivo());
		}
		
		System.out.format("creato grafo con numero di archi %d e numero di vertici %d", grafo.edgeSet().size(),grafo.vertexSet().size());
		*/
		
		for(String statop : grafo.vertexSet()) {
			for(String statoa : grafo.vertexSet()) {
				
				int peso=dao.getPesoArco(statop, statoa);
				
				DefaultWeightedEdge e = grafo.getEdge(statop, statoa);
				if(e==null)
					Graphs.addEdgeWithVertices(grafo, statop, statoa, peso);
				
			}
		}
		System.out.println("Graphs vertex set: " + grafo.vertexSet().size());
		System.out.println("Graphs edge set: " + grafo.edgeSet().size());
		
		
		
		
	}
	
	public List<ArcoPesato> visualizzaVelivoli(String stato){
		List<ArcoPesato> listavelivoli = new ArrayList<ArcoPesato>();
		
		List<String> temp = new ArrayList(Graphs.neighborListOf(grafo, stato));
		
		for(String s : temp) {
			if(!s.equals(stato) && grafo.getEdgeWeight(grafo.getEdge(s, stato))>0 && grafo.getEdgeWeight(grafo.getEdge(stato, s))>0 && !listavelivoli.contains(new ArcoPesato(s,grafo.getEdgeWeight(grafo.getEdge(stato, s)))))
			listavelivoli.add(new ArcoPesato(s,grafo.getEdgeWeight(grafo.getEdge(stato, s))));
			
		}
		
		Collections.sort(listavelivoli);
		System.out.println(listavelivoli.toString()+"numero elementi "+listavelivoli.size());
		return listavelivoli;
	}
	
	
	public List<ArcoPesato> visulizzaVelivoli2(String stato) {
List<ArcoPesato> listavelivoli = new ArrayList<ArcoPesato>();
		
		
		
		
		List<DefaultWeightedEdge> outgoing = new LinkedList<DefaultWeightedEdge>();
		outgoing.addAll(this.grafo.outgoingEdgesOf(stato));
		Collections.sort(outgoing, new Comparator<DefaultWeightedEdge>(){

			@Override
			public int compare(DefaultWeightedEdge o1, DefaultWeightedEdge o2) {
				return (int)grafo.getEdgeWeight(o2) - (int)grafo.getEdgeWeight(o1);
			}
			
		});
		
		for(DefaultWeightedEdge edge : outgoing)
		 {
			if(!grafo.getEdgeTarget(edge).equals(stato) && grafo.getEdgeWeight(edge)>0)
			listavelivoli.add(new ArcoPesato(grafo.getEdgeTarget(edge),grafo.getEdgeWeight(edge)));
			
		}
		
		Collections.sort(listavelivoli);
		System.out.println(listavelivoli.toString());
		return listavelivoli;
	}
	
	public String visulizzaVelivoli3(String stato) {
		
				
				String res = "";
				
				List<DefaultWeightedEdge> outgoing = new LinkedList<DefaultWeightedEdge>();
				outgoing.addAll(this.grafo.outgoingEdgesOf(stato));
				
				Collections.sort(outgoing, new Comparator<DefaultWeightedEdge>() {

					@Override
					public int compare(DefaultWeightedEdge o1, DefaultWeightedEdge o2) {
						// TODO Auto-generated method stub
						return (int) (grafo.getEdgeWeight(o1)-grafo.getEdgeWeight(o2));
					}
					
				});
				for(DefaultWeightedEdge edge : outgoing){
					if(grafo.getEdgeTarget(edge) != stato && grafo.getEdgeWeight(edge) > 0){
						res += grafo.getEdgeTarget(edge);
						res += "\t";
						res += grafo.getEdgeWeight(edge);
						res += "\n";
					}
				}
				return res;
	}
	
	public List<String> getStati(){
		ExtFlightDelaysDAO dao = new ExtFlightDelaysDAO();
		List<String> listaStati = new ArrayList(dao.loadAllStates());
		
		return listaStati;
		
	}
	
	public List<String> getStatiVicini(String stato){
		List<String> lista = new ArrayList();
		List<DefaultWeightedEdge> archi = new LinkedList(grafo.outgoingEdgesOf(stato));
		
		for(DefaultWeightedEdge edge : archi) {
			if(!lista.contains(grafo.getEdgeTarget(edge)))
			lista.add(grafo.getEdgeTarget(edge));
		}
		
		return lista;
		
		
	}
	
	
}
