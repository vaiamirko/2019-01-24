package it.polito.tdp.extflightdelays.model;

public class ArcoPesato<T>  implements  Comparable<ArcoPesato> {
	
	private String state1;
	
	private double peso;

	public ArcoPesato(String state1, double peso){
		super();
		this.state1 = state1;
		this.peso = peso;
	}

	public String getState1() {
		return state1;
	}

	public double getPeso() {
		return peso;
	}

	public int compareTo(ArcoPesato p) {
		// TODO Auto-generated method stub
		
		
		return (int) -(this.peso-p.getPeso());
	}

	@Override
	public String toString() {
		return String.format("ArcoPesato [state1=%s, peso=%s]", state1, peso);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(peso);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((state1 == null) ? 0 : state1.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ArcoPesato other = (ArcoPesato) obj;
		if (Double.doubleToLongBits(peso) != Double.doubleToLongBits(other.peso))
			return false;
		if (state1 == null) {
			if (other.state1 != null)
				return false;
		} else if (!state1.equals(other.state1))
			return false;
		return true;
	}


	
	
	
	

}
