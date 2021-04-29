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
