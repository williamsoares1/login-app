package com.estudos.crt.util.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.estudos.crt.dtos.request.UsuarioRegisterRequestDTO;
import com.estudos.crt.entities.Usuario;

@Mapper
public interface MapperS {

    @Mapping(target = "id", ignore = true)
    Usuario toEntity(UsuarioRegisterRequestDTO dto);
}