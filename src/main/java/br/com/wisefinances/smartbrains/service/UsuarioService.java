package br.com.wisefinances.smartbrains.service;

import br.com.wisefinances.smartbrains.domain.abstracts.AbstractSpecificService;
import br.com.wisefinances.smartbrains.domain.messages.MessagesResponseDTO;
import br.com.wisefinances.smartbrains.enums.SituacaoCadastro;
import br.com.wisefinances.smartbrains.model.dto.usuario.CreateUsuarioDTO;
import br.com.wisefinances.smartbrains.model.entity.usuario.CreateUsuario;
import br.com.wisefinances.smartbrains.model.dto.usuario.UsuarioDTO;
import br.com.wisefinances.smartbrains.repository.usuario.CreateUsuarioRepository;
import br.com.wisefinances.smartbrains.repository.situacaocadastro.SituacaoCadastroRepository;
import br.com.wisefinances.smartbrains.repository.usuario.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService extends AbstractSpecificService<UsuarioDTO, CreateUsuarioDTO, MessagesResponseDTO> {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    CreateUsuarioRepository createUsuarioRepository;

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

    public UsuarioDTO findUsuarioInfoByEmail(String pEmail) {
        var userInfo = usuarioRepository.findByEmail(pEmail);
        return new UsuarioDTO(userInfo.getId(), userInfo.getNome(), userInfo.getProfissao(), userInfo.getEmpresa());
    }

    @Override
    public MessagesResponseDTO save(CreateUsuarioDTO pCreateUsuarioDTO) {
        var usuario = modelMapper.map(pCreateUsuarioDTO, CreateUsuario.class);
        createUsuarioRepository.save(usuario);
        return MessagesResponseDTO.createSucessResponseDTO;
    }

    @Override
    public CreateUsuarioDTO update(Integer pId, CreateUsuarioDTO pCreateUsuarioDTO) {
        var usuario = createUsuarioRepository.getReferenceById(pId);
        BeanUtils.copyProperties(pCreateUsuarioDTO, usuario, "id");
        return new CreateUsuarioDTO(createUsuarioRepository.save(usuario));
    }

    @Override
    public MessagesResponseDTO delete(Integer pId) {
            var usuario = usuarioRepository.getReferenceById(pId);
            var situacaoExcluido = situacaoCadastroRepository.getReferenceById(SituacaoCadastro.EXCLUIDO.getId());

            usuario.setSituacaoCadastro(situacaoExcluido);

            new UsuarioDTO(usuarioRepository.save(usuario));
            return MessagesResponseDTO.deleteSucessResponseDTO;
    }
}