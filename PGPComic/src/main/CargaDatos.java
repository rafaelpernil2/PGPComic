package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.io.IOUtils;

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
		// para identificar cada Comic, al igual que para Publicaci�n
		comicManagement.deleteAll();
		serieManagement.deleteAll();
		mediopubManagement.deleteAll();
		pubManagement.deleteAll();

		Serie superman = new Serie(1, "Superman", "Superheroes");
		Serie batman = new Serie (2,"Batman","Superheroes");
		serieManagement.attachClean(superman); // Y actualizar la persistencia
		serieManagement.attachClean(batman);
		Comic superman1 = new Comic(1, "Superman");
		
		
      	Comic superman2 = new Comic (2,"Barman");
      	Comic batman1 = new Comic (3,"Sex over el telefonillo");
		Mediodepublicacion html = new Mediodepublicacion("HTML");
		Mediodepublicacion wordpress = new Mediodepublicacion("Wordpress");
		mediopubManagement.attachClean(html);
		mediopubManagement.attachClean(wordpress);
		Set<Mediodepublicacion> mediosDefecto = new HashSet<Mediodepublicacion>();
		mediosDefecto.add(html);
		mediosDefecto.add(wordpress);
		superman.setMediodepublicacions(mediosDefecto);
		serieManagement.attachDirty(superman);
		comicManagement.attachClean(superman1);
		comicManagement.attachClean(superman2);
		comicManagement.attachClean(batman1);

		
		
		// Subir fotos
		 FileInputStream foto = null;
		 try {
		 foto = new FileInputStream(new File ("perro.jpg"));
		 } catch (FileNotFoundException e1) {
		 e1.printStackTrace();
		 }
		 byte[] blob=null;
		 try {
		 blob = IOUtils.toByteArray(foto); // Pasa la foto a array de bytes
		 } catch (IOException e) {
		 // TODO Auto-generated catch block
		 e.printStackTrace();
		 }
		 superman1.setFoto(blob); // A�adir fotos
		comicManagement.attachDirty(superman1); // Y actualizamos la persistencia
		
		
		
		
		
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
