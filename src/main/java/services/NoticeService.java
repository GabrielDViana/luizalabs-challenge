package services;

import org.springframework.data.domain.Pageable;
import services.dto.NoticeDTO;

import java.util.List;
import java.util.Optional;

public interface NoticeService {
    NoticeDTO save(NoticeDTO noticeDTO);

    Optional<NoticeDTO> partialUpdate(NoticeDTO noticeDTO);

    List<NoticeDTO> findAll();

    Optional<NoticeDTO> findOne(Long id);

    void delete(Long id);
}
