package com.labs.luizalabschallenge.services;

import com.labs.luizalabschallenge.services.dto.NoticeDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface NoticeService {
    NoticeDTO save(NoticeDTO noticeDTO);

    List<NoticeDTO> findAll();

    Optional<NoticeDTO> findOne(Long id);

    void delete(Long id);
}
