package br.com.smartbrains.service;

import br.com.smartbrains.domain.abstracts.AbstractSpecificService;
import br.com.smartbrains.domain.messages.DeleteResponseDTO;
import br.com.smartbrains.enums.Genero;
import br.com.smartbrains.model.dto.GeneroDTO;
import br.com.smartbrains.model.dto.UsuarioDTO;
import br.com.smartbrains.model.entity.Usuarios;
import br.com.smartbrains.repository.GeneroRepository;
import br.com.smartbrains.repository.SituacaoCadastroRepository;
import br.com.smartbrains.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class UsuarioService extends AbstractSpecificService<UsuarioDTO, DeleteResponseDTO> {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    GeneroRepository generoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    SituacaoCadastroRepository situacaoCadastroRepository;

    @Override
    public Page<UsuarioDTO> findAll(Pageable pageable) {
        return usuarioRepository.findAll(pageable).map(UsuarioDTO::new);
    }

    @Override
    public UsuarioDTO findById(Integer pId) {
        return new UsuarioDTO(usuarioRepository.getReferenceById(pId));
    }

    @Override
    public UsuarioDTO save(UsuarioDTO pUsuarioDTO) {
        var usuarioDTO = verificarGeneroUsuario(pUsuarioDTO);
        var usuario = modelMapper.map(usuarioDTO, Usuarios.class);
        return new UsuarioDTO(usuarioRepository.save(usuario));
    }

    @Override
    public UsuarioDTO update(Integer pId, UsuarioDTO pUsuarioDTO) {
        return null;
    }

    @Override
    public DeleteResponseDTO delete(Integer pId) {
        var usuario = usuarioRepository.getReferenceById(pId);

        if (usuario.getSituacaoCadastro().getId() == 1) {
            var situacaoExcluido = situacaoCadastroRepository.getReferenceById(0);

            usuario.setSituacaoCadastro(situacaoExcluido);
            new UsuarioDTO(usuarioRepository.save(usuario));
            return DeleteResponseDTO.deleteResponseDTO;
        }
        else {
            throw new RuntimeException();
        }
    }

    private UsuarioDTO verificarGeneroUsuario(UsuarioDTO pUsuarioDTO) {
        Map<Integer, Genero> generoMap = Map.of(
                1, Genero.MASCULINO,
                2, Genero.FEMININO,
                3, Genero.OUTROS
        );

        var generoSelecionado = generoMap.get(pUsuarioDTO.getGenero().getId());
        var genero = generoRepository.getReferenceById(generoSelecionado.getId());

        pUsuarioDTO.setGenero(modelMapper.map(genero, GeneroDTO.class));
        return pUsuarioDTO;
    }
}