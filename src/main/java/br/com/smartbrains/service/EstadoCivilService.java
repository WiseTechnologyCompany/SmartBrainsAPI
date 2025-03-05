package br.com.smartbrains.service;

import br.com.smartbrains.domain.abstracts.AbstractService;
import br.com.smartbrains.domain.messages.DeleteSucessResponseDTO;
import br.com.smartbrains.model.dto.EstadoCivilDTO;
import br.com.smartbrains.model.entity.EstadoCivil;
import br.com.smartbrains.repository.EstadoCivilRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EstadoCivilService extends AbstractService<EstadoCivilDTO, DeleteSucessResponseDTO> {

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
    public EstadoCivilDTO save(EstadoCivilDTO pEstadoCivilDTO) {
        var estadoCivilToSave = modelMapper.map(pEstadoCivilDTO, EstadoCivil.class);
        return new EstadoCivilDTO(estadoCivilRepository.save(estadoCivilToSave));
    }

    @Override
    public EstadoCivilDTO update(Integer pId, EstadoCivilDTO pEstadoCivilDTO) {
        var findEstadoCivil = estadoCivilRepository.getReferenceById(pId);
        BeanUtils.copyProperties(pEstadoCivilDTO, findEstadoCivil, "id");
        return new EstadoCivilDTO(estadoCivilRepository.save(findEstadoCivil));
    }

    @Override
    public DeleteSucessResponseDTO delete(Integer pId) {
        var findEstadoCivil = estadoCivilRepository.getReferenceById(pId);
        estadoCivilRepository.delete(findEstadoCivil);
        return DeleteSucessResponseDTO.deleteSucessResponseDTO;
    }
}