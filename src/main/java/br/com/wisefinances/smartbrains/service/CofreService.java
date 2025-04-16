package br.com.wisefinances.smartbrains.service;

import br.com.wisefinances.smartbrains.domain.abstracts.AbstractSpecificService;
import br.com.wisefinances.smartbrains.domain.messages.MessagesResponseDTO;
import br.com.wisefinances.smartbrains.model.dto.cofre.CofreDTO;
import br.com.wisefinances.smartbrains.model.dto.cofre.CreateCofreDTO;
import br.com.wisefinances.smartbrains.model.entity.cofre.CreateCofre;
import br.com.wisefinances.smartbrains.repository.cofre.CofreRepository;
import br.com.wisefinances.smartbrains.repository.cofre.CreateCofreRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CofreService extends AbstractSpecificService<CofreDTO, CreateCofreDTO, MessagesResponseDTO> {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    CofreRepository cofreRepository;

    @Autowired
    CreateCofreRepository createCofreRepository;

    @Override
    public Page<CofreDTO> findAll(Pageable pPageable) {
        return cofreRepository.findAll(pPageable).map(CofreDTO::new);
    }

    @Override
    public CofreDTO findById(Integer pId) {
        return new CofreDTO(cofreRepository.getReferenceById(pId));
    }

    @Override
    public MessagesResponseDTO save(CreateCofreDTO createCofreDTO) {
        var cofre = modelMapper.map(createCofreDTO, CreateCofre.class);
        createCofreRepository.save(cofre);
        return MessagesResponseDTO.createSucessResponseDTO;
    }

    @Override
    public CreateCofreDTO update(Integer pId, CreateCofreDTO createCofreDTO) {
        var cofre = createCofreRepository.getReferenceById(pId);
        BeanUtils.copyProperties(createCofreDTO, cofre, "id");
        return new CreateCofreDTO(createCofreRepository.save(cofre));
    }

    @Override
    public MessagesResponseDTO delete(Integer pId) {
        createCofreRepository.deleteById(pId);
        return MessagesResponseDTO.deleteSucessResponseDTO;
    }
}