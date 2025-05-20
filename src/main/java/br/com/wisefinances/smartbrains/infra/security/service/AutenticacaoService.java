package br.com.wisefinances.smartbrains.infra.security.service;

import br.com.wisefinances.smartbrains.infra.security.model.dto.AutenticacaoDTO;
import br.com.wisefinances.smartbrains.infra.security.model.entity.Autenticacao;
import br.com.wisefinances.smartbrains.infra.security.model.response.AuthenticationResponseDTO;
import br.com.wisefinances.smartbrains.infra.security.model.response.IsEmailValidResponseDTO;
import br.com.wisefinances.smartbrains.infra.security.model.response.IsTokenValidResponseDTO;
import br.com.wisefinances.smartbrains.infra.security.model.response.TokenResponseDTO;
import br.com.wisefinances.smartbrains.infra.security.model.response.check.CheckEmailDTO;
import br.com.wisefinances.smartbrains.infra.security.model.response.check.CheckTokenDTO;
import br.com.wisefinances.smartbrains.infra.security.repository.AutenticacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AutenticacaoRepository autenticacaoRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return autenticacaoRepository.findByEmail(email);
    }

    public AuthenticationResponseDTO saveAuthentication(AutenticacaoDTO autenticacaoDTO) {
        validateAuthentication(autenticacaoDTO);
        autenticacaoRepository.save(autenticacaoDTO.toEntity());
        return AuthenticationResponseDTO.authenticationResponseDTO;
    }

    public IsTokenValidResponseDTO checkToken(CheckTokenDTO pCheckTokenDTO) {
        var isValid = tokenService.isTokenValid(pCheckTokenDTO.getAccess_token());
        return new IsTokenValidResponseDTO(isValid);
    }

    public IsEmailValidResponseDTO checkEmail(CheckEmailDTO pCheckEmailDTO) {
        var emailAlreadyExists = autenticacaoRepository.existsByEmail(pCheckEmailDTO.getEmail());
        return new IsEmailValidResponseDTO(emailAlreadyExists);
    }

    public TokenResponseDTO generateTokenForAuthentication(Authentication authentication) {
        String token = convertAuthenticationToToken(authentication);
        return new TokenResponseDTO(token);
    }

    public void updateAuthenticationEmail(String pNewEmail, String pOldEmail) {
        autenticacaoRepository.updateEmail(pNewEmail, pOldEmail);
    }

    public void updateAuthenticationPassword(AutenticacaoDTO pAutenticacaoDTO) {
        var encryptedPassword = passwordEncoder.encode(pAutenticacaoDTO.getPassword());
        autenticacaoRepository.updatePassword(encryptedPassword, pAutenticacaoDTO.getEmail());
    }

    private void validateAuthentication(AutenticacaoDTO autenticacaoDTO) {
        checkEmail(autenticacaoDTO.getEmail());
        encryptPassword(autenticacaoDTO);
    }

    private void encryptPassword(AutenticacaoDTO autenticacaoDTO) {
        autenticacaoDTO.setPassword(passwordEncoder.encode(autenticacaoDTO.getPassword()));
    }

    private void checkEmail(String email) {
        var findUser = autenticacaoRepository.findByEmail(email);

        if (findUser != null) {
            throw new RuntimeException("Esse email já está cadastrado!");
        }
    }

    private String convertAuthenticationToToken(Authentication authentication) {
        var authenticationToEntity = (Autenticacao) authentication.getPrincipal();
        var entityToDTO = new AutenticacaoDTO(authenticationToEntity);
        return tokenService.generateToken(entityToDTO);
    }
}