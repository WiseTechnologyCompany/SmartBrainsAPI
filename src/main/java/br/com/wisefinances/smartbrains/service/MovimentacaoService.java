package br.com.wisefinances.smartbrains.service;

import br.com.wisefinances.smartbrains.domain.abstracts.AbstractSpecificService;
import br.com.wisefinances.smartbrains.domain.messages.MessagesResponseDTO;
import br.com.wisefinances.smartbrains.model.dto.movimentacao.*;
import br.com.wisefinances.smartbrains.model.entity.movimentacao.CreateMovimentacao;
import br.com.wisefinances.smartbrains.repository.movimentacao.CreateMovimentacaoRepository;
import br.com.wisefinances.smartbrains.repository.movimentacao.MovimentacaoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class MovimentacaoService extends AbstractSpecificService<MovimentacaoDTO, CreateMovimentacaoDTO, MessagesResponseDTO> {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    MovimentacaoRepository movimentacaoRepository;

    @Autowired
    CreateMovimentacaoRepository createMovimentacaoRepository;

    @Override
    public Page<MovimentacaoDTO> findAll(Pageable pPageable) {
        return movimentacaoRepository.findAll(pPageable).map(MovimentacaoDTO::new);
    }

    @Override
    public MovimentacaoDTO findById(Integer pId) {
        return new MovimentacaoDTO(movimentacaoRepository.getReferenceById(pId));
    }

    public CreateMovimentacaoDTO findTransactionById(Integer pId) {
        return new CreateMovimentacaoDTO(createMovimentacaoRepository.getReferenceById(pId));
    }

    public List<UserTransactionsResponseDTO> findAllUserTransactions(String pEmail) {
        int userId = usuarioService.findUsuarioInfoByEmail(pEmail).getId();
        return movimentacaoRepository.findAllUserTransactionsByUserId(userId).stream().map(UserTransactionsResponseDTO::new).toList();
    }

    public TotalTransactionsResponseDTO sumUserTotalTransactions(String pEmail) {
        int userId = usuarioService.findUsuarioInfoByEmail(pEmail).getId();

        BigDecimal totalEntrada = movimentacaoRepository.sumTotalEntrada(userId);
        BigDecimal totalGastosFixos = movimentacaoRepository.sumTotalGastosFixos(userId);
        BigDecimal totalDespesas = movimentacaoRepository.sumTotalDespesas(userId);

        return new TotalTransactionsResponseDTO(totalEntrada, totalGastosFixos, totalDespesas);
    }

    public TotalEntradaSaidaPorAnoDTO getTotalEntradaSaidaPorAno(String pEmail) {
        int userId = usuarioService.findUsuarioInfoByEmail(pEmail).getId();

        BigDecimal totalEntradaPorAno = movimentacaoRepository.findTotalEntradaPorAno(userId);
        BigDecimal totalSaidaPorAno = movimentacaoRepository.findTotalSaidaPorAno(userId);

        return new TotalEntradaSaidaPorAnoDTO(totalEntradaPorAno, totalSaidaPorAno);
    }

    public List<TotalEntradaPorMesDTO> getTotalEntradaPorMes(String pEmail) {
        int userId = usuarioService.findUsuarioInfoByEmail(pEmail).getId();
        var totalEntradaPorMes = movimentacaoRepository.findTotalEntradaPorMes(userId);

        return totalEntradaPorMes.stream()
                .map(object -> new TotalEntradaPorMesDTO(
                        (String) object[0],
                        (BigDecimal) object[1]
                ))
                .toList();
    }

    public List<TotalCategoriaDTO> getTotalCategorias(String email) {
        int userId = usuarioService.findUsuarioInfoByEmail(email).getId();
        var totalCategorias = movimentacaoRepository.findTotalCategorias(userId);

        return totalCategorias.stream()
                .map(object -> new TotalCategoriaDTO(
                        (Integer) object[0],
                        (String) object[1],
                        (BigDecimal) object[2]
                ))
                .toList();
    }

    public List<TotalSaidaPorMesDTO> getTotalSaidaPorMes(String pEmail) {
        int userId = usuarioService.findUsuarioInfoByEmail(pEmail).getId();
        var totalSaidaPorMes = movimentacaoRepository.findTotalSaidaPorMes(userId);

        return totalSaidaPorMes.stream()
                .map(object -> new TotalSaidaPorMesDTO(
                        (String) object[0],
                        (BigDecimal) object[1]
                ))
                .toList();
    }

    @Override
    public MessagesResponseDTO save(CreateMovimentacaoDTO pCreateMovimentacaoDTO) {
        var movimentacao = modelMapper.map(pCreateMovimentacaoDTO, CreateMovimentacao.class);
        createMovimentacaoRepository.save(movimentacao);
        return MessagesResponseDTO.createSucessResponseDTO;
    }

    @Override
    public CreateMovimentacaoDTO update(Integer pId, CreateMovimentacaoDTO pCreateMovimentacaoDTO) {
        var movimentacao = createMovimentacaoRepository.getReferenceById(pId);
        BeanUtils.copyProperties(pCreateMovimentacaoDTO, movimentacao, "id");
        return new CreateMovimentacaoDTO(createMovimentacaoRepository.save(movimentacao));
    }

    @Override
    public MessagesResponseDTO delete(Integer pId) {
        var movimentacao = movimentacaoRepository.getReferenceById(pId);
        movimentacaoRepository.delete(movimentacao);
        return MessagesResponseDTO.deleteSucessResponseDTO;
    }
}