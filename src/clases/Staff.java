package clases;

import java.io.Serializable;

public class Staff implements Serializable{
	
	private static final long serialVersionUID = 4933203357985078004L;
	protected String nombre;
	protected String pais;
	protected int codEscuderia;
	
	public Staff(String nombre, String pais, int codEscuderia) 
	{
		this.nombre = nombre;
		this.pais = pais;
		this.codEscuderia = codEscuderia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public int getCodEscuderia() {
		return codEscuderia;
	}

	public void setCodEscuderia(int codEscuderia) {
		this.codEscuderia = codEscuderia;
	}

	@Override
	public String toString() {
		return "Staff [nombre=" + nombre + ", pais=" + pais + ", codEscuderia=" + codEscuderia + "]";
	}
}
