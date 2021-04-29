package services;

import services.dto.NoticeDTO;

import java.util.List;
import java.util.Optional;

public interface NoticeService {
    NoticeDTO save(NoticeDTO noticeDTO);

    List<NoticeDTO> findAll();

    Optional<NoticeDTO> findOne(Long id);

    void delete(Long id);
}
