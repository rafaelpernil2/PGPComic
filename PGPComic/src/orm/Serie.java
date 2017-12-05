package orm;
// Generated 02-dic-2017 22:16:46 by Hibernate Tools 5.2.3.Final

import java.util.HashSet;
import java.util.Set;

/**
 * Serie generated by hbm2java
 */
public class Serie implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idserie;
	private String nombre;
	private String anotacionPublica;
	private String genero;
	private Set<Comic> comics = new HashSet<Comic>(0);
	private Set<Mediodepublicacion> mediodepublicacions = new HashSet<Mediodepublicacion>(0);

	public Serie() {
	}

	public Serie(int idserie, String nombre, String genero) {
		this.idserie = idserie;
		this.nombre = nombre;
		this.genero = genero;
	}

	public Serie(int idserie, String nombre, String anotacionPublica, String genero, Set<Comic> comics,
			Set<Mediodepublicacion> mediodepublicacions) {
		this.idserie = idserie;
		this.nombre = nombre;
		this.anotacionPublica = anotacionPublica;
		this.genero = genero;
		this.comics = comics;
		this.mediodepublicacions = mediodepublicacions;
	}

	public int getIdserie() {
		return this.idserie;
	}

	public void setIdserie(int idserie) {
		this.idserie = idserie;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAnotacionPublica() {
		return this.anotacionPublica;
	}

	public void setAnotacionPublica(String anotacionPublica) {
		this.anotacionPublica = anotacionPublica;
	}

	public String getGenero() {
		return this.genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public Set<Comic> getComics() {
		return this.comics;
	}

	public void setComics(Set<Comic> comics) {
		this.comics = comics;
	}

	public Set<Mediodepublicacion> getMediodepublicacions() {
		return this.mediodepublicacions;
	}

	public void setMediodepublicacions(Set<Mediodepublicacion> mediodepublicacions) {
		this.mediodepublicacions = mediodepublicacions;
	}
	public String toString() {
		
		return " Serie:" + nombre + " Genero:" + genero; 
	}

}
