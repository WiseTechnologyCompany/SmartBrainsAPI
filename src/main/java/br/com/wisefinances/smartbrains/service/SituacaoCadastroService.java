package br.com.wisefinances.smartbrains.service;

import br.com.wisefinances.smartbrains.domain.abstracts.AbstractService;
import br.com.wisefinances.smartbrains.domain.messages.MessagesResponseDTO;
import br.com.wisefinances.smartbrains.model.dto.situacaocadastro.SituacaoCadastroDTO;
import br.com.wisefinances.smartbrains.model.entity.situacaocadastro.SituacaoCadastro;
import br.com.wisefinances.smartbrains.repository.situacaocadastro.SituacaoCadastroRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SituacaoCadastroService extends AbstractService<SituacaoCadastroDTO, MessagesResponseDTO> {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    SituacaoCadastroRepository situacaoCadastroRepository;

    @Override
    public List<SituacaoCadastroDTO> findAll() {
        return situacaoCadastroRepository.findAll().stream().map(SituacaoCadastroDTO::new).toList();
    }

    @Override
    public SituacaoCadastroDTO findById(Integer pId) {
        var situacaoCadastro = situacaoCadastroRepository.getReferenceById(pId);
        return new SituacaoCadastroDTO(situacaoCadastro);
    }

    @Override
    public MessagesResponseDTO save(SituacaoCadastroDTO pSituacaoCadastroDTO) {
        var situacaoCadastro = modelMapper.map(pSituacaoCadastroDTO, SituacaoCadastro.class);
        new SituacaoCadastroDTO(situacaoCadastroRepository.save(situacaoCadastro));
        return MessagesResponseDTO.createSucessResponseDTO;
    }

    @Override
    public SituacaoCadastroDTO update(Integer pId, SituacaoCadastroDTO pSituacaoCadastroDTO) {
        var situacaoCadastro = situacaoCadastroRepository.getReferenceById(pId);
        BeanUtils.copyProperties(pSituacaoCadastroDTO, situacaoCadastro, "id");
        return new SituacaoCadastroDTO(situacaoCadastroRepository.save(situacaoCadastro));
    }

    @Override
    public MessagesResponseDTO delete(Integer pId) {
        var situacaoCadastro = situacaoCadastroRepository.getReferenceById(pId);
        situacaoCadastroRepository.delete(situacaoCadastro);
        return MessagesResponseDTO.deleteSucessResponseDTO;
    }
}