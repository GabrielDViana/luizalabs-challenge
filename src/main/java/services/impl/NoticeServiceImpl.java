package services.impl;

import domain.Notice;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.NoticeRepository;
import services.NoticeService;
import services.dto.NoticeDTO;
import services.mapper.NoticeMapper;

import java.util.Optional;

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
    public Optional<NoticeDTO> partialUpdate(NoticeDTO noticeDTO) {

        return noticeRepository
                .findById(noticeDTO.getId())
                .map(
                        existingNotice -> {
                            noticeMapper.partialUpdate(existingNotice, noticeDTO);
                            return existingNotice;
                        }
                )
                .map(noticeRepository::save)
                .map(noticeMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<NoticeDTO> findAll(Pageable pageable) {
        return noticeRepository.findAll(pageable).map(noticeMapper::toDto);
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
