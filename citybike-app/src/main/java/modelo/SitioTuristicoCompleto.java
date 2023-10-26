package modelo;

import java.awt.Image;
import java.util.List;

public class SitioTuristicoCompleto extends SitioTuristico{
	
	private String resumen;
	private List<String> categorias;
	private List<String> enlacesComplementarios;
	private Image imagen;

	public SitioTuristicoCompleto(String nombre, String descripcion, double distancia, String urlWikipedia, String resumen, List<String> categorias, List<String> enlacesComplementarios, Image imagen) {
		super(nombre, descripcion, distancia, urlWikipedia);
		this.resumen = resumen;
		this.categorias = categorias;
		this.enlacesComplementarios = enlacesComplementarios;
		this.imagen = imagen;
	}

	public String getResumen() {
		return resumen;
	}

	public void setResumen(String resumen) {
		this.resumen = resumen;
	}

	public List<String> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<String> categorias) {
		this.categorias = categorias;
	}

	public List<String> getEnlacesComplementarios() {
		return enlacesComplementarios;
	}

	public void setEnlacesComplementarios(List<String> enlacesComplementarios) {
		this.enlacesComplementarios = enlacesComplementarios;
	}

	public Image getImagen() {
		return imagen;
	}

	public void setImagen(Image imagen) {
		this.imagen = imagen;
	}

	@Override
	public String toString() {
		 
		return super.toString() + " \n SitioTuristicoCompleto [resumen=" + resumen + ", categorias=" + categorias + ", enlacesComplementarios="
				+ enlacesComplementarios + ", imagen=" + imagen + "]";
	}


	
		
	}
	

