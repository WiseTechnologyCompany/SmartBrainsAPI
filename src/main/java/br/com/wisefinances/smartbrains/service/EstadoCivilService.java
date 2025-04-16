package br.com.wisefinances.smartbrains.service;

import br.com.wisefinances.smartbrains.domain.abstracts.AbstractService;
import br.com.wisefinances.smartbrains.domain.messages.MessagesResponseDTO;
import br.com.wisefinances.smartbrains.model.dto.estadocivil.EstadoCivilDTO;
import br.com.wisefinances.smartbrains.model.entity.estadocivil.EstadoCivil;
import br.com.wisefinances.smartbrains.repository.estadocivil.EstadoCivilRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoCivilService extends AbstractService<EstadoCivilDTO, MessagesResponseDTO> {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    EstadoCivilRepository estadoCivilRepository;

    @Override
    public List<EstadoCivilDTO> findAll() {
        return estadoCivilRepository.findAll().stream().map(EstadoCivilDTO::new).toList();
    }

    @Override
    public EstadoCivilDTO findById(Integer pId) {
        var findEstadoCivil = estadoCivilRepository.getReferenceById(pId);
        return new EstadoCivilDTO(findEstadoCivil);
    }

    @Override
    public MessagesResponseDTO save(EstadoCivilDTO pEstadoCivilDTO) {
        var estadoCivil = modelMapper.map(pEstadoCivilDTO, EstadoCivil.class);
        new EstadoCivilDTO(estadoCivilRepository.save(estadoCivil));
        return MessagesResponseDTO.createSucessResponseDTO;
    }

    @Override
    public EstadoCivilDTO update(Integer pId, EstadoCivilDTO pEstadoCivilDTO) {
        var estadoCivil = estadoCivilRepository.getReferenceById(pId);
        BeanUtils.copyProperties(pEstadoCivilDTO, estadoCivil, "id");
        return new EstadoCivilDTO(estadoCivilRepository.save(estadoCivil));
    }

    @Override
    public MessagesResponseDTO delete(Integer pId) {
        var estadoCivil = estadoCivilRepository.getReferenceById(pId);
        estadoCivilRepository.delete(estadoCivil);
        return MessagesResponseDTO.deleteSucessResponseDTO;
    }
}