package com.kryhowsky.maslibrary.mapper;

import com.kryhowsky.maslibrary.model.dao.Auditable;
import com.kryhowsky.maslibrary.model.dto.AuditableDto;
import com.kryhowsky.maslibrary.security.SecurityUtils;
import org.mapstruct.AfterMapping;
import org.mapstruct.MappingTarget;

public interface AuditableMapper<T extends Auditable, U extends AuditableDto> {

    @AfterMapping
    default void mapAuditToDto(T auditable, @MappingTarget U.AuditableDtoBuilder<?, ?> auditableDto) {
        if (!SecurityUtils.hasRole("ROLE_ADMIN")) {
            auditableDto.createdBy(null);
            auditableDto.createdDate(null);
            auditableDto.lastModifiedBy(null);
            auditableDto.lastModifiedDate(null);
        }
    }
}
