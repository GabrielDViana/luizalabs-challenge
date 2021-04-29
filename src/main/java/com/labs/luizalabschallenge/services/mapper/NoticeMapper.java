package com.labs.luizalabschallenge.services.mapper;

import com.labs.luizalabschallenge.domain.Notice;
import org.mapstruct.Mapper;
import com.labs.luizalabschallenge.services.dto.NoticeDTO;

@Mapper(componentModel = "spring", uses = {})
public interface NoticeMapper extends EntityMapper<NoticeDTO, Notice> {}
