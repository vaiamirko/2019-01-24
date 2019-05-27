package it.polito.tdp.extflightdelays.model;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

import it.polito.tdp.extflightdelays.db.ExtFlightDelaysDAO;

public class Model {
	private Graph<String, DefaultWeightedEdge> graph;
	private List<String> stati;
	ExtFlightDelaysDAO dao = new ExtFlightDelaysDAO();

	public Model(){
		this.stati = dao.loadAllStates();
	}
	
	public List<String> getStati(){
		return this.stati;
	}
	
	public void creaGrafo() {
		this.graph = new DefaultDirectedWeightedGraph<>(DefaultWeightedEdge.class) ;
		Graphs.addAllVertices(graph, stati);
		
		for(String stato1 : stati){
			for(String stato2 : stati){
				int nVeivoli = dao.getVeivoli(stato1,stato2);
				DefaultWeightedEdge e = graph.getEdge(stato1, stato2);
				if (e == null) {
					Graphs.addEdgeWithVertices(graph, stato1, stato2, nVeivoli);
				}
			}
		}
		System.out.println("Graphs vertex set: " + graph.vertexSet().size());
		System.out.println("Graphs edge set: " + graph.edgeSet().size());
	}

	public String visualizzaVoli(String stato) {
		String res = "";
		if(this.graph == null)
			return "Devi prima creare il grafo!";
		
		List<DefaultWeightedEdge> outgoing = new LinkedList<DefaultWeightedEdge>();
		outgoing.addAll(this.graph.outgoingEdgesOf(stato));
		Collections.sort(outgoing, new Comparator<DefaultWeightedEdge>(){

			@Override
			public int compare(DefaultWeightedEdge o1, DefaultWeightedEdge o2) {
				return (int)graph.getEdgeWeight(o2) - (int)graph.getEdgeWeight(o1);
			}
			
		});
		
		for(DefaultWeightedEdge edge : outgoing){
			if(graph.getEdgeTarget(edge) != stato && graph.getEdgeWeight(edge) > 0){
				res += graph.getEdgeTarget(edge);
				res += "\t";
				res += graph.getEdgeWeight(edge);
				res += "\n";
			}
		}
		return res;
	}



}
