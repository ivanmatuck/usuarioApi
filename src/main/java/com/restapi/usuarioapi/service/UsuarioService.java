package com.restapi.usuarioapi.service;

import com.restapi.usuarioapi.dto.MessageResponseDTO;
import com.restapi.usuarioapi.dto.UsuarioDTO;
import com.restapi.usuarioapi.entity.Usuario;
import com.restapi.usuarioapi.exception.UsuarioForCpfNotFoundException;
import com.restapi.usuarioapi.exception.UsuarioNotFoundException;
import com.restapi.usuarioapi.mapper.UsuarioMapper;
import com.restapi.usuarioapi.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UsuarioService {

    private UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper = UsuarioMapper.INSTANCE;

    public MessageResponseDTO createUsuario(UsuarioDTO usuarioDTO){
            Usuario usuarioToSave = usuarioMapper.toModel(usuarioDTO);
            Usuario savedUsuario = usuarioRepository.save(usuarioToSave);

        return createMessageResponse(savedUsuario.getId(), "Created usuario with ID ");
    }

    public List<UsuarioDTO> listAll() {
        List<Usuario> allPeople = usuarioRepository.findAll();
        return allPeople.stream()
                .map(usuarioMapper::toDTO)
                .collect(Collectors.toList());
    }

    public UsuarioDTO findByCPF(String cpf) throws UsuarioNotFoundException {
        Usuario usuario = verifyIfExists(cpf);
            return usuarioMapper.toDTO(usuario);

    }

    public MessageResponseDTO updateByCpf(String cpf, UsuarioDTO usuarioDTO) throws UsuarioNotFoundException {
        verifyIfExists(cpf);

        Usuario usuarioToUpdate = usuarioMapper.toModel(usuarioDTO);

        Usuario updatedUsuario = usuarioRepository.save(usuarioToUpdate);
        return createMessageResponseString(updatedUsuario.getCpf(), "Updated usuario with ID ");
    }

    @Transactional
    public void deleteByCpf(String cpf) throws UsuarioNotFoundException {
        verifyIfExists(cpf);
        usuarioRepository.deleteUsuarioByCpf(cpf);
    }

    private Usuario verifyIfExists(String cpf) throws UsuarioNotFoundException{
        return usuarioRepository.findUsuarioByCpf(cpf)
                .orElseThrow(() ->new UsuarioNotFoundException(cpf));
    }

    private MessageResponseDTO createMessageResponse(Long id, String message) {
        return MessageResponseDTO
                .builder()
                .message(message + id)
                .build();
    }

    private MessageResponseDTO createMessageResponseString(String cpf, String message) {
        return MessageResponseDTO
                .builder()
                .message(message + cpf)
                .build();
    }
}
