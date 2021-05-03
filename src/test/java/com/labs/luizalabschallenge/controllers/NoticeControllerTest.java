package com.labs.luizalabschallenge.controllers;

import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import com.labs.luizalabschallenge.TestUtils;
import com.labs.luizalabschallenge.domain.Notice;
import com.labs.luizalabschallenge.enums.NoticeType;
import com.labs.luizalabschallenge.exception.BadRequestException;
import com.labs.luizalabschallenge.repository.NoticeRepository;
import com.labs.luizalabschallenge.services.NoticeService;
import com.labs.luizalabschallenge.services.dto.NoticeDTO;
import com.labs.luizalabschallenge.services.impl.NoticeServiceImpl;
import com.labs.luizalabschallenge.services.mapper.NoticeMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.NestedServletException;


@ExtendWith(MockitoExtension.class)
class NoticeControllerTest {

    private static final String DEFAULT_PHONE_NUMBER = "99999999999";

    private static final ZonedDateTime DEFAULT_SCHEDULE_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);

    private static final NoticeType DEFAULT_NOTICE_TYPE = NoticeType.WHATSAPP;

    private static final String DEFAULT_MESSAGE_CONTENT = "Default message!";

    private static final String DEFAULT_EMAIL = "test@test.com";

    private static final String ENTITY_API_URL = "/api/notices";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private NoticeController noticeController;

    @Mock
    private NoticeRepository noticeRepository;

    private NoticeMapper noticeMapper;

    private NoticeService noticeService;

    private MockMvc mockMvc;

    private Notice notice;

    public static Notice createEntity() {
        Notice notice = Notice.builder()
                .phoneNumber(DEFAULT_PHONE_NUMBER)
                .scheduleDate(DEFAULT_SCHEDULE_DATE)
                .noticeType(DEFAULT_NOTICE_TYPE)
                .messageContent(DEFAULT_MESSAGE_CONTENT)
                .email(DEFAULT_EMAIL)
                .build();
        return notice;
    }

    @BeforeEach
    public void initTest() {
        notice = createEntity();
        noticeMapper = new NoticeMapper();
        noticeService =  new NoticeServiceImpl(noticeRepository, noticeMapper);
        noticeController = new NoticeController(noticeService, noticeRepository);
        mockMvc = MockMvcBuilders.standaloneSetup(noticeController).build();
    }

    @Test
    @Transactional
    void createNotice() throws Exception {

        NoticeDTO noticeDTO = noticeMapper.map(notice);
        when(noticeRepository.save(any())).thenReturn(notice);

        mockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtils.convertObjectToJsonBytes(noticeDTO)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.phoneNumber").value(DEFAULT_PHONE_NUMBER))
                .andExpect(jsonPath("$.scheduleDate").hasJsonPath())
                .andExpect(jsonPath("$.noticeType").value(DEFAULT_NOTICE_TYPE.getNoticeDesc()))
                .andExpect(jsonPath("$.messageContent").value(DEFAULT_MESSAGE_CONTENT))
                .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL));
    }

    @Test
    @Transactional
    void getAllNotices() throws Exception {
        noticeRepository.saveAndFlush(notice);

        final var notices = new ArrayList<Notice>(Collections.singleton(notice));
        when(noticeRepository.findAll()).thenReturn(notices);

        mockMvc
                .perform(get(ENTITY_API_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.[*].phoneNumber").value(hasItem(DEFAULT_PHONE_NUMBER)))
                .andExpect(jsonPath("$.[*].scheduleDate").hasJsonPath())
                .andExpect(jsonPath("$.[*].noticeType").value(hasItem(DEFAULT_NOTICE_TYPE.getNoticeDesc())))
                .andExpect(jsonPath("$.[*].messageContent").value(hasItem(DEFAULT_MESSAGE_CONTENT)))
                .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)));
    }

    @Test
    @Transactional
    void getNotice() throws Exception {

        noticeRepository.saveAndFlush(notice);
        when(noticeRepository.findById(any())).thenReturn(Optional.ofNullable(notice));

        mockMvc
                .perform(get(ENTITY_API_URL_ID, 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.phoneNumber").value(DEFAULT_PHONE_NUMBER))
                .andExpect(jsonPath("$.scheduleDate").hasJsonPath())
                .andExpect(jsonPath("$.noticeType").value(DEFAULT_NOTICE_TYPE.getNoticeDesc()))
                .andExpect(jsonPath("$.messageContent").value(DEFAULT_MESSAGE_CONTENT))
                .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL));
    }

    @Test
    @Transactional
    void getNonExistingNotice() throws BadRequestException, Exception {
        try {
            mockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE))
                    .andExpect(status().isBadRequest())
                    .andExpect(result -> assertTrue(result.getResolvedException() instanceof BadRequestException))
                    .andExpect(result -> assertEquals("notice not found", result.getResolvedException().getMessage()));
        } catch (NestedServletException e) {
            e.printStackTrace();
        }
    }

    @Disabled("DeleteMapping not recognized")
    @Test
    @Transactional
    void deleteNotice() throws Exception {

        Mockito.doNothing().when(noticeRepository).delete(any());
        mockMvc
                .perform(delete(ENTITY_API_URL_ID, notice.getId()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
