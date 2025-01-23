package clases;

import java.io.Serializable;

public class Carrera implements Serializable{

	private static final long serialVersionUID = 8192661511513136483L;
	private int CodCarrera;
	private String nombCarrera;
	private boolean ganador;
	
	public Carrera(int codCarrera, String nombCarrera, boolean ganador) 
	{
		CodCarrera = codCarrera;
		this.nombCarrera = nombCarrera;
		this.ganador = ganador;
	}

	public int getCodCarrera() {
		return CodCarrera;
	}

	public void setCodCarrera(int codCarrera) {
		CodCarrera = codCarrera;
	}

	public String getNombCarrera() {
		return nombCarrera;
	}

	public void setNombCarrera(String nombCarrera) {
		this.nombCarrera = nombCarrera;
	}

	public boolean isGanador() {
		return ganador;
	}

	public void setGanador(boolean ganador) {
		this.ganador = ganador;
	}

	@Override
	public String toString() {
		return "Carrera [CodCarrera=" + CodCarrera + ", nombCarrera=" + nombCarrera + ", ganador=" + ganador + "]";
	}
	
	
}
