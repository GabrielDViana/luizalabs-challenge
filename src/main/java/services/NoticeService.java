package services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import services.dto.NoticeDTO;

import java.util.Optional;

public interface NoticeService {
    NoticeDTO save(NoticeDTO noticeDTO);

    Optional<NoticeDTO> partialUpdate(NoticeDTO noticeDTO);

    Page<NoticeDTO> findAll(Pageable pageable);

    Optional<NoticeDTO> findOne(Long id);

    void delete(Long id);
}
