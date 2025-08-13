package co.com.myndfuly.mapper;

import co.com.myndfuly.controller.dto.ContactDto;
import co.com.myndfuly.data.ContactData;
import co.com.myndfuly.persistence.entity.ContactEntity;
import java.util.List;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ContactMapper {
  ContactMapper INSTANCE = Mappers.getMapper(ContactMapper.class);

  @Named("toDto")
  ContactDto toDto(ContactData contactData);

  @IterableMapping(qualifiedByName = "toDto")
  List<ContactDto> toDto(List<ContactData> contactData);

  ContactData toData(ContactDto contactDto);

  @Named("toData")
  ContactData toData(ContactEntity contactEntity);

  @IterableMapping(qualifiedByName = "toData")
  List<ContactData> toData(List<ContactEntity> contactEntities);

  @Mapping(target = "customer.id", source = "customerId")
  ContactEntity toEntity(Integer customerId, ContactData contactData);
}
