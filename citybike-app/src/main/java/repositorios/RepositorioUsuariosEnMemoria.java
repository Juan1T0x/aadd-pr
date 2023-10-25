package repositorios;

import java.util.ArrayList;
import java.util.List;

import modelo.Usuario;

public class RepositorioUsuariosEnMemoria implements RepositorioUsuario {
	
	private List<Usuario> usuarios = new ArrayList<>();

	@Override
	public Usuario saveUsuario(Usuario usuario) {
		usuarios.add(usuario);
		return usuario;
	}

	@Override
	public Usuario findUsuarioByEmail(String email) {
		for(Usuario usuario : usuarios) {
			if(usuario.getEmail().equals(email)) {
				return usuario;
			}
		}
		return null;
	}

	@Override
	public List<Usuario> getAllUsuarios() {
		return usuarios;
	}

}
