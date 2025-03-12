package br.com.wisefinances.smartbrains.service;

import br.com.wisefinances.smartbrains.domain.abstracts.AbstractSpecificService;
import br.com.wisefinances.smartbrains.domain.messages.MessagesResponseDTO;
import br.com.wisefinances.smartbrains.enums.SituacaoCadastro;
import br.com.wisefinances.smartbrains.model.dto.EstadoCivilDTO;
import br.com.wisefinances.smartbrains.model.dto.GeneroDTO;
import br.com.wisefinances.smartbrains.model.dto.SituacaoCadastroDTO;
import br.com.wisefinances.smartbrains.model.dto.UsuarioDTO;
import br.com.wisefinances.smartbrains.model.entity.Usuarios;
import br.com.wisefinances.smartbrains.repository.EstadoCivilRepository;
import br.com.wisefinances.smartbrains.repository.GeneroRepository;
import br.com.wisefinances.smartbrains.repository.SituacaoCadastroRepository;
import br.com.wisefinances.smartbrains.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UsuarioService extends AbstractSpecificService<UsuarioDTO, UsuarioDTO, MessagesResponseDTO> {

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
    public MessagesResponseDTO save(UsuarioDTO pUsuarioDTO) {
        var usuarioDTO = preencherCampos(pUsuarioDTO);
        var usuario = modelMapper.map(pUsuarioDTO, Usuarios.class);
        usuarioRepository.save(usuario);
        return MessagesResponseDTO.createSucessResponseDTO;
    }

    @Override
    public UsuarioDTO update(Integer pId, UsuarioDTO pUsuarioDTO) {
        return null;
    }

    @Override
    public MessagesResponseDTO delete(Integer pId) {
            var usuario = usuarioRepository.getReferenceById(pId);
            var situacaoExcluido = situacaoCadastroRepository.getReferenceById(SituacaoCadastro.EXCLUIDO.getId());

            usuario.setSituacaoCadastro(situacaoExcluido);

            new UsuarioDTO(usuarioRepository.save(usuario));
            return MessagesResponseDTO.deleteSucessResponseDTO;
    }

    private UsuarioDTO preencherCampos(UsuarioDTO pUsuarioDTO) {
        var usuarioComGenero = preencherGenero(pUsuarioDTO);
        var usuarioComEstadoCivil = preencherEstadoCivil(usuarioComGenero);
        var usuarioComSituacaoCadastro = preencherSituacaoCadastro(usuarioComEstadoCivil);
        return preencherDataCadastro(usuarioComSituacaoCadastro);
    }

    private UsuarioDTO preencherGenero(UsuarioDTO usuarioDTO) {
        var generoEntidade = generoRepository.getReferenceById(usuarioDTO.getGenero().getId());
        var generoDTO = modelMapper.map(generoEntidade, GeneroDTO.class);
        usuarioDTO.setGenero(generoDTO);
        return usuarioDTO;
    }

    private UsuarioDTO preencherEstadoCivil(UsuarioDTO usuarioDTO) {
        var estadoCivilEntidade = estadoCivilRepository.getReferenceById(usuarioDTO.getEstadoCivil().getId());
        var estadoCivilDTO = modelMapper.map(estadoCivilEntidade, EstadoCivilDTO.class);
        usuarioDTO.setEstadoCivil(estadoCivilDTO);
        return usuarioDTO;
    }

    private UsuarioDTO preencherSituacaoCadastro(UsuarioDTO usuarioDTO) {
        var situacaoCadastroAtivo = situacaoCadastroRepository.getReferenceById(SituacaoCadastro.ATIVO.getId());
        var situacaoCadastroAtivoDTO = modelMapper.map(situacaoCadastroAtivo, SituacaoCadastroDTO.class);
        usuarioDTO.setSituacaoCadastro(situacaoCadastroAtivoDTO);
        return usuarioDTO;
    }

    private UsuarioDTO preencherDataCadastro(UsuarioDTO usuarioDTO) {
        usuarioDTO.setDataCadastro(LocalDate.now());
        return usuarioDTO;
    }
}