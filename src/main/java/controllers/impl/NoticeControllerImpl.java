package controllers.impl;

import controllers.NoticeController;
import exception.BadRequestException;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repository.NoticeRepository;
import services.NoticeService;
import services.dto.NoticeDTO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@Api(value = "Notice", tags = {"Notice"})
@AllArgsConstructor
public class NoticeControllerImpl implements NoticeController {

    private final NoticeService noticeService;

    private final NoticeRepository noticeRepository;

    @Override
    public ResponseEntity<NoticeDTO> createNotice(@Valid @RequestBody NoticeDTO noticeDTO) throws URISyntaxException {
        if (noticeDTO.getId() != null) {
            throw new BadRequestException("A new notice cannot already have an ID");
        }
        NoticeDTO result = noticeService.save(noticeDTO);
        return ResponseEntity
                .created(new URI("/api/notices/" + result.getId()))
                .body(result);
    }

    @Override
    public ResponseEntity<NoticeDTO> updateNotice(
            @PathVariable(value = "id", required = false) final Long id,
            @Valid @RequestBody NoticeDTO noticeDTO
    ) throws URISyntaxException {
        this.verifyDataIntegrity(id, noticeDTO);

        NoticeDTO result = noticeService.save(noticeDTO);
        return ResponseEntity
                .ok()
                .body(result);
    }

    @Override
    public ResponseEntity<NoticeDTO> partialUpdateNotice(
            @PathVariable(value = "id", required = false) final Long id,
            @NotNull @RequestBody NoticeDTO noticeDTO
    ) throws URISyntaxException {
        this.verifyDataIntegrity(id, noticeDTO);

        Optional<NoticeDTO> result = noticeService.partialUpdate(noticeDTO);

        return result.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }

    @Override
    public ResponseEntity<List<NoticeDTO>> getAllNotices() {
        List<NoticeDTO> notices = noticeService.findAll();
        return ResponseEntity.ok().body(notices);
    }

    @Override
    public ResponseEntity<NoticeDTO> getNotice(@PathVariable Long id) {
        Optional<NoticeDTO> noticeDTO = noticeService.findOne(id);
        if (!noticeDTO.isPresent()) {
            throw new BadRequestException("notice not found");
        }
        return noticeDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }

    @Override
    public ResponseEntity<Void> deleteNotice(@PathVariable Long id) {
        noticeService.delete(id);
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
