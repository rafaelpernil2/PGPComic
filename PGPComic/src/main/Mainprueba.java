package main;

import orm.Comic;
import orm.Mediodepublicacion;
import orm.MediodepublicacionHome;
import orm.Publicacion;
import orm.Serie;
import orm.SerieHome;

public class Mainprueba {
	public static void main(String[] args) {
		System.out.println("prueba");
		SerieHome sh = new SerieHome();
		MediodepublicacionHome mh = new MediodepublicacionHome();
		// ComicHome ch = new ComicHome();
		Serie s = sh.findById(2);
		Mediodepublicacion medio = mh.findById("YouPorn");
		System.out.println(s.getNombre());
		medio.getSeries().clear();
		mh.attachDirty(medio);
		s.getMediodepublicacions().clear();
		sh.attachDirty(s);
		for (Serie s1 : sh.list()) {
			System.out.println(s1.getNombre());
		}

		// for (Mediodepublicacion c : s.getMediodepublicacions()) {
		// System.out.println(c.getNombre());
		//
		// if (!c.getPublicacions().isEmpty()) {
		// for (Publicacion p : c.getPublicacions()) {
		// System.out.println(
		// p.getId().getFecha() + p.getComic().getNombre() +
		// p.getId().getComicSerieIdserie());
		// ;
		// }
		//
		// }
		// }
		//
		// for (Comic c4 : s.getComics()) {
		// if (!c4.getPublicacions().isEmpty()) {
		// System.out.println(c4.getNombre() + "Publicaciones:");
		//
		// for (Publicacion p : c4.getPublicacions()) {
		// System.out.println(p.getId().getFecha() +
		// p.getMediodepublicacion().getNombre());
		// }
		// }
	}

}
