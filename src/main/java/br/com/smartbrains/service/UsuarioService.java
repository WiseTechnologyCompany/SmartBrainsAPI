package br.com.smartbrains.service;

import br.com.smartbrains.domain.abstracts.AbstractSpecificService;
import br.com.smartbrains.domain.messages.DeleteResponseDTO;
import br.com.smartbrains.enums.EstadoCivil;
import br.com.smartbrains.enums.Genero;
import br.com.smartbrains.enums.SituacaoCadastro;
import br.com.smartbrains.model.dto.EstadoCivilDTO;
import br.com.smartbrains.model.dto.GeneroDTO;
import br.com.smartbrains.model.dto.UsuarioDTO;
import br.com.smartbrains.model.dto.create.CreateUsuarioDTO;
import br.com.smartbrains.model.entity.Usuarios;
import br.com.smartbrains.repository.EstadoCivilRepository;
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
public class UsuarioService extends AbstractSpecificService<UsuarioDTO, CreateUsuarioDTO, DeleteResponseDTO> {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    GeneroRepository generoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    EstadoCivilRepository estadoCivilRepository;

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
    public UsuarioDTO save(CreateUsuarioDTO pCreateUsuarioDTO) {
       var usuarioDTO = modelMapper.map(pCreateUsuarioDTO, UsuarioDTO.class);
       var generoDTO = verificarGeneroUsuario(usuarioDTO);
       var estadoCivilDTO = verificarEstadoCivilUsuario(generoDTO);
       var usuario = modelMapper.map(estadoCivilDTO, Usuarios.class);
       return new UsuarioDTO(usuarioRepository.save(usuario));
    }

    @Override
    public UsuarioDTO update(Integer pId, UsuarioDTO pUsuarioDTO) {
        return null;
    }

    @Override
    public DeleteResponseDTO delete(Integer pId) {
        var usuario = usuarioRepository.getReferenceById(pId);

        if (usuario.getSituacaoCadastro().getId().equals(SituacaoCadastro.ATIVO.getId())) {
            var situacaoExcluido = situacaoCadastroRepository.getReferenceById(SituacaoCadastro.EXCLUIDO.getId());
            usuario.setSituacaoCadastro(situacaoExcluido);

            new UsuarioDTO(usuarioRepository.save(usuario));
            return DeleteResponseDTO.deleteResponseDTO;
        }
        else {
            throw new RuntimeException();
        }
    }

    private UsuarioDTO verificarGeneroUsuario(UsuarioDTO pUsuarioDTO) {
        var genero = generoRepository.getReferenceById(pUsuarioDTO.getGenero().getId());
        var generoDTO = modelMapper.map(genero, GeneroDTO.class);
        pUsuarioDTO.setGenero(generoDTO);
        return pUsuarioDTO;
    }

    private UsuarioDTO verificarEstadoCivilUsuario(UsuarioDTO pUsuarioDTO) {
        var estadoCivil = estadoCivilRepository.getReferenceById(pUsuarioDTO.getEstadoCivil().getId());
        var estadoCivilDTO = modelMapper.map(estadoCivil, EstadoCivilDTO.class);
        pUsuarioDTO.setEstadoCivil(estadoCivilDTO);
        return pUsuarioDTO;
    }
}