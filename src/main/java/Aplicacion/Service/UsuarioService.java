package Aplicacion.Service;

import Aplicacion.DAO.UsuarioDAO;
import Dominio.Modelo.Usuario;
import Dominio.repository.ICRUD;

import java.util.List;
import java.util.Optional;

public class UsuarioService implements ICRUD<Usuario, Integer> {

    private final UsuarioDAO usuarioDAO;

    public UsuarioService() {
        this.usuarioDAO = new UsuarioDAO();
    }

    @Override
    public int save(Usuario beans) {
        return usuarioDAO.save(beans);
    }

    @Override
    public int update(Usuario beans) {
        if (beans == null || beans.getIdUsuario() <= 0) return -1;
        return usuarioDAO.update(beans);
    }

    @Override
    public int delete(Integer id) {
        if (id == null || id < 0) return -1;
        return usuarioDAO.delete(id);
    }

    @Override
    public Optional<Usuario> findById(Integer id) {
        if (id == null || id < 0) return Optional.empty();
        return usuarioDAO.findById(id);
    }

    @Override
    public List<Usuario> findAll() {
        return usuarioDAO.findAll();
    }

    @Override
    public int saveAndFindId(Usuario beans) {
        return usuarioDAO.saveAndFindId(beans);
    }

    public Usuario login(String correo, String password) {
        if (correo == null || password == null) return null;
        return usuarioDAO.login(correo, password);
    }
}
