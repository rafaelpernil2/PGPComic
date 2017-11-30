import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
	private static SessionFactory factory;
	public static void main(String[] args) {
		Configuration cfg = new Configuration().addResource("Comic.hbm.xml");
		 try {
	         factory = cfg.configure().buildSessionFactory();
	      } catch (Throwable ex) { 
	         System.err.println("Failed to create sessionFactory object." + ex);
	         throw new ExceptionInInitializerError(ex); 
	      }
		 ComicHome comicManagement = new ComicHome();
		 	comicManagement.delete(comicManagement.findById(2));
			Comic c = new Comic (2,"Pergamper");
			Comic c2 = new Comic (3,"PerGamPer 2: La venganza");
			comicManagement.attachClean(c);
			comicManagement.attachClean(c2);
//			comicManagement.delete(comicManagement.findById(1));
		 factory.close();
	}
	
}
