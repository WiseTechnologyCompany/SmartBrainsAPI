package br.com.smartbrains.service;

import br.com.smartbrains.domain.abstracts.AbstractSpecificService;
import br.com.smartbrains.domain.messages.MessagesResponseDTO;
import br.com.smartbrains.enums.SituacaoCadastro;
import br.com.smartbrains.model.modify.dto.ModifyUsuarioDTO;
import br.com.smartbrains.model.modify.entity.ModifyUsuario;
import br.com.smartbrains.model.dto.UsuarioDTO;
import br.com.smartbrains.repository.ModifyUsuarioRepository;
import br.com.smartbrains.repository.SituacaoCadastroRepository;
import br.com.smartbrains.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService extends AbstractSpecificService<UsuarioDTO, ModifyUsuarioDTO, MessagesResponseDTO> {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    ModifyUsuarioRepository modifyUsuarioRepository;

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
    public MessagesResponseDTO save(ModifyUsuarioDTO pModifyUsuarioDTO) {
        var usuario = modelMapper.map(pModifyUsuarioDTO, ModifyUsuario.class);
        modifyUsuarioRepository.save(usuario);
        return MessagesResponseDTO.createSucessResponseDTO;
    }

    @Override
    public ModifyUsuarioDTO update(Integer pId, ModifyUsuarioDTO pModifyUsuarioDTO) {
        var usuario = modifyUsuarioRepository.getReferenceById(pId);
        BeanUtils.copyProperties(pModifyUsuarioDTO, usuario, "id");
        return new ModifyUsuarioDTO(modifyUsuarioRepository.save(usuario));
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