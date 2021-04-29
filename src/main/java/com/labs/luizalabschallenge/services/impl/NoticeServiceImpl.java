package com.labs.luizalabschallenge.services.impl;

import com.labs.luizalabschallenge.domain.Notice;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.labs.luizalabschallenge.repository.NoticeRepository;
import com.labs.luizalabschallenge.services.NoticeService;
import com.labs.luizalabschallenge.services.dto.NoticeDTO;
import com.labs.luizalabschallenge.services.mapper.NoticeMapper;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;

    private final NoticeMapper noticeMapper;

    public NoticeServiceImpl(NoticeRepository noticeRepository, NoticeMapper noticeMapper) {
        this.noticeRepository = noticeRepository;
        this.noticeMapper = noticeMapper;
    }

    @Override
    public NoticeDTO save(NoticeDTO noticeDTO) {
        Notice notice = noticeMapper.toEntity(noticeDTO);
        notice = noticeRepository.save(notice);

        return noticeMapper.toDto(notice);
    }

    @Override
    @Transactional(readOnly = true)
    public List<NoticeDTO> findAll() {
        return noticeRepository.findAll().stream().map(noticeMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<NoticeDTO> findOne(Long id) {
        return noticeRepository.findById(id).map(noticeMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        noticeRepository.deleteById(id);
    }
}
