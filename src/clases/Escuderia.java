package clases;

import java.io.Serializable;

public class Escuderia implements Serializable {

	private static final long serialVersionUID = 5298755273539553201L;
	private int codEscu;
	private String nombre;
	
	public Escuderia(int codEscu, String nombre) 
	{
		this.codEscu = codEscu;
		this.nombre = nombre;
	}

	public int getCodEscu() {
		return codEscu;
	}

	public void setCodEscu(int codEscu) {
		this.codEscu = codEscu;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Escuderia [codEscu=" + codEscu + ", nombre=" + nombre + "]";
	}
}
