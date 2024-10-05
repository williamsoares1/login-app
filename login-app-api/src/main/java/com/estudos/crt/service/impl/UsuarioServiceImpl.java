package com.estudos.crt.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.estudos.crt.dtos.request.UsuarioLoginRequestDTO;
import com.estudos.crt.dtos.request.UsuarioRegisterRequestDTO;
import com.estudos.crt.dtos.response.UsuarioResponseDTO;
import com.estudos.crt.entities.Usuario;
import com.estudos.crt.infrastructure.security.dtos.TokenDTO;
import com.estudos.crt.infrastructure.security.service.TokenService;
import com.estudos.crt.infrastructure.security.service.impl.UserDetailsImpl;
import com.estudos.crt.repositories.UsuarioRepository;
import com.estudos.crt.service.SessionService;
import com.estudos.crt.service.UsuarioService;
import com.estudos.crt.util.mapper.MapperS;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.Cookie;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private SessionService sessionService;

    @Value("${auth.jwt.token.expiration}")
    private Integer tokenExpiration;

    @Value("${auth.jwt.refresh-token.expiration}")
    private Integer RefreshTokenExpiration;

    @Override
    public Optional<UsuarioResponseDTO> login(UsuarioLoginRequestDTO dto) {
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(dto.email(), dto.senha());
            var auth = this.manager.authenticate(usernamePassword);

            UserDetails userDetails = (UserDetails) auth.getPrincipal();

            Usuario usuario = repository.findByEmail(userDetails.getUsername());

            if (usuario == null) {
                throw new RuntimeException("Usuário não encontrado para o email fornecido.");
            }

            UsuarioResponseDTO responseDTO = MapperS.INSTANCE.toDTO(usuario);
            salvarTokens(usuario);
            return Optional.of(responseDTO);

        } catch (AuthenticationException e) {
            throw new RuntimeException("Erro ao processar o login: " + e.getMessage(), e);
        } catch (RuntimeException e) {
            throw new RuntimeException("Erro ao processar o login: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<String> register(UsuarioRegisterRequestDTO dto) {
        String passaword = dto.senha();
        String encryptedPassword = new BCryptPasswordEncoder().encode(passaword);

        Usuario usuario = MapperS.INSTANCE.toEntity(dto);

        usuario.setSenha(encryptedPassword);
        repository.save(usuario);

        return Optional.of("Foi");
    }

    @Override
    public void refreshToken() throws NotFoundException {
        String refreshToken = (String) sessionService.getAttribute("refreshToken");

        if (refreshToken == null) {
            throw new RuntimeException("Refresh token não encontrado na sessão.");
        }
        
        var subject = tokenService.validateToken(refreshToken);

        Usuario usuario = repository.findByEmail(subject);

        if (usuario == null) {
            throw new RuntimeException("Usuário não encontrado para o token fornecido.");
        }

        UserDetails userDetails = new UserDetailsImpl(usuario);
        var auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);

        salvarTokens(usuario);
    }

    @Override
    public void salvarTokens(Usuario usuario) {
        TokenDTO tokens = tokenService.obterTokens(usuario);
        UsuarioResponseDTO responseDTO = MapperS.INSTANCE.toDTO(usuario);

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            String userJson = objectMapper.writeValueAsString(responseDTO);
            sessionService.setAttribute("user", userJson);
            sessionService.setAttribute("refreshToken", tokens.refreshToken());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        addCookie("accessToken", tokens.token(), tokenExpiration, "/");
    }

    @Override
    public void logout() {
        addCookie("accessToken", "", 0, "/");
        addCookie("SESSION", "", 0, "/");

        sessionService.invalidate();
    }

    private void addCookie(String name, String value, int maxAge, String path) {
        Cookie cookie = new Cookie(name, value);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(maxAge);
        cookie.setPath(path);
        sessionService.addCookie(cookie);
    }

}
