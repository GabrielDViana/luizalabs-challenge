package services.impl;

import domain.Notice;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.NoticeRepository;
import services.NoticeService;
import services.dto.NoticeDTO;
import services.mapper.NoticeMapper;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;

    private final NoticeMapper noticeMapper;

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
