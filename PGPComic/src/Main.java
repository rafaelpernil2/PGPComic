import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
/*
 * Ejemplo de funcionamiento
 * 
 * 
 * */
public class Main {
	private static SessionFactory factory;

	public static void main(String[] args) {
		Configuration cfg = new Configuration();
		try {
			factory = cfg.configure().buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
		// Se instancian los controladores
		ComicHome comicManagement = new ComicHome(); 
		SerieHome serieManagement = new SerieHome();
		// ComicId es una clase autogenerada que implementa equals y hashcode para identificar cada Comic, al igual que para Publicación
		ComicId cId = new ComicId(1, 1);
		if (comicManagement.findById(cId)!=null){ //Si existe
			comicManagement.delete(comicManagement.findById(cId)); // Lo borra
		}
		if (serieManagement.findById(1) !=null){
			serieManagement.delete(serieManagement.findById(1));
		}
		
		Serie superman = new Serie(1, "Superman", "Superheroes"); // Importante crear primero la serie
		serieManagement.attachClean(superman); // Y actualizar la persistencia
		Comic c = new Comic(cId, superman, "Superheroes"); // Luego creamos el comic
		comicManagement.attachClean(c); // Y actualizamos la persistencia
		factory.getCurrentSession().close();
		factory.close(); // Cerramos la factory
	}

}
