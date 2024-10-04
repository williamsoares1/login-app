package com.estudos.crt.util.mapper;

import java.util.Objects;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.estudos.crt.dtos.request.UsuarioRegisterRequestDTO;
import com.estudos.crt.dtos.response.UsuarioResponseDTO;
import com.estudos.crt.entities.Usuario;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Mapper
public interface MapperS {
    MapperS INSTANCE = Mappers.getMapper(MapperS.class);
    ObjectMapper objectMapper = new ObjectMapper();

    @Mapping(target = "id", ignore = true)
    Usuario toEntity(UsuarioRegisterRequestDTO dto);

    UsuarioResponseDTO toDTO(Usuario usuario);

    default UsuarioResponseDTO StringToDTO(String string) throws JsonMappingException, JsonProcessingException {
        if (Objects.nonNull(string)) {
            return objectMapper.readValue(string, UsuarioResponseDTO.class);
        }
        return null;
    }
}