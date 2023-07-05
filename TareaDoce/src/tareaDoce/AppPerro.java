package tareaDoce;

public class AppPerro {
	  public static void main(String[] args) {
	        Perro perro1 = new Perro("Fido", 5);
	        Perro perro2 = new Perro("Rex", 3);

	        System.out.println("Perro 1: " + perro1.getNombre() + ", Edad: " + perro1.getEdad());
	        System.out.println("Perro 2: " + perro2.getNombre() + ", Edad: " + perro2.getEdad());
	    }
	}