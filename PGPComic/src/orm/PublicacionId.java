package orm;
// Generated 02-dic-2017 22:16:46 by Hibernate Tools 5.2.3.Final

import java.util.Date;

/**
 * PublicacionId generated by hbm2java
 */
public class PublicacionId implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date fecha;
	private int comicIdComic;
	private int comicSerieIdserie;
	private String medioDePublicacionNombre;

	public PublicacionId() {
	}

	public PublicacionId(Date fecha, int comicIdComic, int comicSerieIdserie, String medioDePublicacionNombre) {
		this.fecha = fecha;
		this.comicIdComic = comicIdComic;
		this.comicSerieIdserie = comicSerieIdserie;
		this.medioDePublicacionNombre = medioDePublicacionNombre;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getComicIdComic() {
		return this.comicIdComic;
	}

	public void setComicIdComic(int comicIdComic) {
		this.comicIdComic = comicIdComic;
	}

	public int getComicSerieIdserie() {
		return this.comicSerieIdserie;
	}

	public void setComicSerieIdserie(int comicSerieIdserie) {
		this.comicSerieIdserie = comicSerieIdserie;
	}

	public String getMedioDePublicacionNombre() {
		return this.medioDePublicacionNombre;
	}

	public void setMedioDePublicacionNombre(String medioDePublicacionNombre) {
		this.medioDePublicacionNombre = medioDePublicacionNombre;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof PublicacionId))
			return false;
		PublicacionId castOther = (PublicacionId) other;

		return ((this.getFecha() == castOther.getFecha()) || (this.getFecha() != null && castOther.getFecha() != null
				&& this.getFecha().equals(castOther.getFecha())))
				&& (this.getComicIdComic() == castOther.getComicIdComic())
				&& (this.getComicSerieIdserie() == castOther.getComicSerieIdserie())
				&& ((this.getMedioDePublicacionNombre() == castOther.getMedioDePublicacionNombre())
						|| (this.getMedioDePublicacionNombre() != null
								&& castOther.getMedioDePublicacionNombre() != null
								&& this.getMedioDePublicacionNombre().equals(castOther.getMedioDePublicacionNombre())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getFecha() == null ? 0 : this.getFecha().hashCode());
		result = 37 * result + this.getComicIdComic();
		result = 37 * result + this.getComicSerieIdserie();
		result = 37 * result
				+ (getMedioDePublicacionNombre() == null ? 0 : this.getMedioDePublicacionNombre().hashCode());
		return result;
	}

}