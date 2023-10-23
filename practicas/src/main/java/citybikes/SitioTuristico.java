package citybikes;


import java.util.List;

public class SitioTuristico {
    private String nombre;
    private String resumen;
    private List<String> categorias;
    private List<String> enlacesExternos;
    private String imagenWikimedia;
    private String urlWikipedia;
    
    
    public SitioTuristico() {
    }

    public SitioTuristico(String nombre, String resumen, List<String> categorias, List<String> enlacesExternos, String imagenWikimedia, String urlWikipedia) {
        this.nombre = nombre;
        this.resumen = resumen;
        this.categorias = categorias;
        this.enlacesExternos = enlacesExternos;
        this.imagenWikimedia = imagenWikimedia;
        this.urlWikipedia = urlWikipedia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public List<String> getEnlacesExternos() {
        return enlacesExternos;
    }

    public void setEnlacesExternos(List<String> enlacesExternos) {
        this.enlacesExternos = enlacesExternos;
    }

    public String getImagenWikimedia() {
        return imagenWikimedia;
    }

    public void setImagenWikimedia(String imagenWikimedia) {
        this.imagenWikimedia = imagenWikimedia;
    }

	public String getUrlWikipedia() {
		return urlWikipedia;
	}

	public void setUrlWikipedia(String urlWikipedia) {
		this.urlWikipedia = urlWikipedia;
	}

	@Override
	public String toString() {
		return "SitioTuristico [nombre=" + nombre + ", resumen=" + resumen + ", categorias=" + categorias
				+ ", enlacesExternos=" + enlacesExternos + ", imagenWikimedia=" + imagenWikimedia + ", urlWikipedia="
				+ urlWikipedia + "]";
	}

	
	
	
}
