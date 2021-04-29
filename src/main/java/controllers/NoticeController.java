package controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.dto.NoticeDTO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URISyntaxException;
import java.util.List;

@RequestMapping("/notice")
public interface NoticeController {
    @ApiOperation(value = "Notice creation")
    @PostMapping("/notices")
    ResponseEntity<NoticeDTO> createNotice(@Valid @RequestBody NoticeDTO noticeDTO) throws URISyntaxException;

    @ApiOperation(value = "Notice updating")
    @PutMapping("/notices/{id}")
    ResponseEntity<NoticeDTO> updateNotice(
            @PathVariable(value = "id", required = false) final Long id,
            @Valid @RequestBody NoticeDTO noticeDTO
    ) throws URISyntaxException;

    @ApiOperation(value = "Notice partial updating")
    @PatchMapping(value = "/notices/{id}", consumes = "application/merge-patch+json")
    ResponseEntity<NoticeDTO> partialUpdateNotice(
            @PathVariable(value = "id", required = false) final Long id,
            @NotNull @RequestBody NoticeDTO noticeDTO
    ) throws URISyntaxException;

    @ApiOperation(value = "Return all Notices")
    @GetMapping("/notices")
    ResponseEntity<List<NoticeDTO>> getAllNotices();

    @ApiOperation(value = "Return Notice by id")
    @GetMapping("/notices/{id}")
    ResponseEntity<NoticeDTO> getNotice(@PathVariable Long id);

    @ApiOperation(value = "Delete Notice by id")
    @DeleteMapping("/notices/{id}")
    ResponseEntity<Void> deleteNotice(@PathVariable Long id);

}
