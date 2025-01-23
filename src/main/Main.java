package main;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import clases.*;
import utilidades.MyObjectOutputStream;
import utilidades.Utilidades;

public class Main {

	public static int mostrarMenu() {
		System.out.println("0. Salir");
		System.out.println("1. Mostrar los ganadores por escudería ordenados por carrera. ");
		System.out.println("2. Modificar nombre de la escudería.  ");
		System.out.println("3. Insertar mecánico ");
		System.out.println("4. Visualizar:");
		return Utilidades.leerInt();
	}
	
	public static void fillData(File fichStaff,File fichEscuderias)
	{
		Escuderia ferrari = new Escuderia(1, "Ferrari");
        Escuderia redBull = new Escuderia(2, "Red Bull");

        
        Carrera c1 = new Carrera(1, "Suzuka", false);
        Carrera c2 = new Carrera(2, "Silverstone", true);
        Carrera c3 = new Carrera(3,"Monza",true);
        ArrayList<Carrera> car1 = new ArrayList<>();
        car1.add(c1);
        car1.add(c2);
        car1.add(c3);
        Piloto piloto1 = new Piloto("Carlos Sainz", "España",1,car1 );
        
        Carrera a1 = new Carrera(1, "Suzuka", true);
        Carrera a2 = new Carrera(2, "Silverstone", false);
        Carrera a3 = new Carrera(3,"Monza",false);
        ArrayList<Carrera> car2 = new ArrayList<>();
        car2.add(a1);
        car2.add(a2);
        car2.add(a3);
        
        Piloto piloto2 = new Piloto("Mark Webber", "Francia",2,car2 );
        
        Mecanico mecanico1 = new Mecanico("Laura", "Finlandia", 1, Puesto.JUNIOR);
        Mecanico mecanico2 = new Mecanico("Sara", "Rumania", 1, Puesto.SENIOR);
        
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fichEscuderias))) {
            try {
				oos.writeObject(ferrari);
				oos.writeObject(redBull);
				oos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
        } catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        

        // Guardar staff en el archivo Staff.dat
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fichStaff))) {
            try {
				oos.writeObject(piloto1);
				oos.writeObject(piloto2);
				oos.writeObject(mecanico1);
				oos.writeObject(mecanico2);
				oos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

        System.out.println("Datos guardados exitosamente en los archivos.");
    }
	
	public static void modificarNombreEscuderia(File fich)
	{
		String nombre,nuevoNombre;
		
		boolean modificado = false;
		boolean endOfFile = false;
		
		File fichAux = new File("fichAux.dat");
		
		System.out.println("Introduce el nombre que quieres cambiar");
		nombre = Utilidades.introducirCadena();
		
		if (fich.exists()) {
			try {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fich));
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fichAux));

				// Leer mientras no se alcance el fin del archivo
				while (!endOfFile&&!modificado) {
					try {
						Escuderia aux = (Escuderia) ois.readObject();
						if (aux.getNombre().equalsIgnoreCase(nombre)) 
						{
							System.out.println(aux.toString());
							System.out.println("Cual es el nuevo Nombre?");
							nuevoNombre = Utilidades.introducirCadena();
							aux.setNombre(nuevoNombre);
							
							modificado = true;
						} else {
							System.out.println("No hay una escuderia con ese nombre");
						}
						oos.writeObject(aux);
					} catch (EOFException e) {
						// Fin del archivo alcanzado	
						endOfFile = false;
					}
				}
				oos.close();
				ois.close();
				if (modificado) 
				{
					System.out.println("Archivo modificado");
					if (fich.delete()) {
						fichAux.renameTo(fich);
					}
				}

			} catch (Exception e) {
				System.out.println("Fatal error");
			}
		} else {
			System.out.println("Fichero nuevo");
		}
	
	}
	
	public static void insertarMecanico(File fich)
	{
		String nombre,pais,puestoIntroducido;
		int codEscuderia;
		Puesto p = null;
		
		if (fich.exists()) 
		{
			System.out.println("El fichero ya existe, se añadirán al final");
			MyObjectOutputStream moos;
			try {
				moos = new MyObjectOutputStream(new FileOutputStream(fich, true));				
				System.out.println("Introduce un Nombre: ");
				nombre = Utilidades.introducirCadena();
				System.out.println("Introduce el pais:");
				pais = Utilidades.introducirCadena();
				System.out.println("Introduce el codigo Escuderia");
				codEscuderia = Utilidades.leerInt();
				do {
					System.out.println("Introduce el Puesto: Senior/Junior ");
					puestoIntroducido = Utilidades.introducirCadena().toUpperCase();
					try {
						p = Puesto.valueOf(puestoIntroducido);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} while (puestoIntroducido==null);
				Mecanico aux = new Mecanico(nombre,pais,codEscuderia,p);
				System.out.println("Mecanico creado");
				System.out.println(aux.toString());
				moos.writeObject(aux);
				System.out.println("Se ha guardado Correctamente");
				moos.close();
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void visualizar(File fichStaff,File fichEscuderias)
	{
		boolean finArchivo;
		
		if (fichEscuderias.exists()) {
	        System.out.println("Escuderías:");
	        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichEscuderias))) {
	            finArchivo = false;
	            while (!finArchivo) {
	                try {
	                    Escuderia escuderia = (Escuderia) ois.readObject();
	                    System.out.println(escuderia);
	                } catch (EOFException e) {
	                    finArchivo = true; // Fin del archivo alcanzado
	                }
	            }
	        } catch (IOException | ClassNotFoundException e) {
	            System.err.println("Error al leer el archivo de escuderías: " + e.getMessage());
	        }
	    } else {
	        System.out.println("No existe el archivo de escuderías.");
	    }
		
		if (fichStaff.exists()) 
		{
			System.out.println("Staff y mecanicos:");
	        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichStaff))) {
	        	finArchivo = false;
	            while (!finArchivo) {
	                try {
	                    Staff staff = (Staff) ois.readObject();
	                    System.out.println(staff);
	                } catch (EOFException e) {
	                    finArchivo = true; // Fin del archivo alcanzado
	                }
	            }
	        } catch (IOException | ClassNotFoundException e) {
	            System.err.println("Error al leer el archivo de staff: " + e.getMessage());
	        }
	    } else {
	        System.out.println("No existe el archivo de staff.");
	    }
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		File fichStaff = new File("Staff.dat");
		File fichEscuderias = new File("Escuderias.dat");
		
		fillData(fichStaff,fichEscuderias);
		
		int opcion;
		
		do {
			opcion = mostrarMenu();
			switch (opcion) {
			case 1:
				
				break;
			case 2:
				 modificarNombreEscuderia(fichEscuderias);
				break;
			case 3:
				insertarMecanico(fichStaff);
				break;
			case 4:
				visualizar(fichStaff,fichEscuderias);
				break;
			}
		} while (opcion != 0);
	}

}
