package co.com.myndfuly.mapper;

import co.com.myndfuly.controller.dto.BasicInfoCustomerDto;
import co.com.myndfuly.controller.dto.CustomerDto;
import co.com.myndfuly.data.BasicInfoCustomerData;
import co.com.myndfuly.data.CustomerData;
import co.com.myndfuly.persistence.entity.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CustomerMapper {
  CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

  CustomerDto toDto(CustomerData customerData);

  CustomerData toData(CustomerEntity customerEntity);

  BasicInfoCustomerData toData(BasicInfoCustomerDto basicInfoCustomerDto);

  CustomerEntity toEntity(CustomerData customerData);
}
