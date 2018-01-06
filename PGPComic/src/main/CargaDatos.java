package main;

import java.util.HashSet;
import java.util.Set;

import orm.Comic;
import orm.ComicHome;
import orm.ComicId;
import orm.Mediodepublicacion;
import orm.MediodepublicacionHome;
import orm.PublicacionHome;
import orm.Serie;
import orm.SerieHome;

public class CargaDatos {

	public static void main(String[] args) {
		// Se instancian los controladores
		ComicHome comicManagement = new ComicHome();
		SerieHome serieManagement = new SerieHome();
		MediodepublicacionHome mediopubManagement = new MediodepublicacionHome();
		PublicacionHome pubManagement = new PublicacionHome();
		
		// ComicId es una clase autogenerada que implementa equals y hashcode
		// para identificar cada Comic, al igual que para Publicación
		comicManagement.deleteAll();
		serieManagement.deleteAll();
		mediopubManagement.deleteAll();
		pubManagement.deleteAll();

		Serie superman = new Serie(1, "Superman", "Superheroes");
		Serie batman = new Serie (2,"Batman","Superheroes");
		serieManagement.attachClean(superman); // Y actualizar la persistencia
		serieManagement.attachClean(batman);
//		Comic superman1 = new Comic(new ComicId(1,1), superman, "Superman"); 
//		Comic superman2 = new Comic (new ComicId(2,1),superman, "Superman 2");
//		Comic batman1 = new Comic (new ComicId(3,2),batman,"Batman Origins");
		Mediodepublicacion html = new Mediodepublicacion("HTML");
		Mediodepublicacion wordpress = new Mediodepublicacion("Wordpress");
		mediopubManagement.attachClean(html);
		mediopubManagement.attachClean(wordpress);
		Set<Mediodepublicacion> mediosDefecto = new HashSet<Mediodepublicacion>();
		mediosDefecto.add(html);
		mediosDefecto.add(wordpress);
		superman.setMediodepublicacions(mediosDefecto);
//		serieManagement.attachDirty(superman);
//		comicManagement.attachClean(superman1);
//		comicManagement.attachClean(superman2);
//		comicManagement.attachClean(batman1);
		System.exit(0);
//		
//		
//		// Subir fotos
//		 FileInputStream foto = null;
//		 try {
//		 foto = new FileInputStream(new File ("foto.png"));
//		 } catch (FileNotFoundException e1) {
//		 e1.printStackTrace();
//		 }
//		 byte[] blob=null;
//		 try {
//		 blob = IOUtils.toByteArray(foto); // Pasa la foto a array de bytes
//		 } catch (IOException e) {
//		 // TODO Auto-generated catch block
//		 e.printStackTrace();
//		 }
//		 superman1.setFoto(blob); // Añadir fotos
//		comicManagement.attachClean(superman1); // Y actualizamos la persistencia
//		superman = serieManagement.findById(superman.getIdserie());
//		for (Comic comi : superman.getComics()) {
//			System.out.println(comi.getNombre());
//		}
//		
//		System.out.println("Get comics: " + serieManagement.findById(1));
//		
//		System.out.println(comicManagement.list());
//		byte[] fotoEnBytes = comicManagement.findById(superman1.getId()).getFoto();
//		try {
//			FileUtils.writeByteArrayToFile(new File("resultado.png"), fotoEnBytes);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
	}

}
