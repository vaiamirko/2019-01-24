package it.polito.tdp.extflightdelays.model;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		
		model.creaGrafo();
		System.out.println("numero di vertici e di archi"+model.grafo.vertexSet().size()+" "+model.grafo.edgeSet().size());
		System.out.println(model.visulizzaVelivoli3("AK"));
	}

}
