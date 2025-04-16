package br.com.wisefinances.smartbrains.service;

import br.com.wisefinances.smartbrains.domain.abstracts.AbstractService;
import br.com.wisefinances.smartbrains.domain.messages.MessagesResponseDTO;
import br.com.wisefinances.smartbrains.model.dto.genero.GeneroDTO;
import br.com.wisefinances.smartbrains.model.entity.genero.Genero;
import br.com.wisefinances.smartbrains.repository.genero.GeneroRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneroService extends AbstractService<GeneroDTO, MessagesResponseDTO> {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    GeneroRepository generoRepository;

    @Override
    public List<GeneroDTO> findAll() {
        return generoRepository.findAll().stream().map(GeneroDTO::new).toList();
    }

    @Override
    public GeneroDTO findById(Integer pId) {
        var genero = generoRepository.getReferenceById(pId);
        return new GeneroDTO(genero);
    }

    @Override
    public MessagesResponseDTO save(GeneroDTO pGeneroDTO) {
        var genero = modelMapper.map(pGeneroDTO, Genero.class);
        new GeneroDTO(generoRepository.save(genero));
        return MessagesResponseDTO.createSucessResponseDTO;
    }

    @Override
    public GeneroDTO update(Integer pId, GeneroDTO pGeneroDTO) {
        var genero = generoRepository.getReferenceById(pId);
        BeanUtils.copyProperties(pGeneroDTO, genero, "id");
        return new GeneroDTO(generoRepository.save(genero));
    }

    @Override
    public MessagesResponseDTO delete(Integer pId) {
        var genero = generoRepository.getReferenceById(pId);
        generoRepository.delete(genero);
        return MessagesResponseDTO.deleteSucessResponseDTO;
    }
}