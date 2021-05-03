package com.labs.luizalabschallenge.controllers;

import com.labs.luizalabschallenge.exception.BadRequestException;
import com.labs.luizalabschallenge.repository.NoticeRepository;
import com.labs.luizalabschallenge.services.NoticeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.labs.luizalabschallenge.services.dto.NoticeDTO;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class NoticeController {

    private final NoticeService noticeService;

    private final NoticeRepository noticeRepository;

    @PostMapping("/notices")
    public ResponseEntity<NoticeDTO> createNotice(@Valid @RequestBody NoticeDTO noticeDTO) throws URISyntaxException {
        if (noticeDTO.getId() != null) {
            throw new BadRequestException("A new notice cannot already have an ID");
        }
        NoticeDTO result = noticeService.save(noticeDTO);
        return ResponseEntity
                .created(new URI("/api/notices/" + result.getId()))
                .body(result);
    }

    @GetMapping("/notices")
    public ResponseEntity<List<NoticeDTO>> getAllNotices() {
        List<NoticeDTO> notices = noticeService.findAll();
        return ResponseEntity.ok().body(notices);
    }

    @GetMapping("/notices/{id}")
    public ResponseEntity<NoticeDTO> getNotice(@PathVariable Long id) {
        Optional<NoticeDTO> noticeDTO = noticeService.findOne(id);
        if (!noticeDTO.isPresent()) {
            throw new BadRequestException("notice not found");
        }
        return noticeDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }

    @RequestMapping(value = "/notices/{id}", method = DELETE)
    public ResponseEntity<Void> deleteNotice(@PathVariable("id") Long id) {
        try {
            noticeService.delete(id);
        } catch (BadRequestException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity
                .noContent()
                .build();
    }

    private void verifyDataIntegrity(final Long id, final NoticeDTO notice) {
        if (notice.getId() == null || !Objects.equals(id, notice.getId())) {
            throw new BadRequestException("Invalid notice id");
        }

        if (!noticeRepository.existsById(id)) {
            throw new BadRequestException("Entity notice not found");
        }
    }

}
