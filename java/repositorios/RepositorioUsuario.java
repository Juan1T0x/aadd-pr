package repositorios;

import java.util.List;

import modelo.Usuario;

public interface RepositorioUsuario {
	Usuario saveUsuario(Usuario usuario);
	Usuario findUsuarioByEmail(String email);
	List<Usuario> getAllUsuarios();
}
