package orm;
// Generated 06-ene-2018 11:57:57 by Hibernate Tools 5.2.3.Final

import java.util.HashSet;
import java.util.Set;

/**
 * Mediodepublicacion generated by hbm2java
 */
public class Mediodepublicacion implements java.io.Serializable {

	private String nombre;
	private Set publicacions = new HashSet(0);
	private Set series = new HashSet(0);

	public Mediodepublicacion() {
	}

	public Mediodepublicacion(String nombre) {
		this.nombre = nombre;
	}

	public Mediodepublicacion(String nombre, Set publicacions, Set series) {
		this.nombre = nombre;
		this.publicacions = publicacions;
		this.series = series;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<Publicacion> getPublicacions() {
		return this.publicacions;
	}

	public void setPublicacions(Set<Publicacion> publicacions) {
		this.publicacions = publicacions;
	}

	public Set getSeries() {
		return this.series;
	}

	public void setSeries(Set<Serie> series) {
		this.series = series;
	}
	public String toString() {
		return nombre; 
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mediodepublicacion other = (Mediodepublicacion) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}
}
