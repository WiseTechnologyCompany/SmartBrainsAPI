package br.com.smartbrains.service;

import br.com.smartbrains.domain.abstracts.AbstractService;
import br.com.smartbrains.domain.messages.DeleteResponseDTO;
import br.com.smartbrains.model.dto.SituacaoCadastroDTO;
import br.com.smartbrains.model.entity.SituacaoCadastro;
import br.com.smartbrains.repository.SituacaoCadastroRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SituacaoCadastroService extends AbstractService<SituacaoCadastroDTO, DeleteResponseDTO> {

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
    public SituacaoCadastroDTO save(SituacaoCadastroDTO pSituacaoCadastroDTO) {
        var situacaoCadastro = modelMapper.map(pSituacaoCadastroDTO, SituacaoCadastro.class);
        return new SituacaoCadastroDTO(situacaoCadastroRepository.save(situacaoCadastro));
    }

    @Override
    public SituacaoCadastroDTO update(Integer pId, SituacaoCadastroDTO pSituacaoCadastroDTO) {
        var situacaoCadastro = situacaoCadastroRepository.getReferenceById(pId);
        BeanUtils.copyProperties(pSituacaoCadastroDTO, situacaoCadastro, "id");
        return new SituacaoCadastroDTO(situacaoCadastroRepository.save(situacaoCadastro));
    }

    @Override
    public DeleteResponseDTO delete(Integer pId) {
        var situacaoCadastro = situacaoCadastroRepository.getReferenceById(pId);
        situacaoCadastroRepository.delete(situacaoCadastro);
        return DeleteResponseDTO.deleteResponseDTO;
    }
}