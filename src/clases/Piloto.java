package clases;

import java.util.ArrayList;

public class Piloto extends Staff{

	private ArrayList<Carrera> carreras;

	public Piloto(String nombre, String pais, int codEscuderia, ArrayList<Carrera> carreras) 
	{
		super(nombre, pais, codEscuderia);
		this.carreras = carreras;
	}

	public ArrayList<Carrera> getCarreras() {
		return carreras;
	}

	public void setCarreras(ArrayList<Carrera> carreras) {
		this.carreras = carreras;
	}

	@Override
	public String toString() {
		return "Piloto [carreras=" + carreras + ", nombre=" + nombre + ", pais=" + pais + ", codEscuderia="
				+ codEscuderia + "]";
	}
}
