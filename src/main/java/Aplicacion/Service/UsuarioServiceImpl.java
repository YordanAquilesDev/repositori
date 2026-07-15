package Aplicacion.Service;

import Aplicacion.DAO.UsuarioRepository;
import Dominio.Modelo.Usuario;
import Dominio.repository.ICRUD;

import java.util.List;
import java.util.Optional;

public class UsuarioServiceImpl implements ICRUD<Usuario, Integer> {

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl() {
        this.usuarioRepository = new UsuarioRepository();
    }

    @Override
    public int save(Usuario beans) {
        return usuarioRepository.save(beans);
    }

    @Override
    public int update(Usuario beans) {
        if (beans == null || beans.getIdUsuario() <= 0) return -1;
        return usuarioRepository.update(beans);
    }

    @Override
    public int delete(Integer id) {
        if (id == null || id < 0) return -1;
        return usuarioRepository.delete(id);
    }

    @Override
    public Optional<Usuario> findById(Integer id) {
        if (id == null || id < 0) return Optional.empty();
        return usuarioRepository.findById(id);
    }

    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public int saveAndFindId(Usuario beans) {

        return usuarioRepository.saveAndFindId(beans);
    }

    public Usuario login(String username, String password) {
        if (username == null || password == null) return null;
        return usuarioRepository.login(username, password);
    }
}
