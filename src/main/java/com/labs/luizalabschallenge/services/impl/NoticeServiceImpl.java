package com.labs.luizalabschallenge.services.impl;

import com.labs.luizalabschallenge.domain.Notice;
import com.labs.luizalabschallenge.services.schedule.NoticeSchedule;
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
@AllArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;

    private final NoticeMapper noticeMapper;

    private final NoticeSchedule noticeSchedule;

    @Override
    public NoticeDTO save(NoticeDTO noticeDTO) {
        Notice notice = noticeMapper.toEntity(noticeDTO);
        notice = noticeRepository.save(notice);

        final var map = noticeMapper.map(notice);
        noticeSchedule.scheduleRunnableTrigger(map);
        return map;
    }

    @Override
    @Transactional(readOnly = true)
    public List<NoticeDTO> findAll() {
        return noticeRepository.findAll().stream().map(noticeMapper::map).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<NoticeDTO> findOne(Long id) {
        return noticeRepository.findById(id).map(noticeMapper::map);
    }

    @Override
    public void delete(Long id) {
        noticeRepository.deleteById(id);
    }
}
