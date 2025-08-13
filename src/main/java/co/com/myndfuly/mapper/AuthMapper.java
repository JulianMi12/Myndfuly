package co.com.myndfuly.mapper;

import co.com.myndfuly.controller.dto.SessionResponseDto;
import co.com.myndfuly.controller.dto.SignUpRequestDto;
import co.com.myndfuly.data.SessionResponseData;
import co.com.myndfuly.data.SignUpRequestData;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AuthMapper {

  AuthMapper INSTANCE = Mappers.getMapper(AuthMapper.class);

  SessionResponseDto toDto(SessionResponseData sessionResponseData);

  SignUpRequestData toData(SignUpRequestDto signUpRequestDto);
}
