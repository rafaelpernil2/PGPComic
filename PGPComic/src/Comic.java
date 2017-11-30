// default package
// Generated 28-nov-2017 17:59:17 by Hibernate Tools 5.2.3.Final
/**
 * Comic generated by hbm2java
 */
public class Comic implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idComic;
	private String nombre;
	private byte[] crossover;
	private Integer isbn;

	public Comic() {
	}

	public Comic(int idComic, String nombre) {
		this.idComic = idComic;
		this.nombre = nombre;
	}

	public Comic(int idComic, String nombre, byte[] crossover, Integer isbn) {
		this.idComic = idComic;
		this.nombre = nombre;
		this.crossover = crossover;
		this.isbn = isbn;
	}

	public int getIdComic() {
		return this.idComic;
	}

	public void setIdComic(int idComic) {
		this.idComic = idComic;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public byte[] getCrossover() {
		return this.crossover;
	}

	public void setCrossover(byte[] crossover) {
		this.crossover = crossover;
	}

	public Integer getIsbn() {
		return this.isbn;
	}

	public void setIsbn(Integer isbn) {
		this.isbn = isbn;
	}

}
