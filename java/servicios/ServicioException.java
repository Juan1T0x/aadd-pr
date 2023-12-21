package servicios;


//Los servicios deberían lanzar sus propias excepciones.

public class ServicioException extends Exception {

	public ServicioException(String message) {
        super(message);
    }

    public ServicioException(String message, Throwable cause) {
        super(message, cause);
    }
}