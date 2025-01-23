package clases;

public class Mecanico  extends Staff{

	private static final long serialVersionUID = -2158303457022118758L;
	private Puesto puesto;

	public Mecanico(String nombre, String pais, int codEscuderia, Puesto puesto) 
	{
		super(nombre, pais, codEscuderia);
		this.puesto = puesto;
	}

	public Puesto getPuesto() {
		return puesto;
	}

	public void setPuesto(Puesto puesto) {
		this.puesto = puesto;
	}

	@Override
	public String toString() {
		return "Mecanico [puesto=" + puesto + ", nombre=" + nombre + ", pais=" + pais + ", codEscuderia=" + codEscuderia
				+ "]";
	}
}
