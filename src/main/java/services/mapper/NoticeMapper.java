package services.mapper;

import domain.Notice;
import org.mapstruct.Mapper;
import services.dto.NoticeDTO;

@Mapper(componentModel = "spring", uses = {})
public interface NoticeMapper extends EntityMapper<NoticeDTO, Notice> {}
