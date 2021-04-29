package com.labs.luizalabschallenge.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import java.io.IOException;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.labs.luizalabschallenge.domain.Notice;
import com.labs.luizalabschallenge.enums.NoticeType;
import com.labs.luizalabschallenge.repository.NoticeRepository;
import com.labs.luizalabschallenge.services.NoticeService;
import com.labs.luizalabschallenge.services.dto.NoticeDTO;
import com.labs.luizalabschallenge.services.mapper.NoticeMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;


@ExtendWith(MockitoExtension.class)
class NoticeControllerTest {

    private static final String DEFAULT_PHONE_NUMBER = "99999999999";

    private static final ZonedDateTime DEFAULT_SCHEDULE_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);

    private static final NoticeType DEFAULT_NOTICE_TYPE = NoticeType.WHATSAPP;

    private static final String DEFAULT_MESSAGE_CONTENT = "Default message!";

    private static final String DEFAULT_EMAIL = "test@test.com";

    private static final String ENTITY_API_URL = "/api/notices";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    private NoticeController noticeController;
    @Mock
    private NoticeRepository noticeRepository;

    @Mock
    private NoticeMapper noticeMapper;

    @Mock
    private NoticeService noticeService;

    @Autowired
    private EntityManager em;

    private MockMvc mockMvc;

    private Notice notice;

    private static final ObjectMapper mapper = createObjectMapper();

    public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        return mapper.writeValueAsBytes(object);
    }
    private static ObjectMapper createObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRITE_DURATIONS_AS_TIMESTAMPS, false);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }

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
        noticeController = new NoticeController(noticeService, noticeRepository);
        mockMvc = MockMvcBuilders.standaloneSetup(noticeController).build();
    }

    @Test
    @Transactional
    void createNotice() throws Exception {
        int databaseSizeBeforeCreate = noticeRepository.findAll().size();

        NoticeDTO noticeDTO = noticeMapper.toDto(notice);
        mockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(this.convertObjectToJsonBytes(noticeDTO)))
                .andExpect(status().isCreated());


        List<Notice> noticeList = noticeRepository.findAll();
        assertThat(noticeList).hasSize(databaseSizeBeforeCreate + 1);
        Notice testNotice = noticeList.get(noticeList.size() - 1);
        assertThat(testNotice.getPhoneNumber()).isEqualTo(DEFAULT_PHONE_NUMBER);
        assertThat(testNotice.getScheduleDate()).isEqualTo(DEFAULT_SCHEDULE_DATE);
        assertThat(testNotice.getNoticeType()).isEqualTo(DEFAULT_NOTICE_TYPE);
        assertThat(testNotice.getMessageContent()).isEqualTo(DEFAULT_MESSAGE_CONTENT);
        assertThat(testNotice.getEmail()).isEqualTo(DEFAULT_EMAIL);
    }

    @Test
    @Transactional
    void getAllNotices() throws Exception {
        noticeRepository.saveAndFlush(notice);

        mockMvc
                .perform(get(ENTITY_API_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.[*].id").value(hasItem(notice.getId().intValue())))
                .andExpect(jsonPath("$.[*].phoneNumber").value(hasItem(DEFAULT_PHONE_NUMBER)))
                .andExpect(jsonPath("$.[*].scheduleDate").value(hasItem(DEFAULT_SCHEDULE_DATE)))
                .andExpect(jsonPath("$.[*].noticeType").value(hasItem(DEFAULT_NOTICE_TYPE)))
                .andExpect(jsonPath("$.[*].messageContent").value(hasItem(DEFAULT_MESSAGE_CONTENT)))
                .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)));
    }

    @Test
    @Transactional
    void getNotice() throws Exception {

        noticeRepository.saveAndFlush(notice);

        mockMvc
                .perform(get(ENTITY_API_URL_ID, notice.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(notice.getId().intValue()))
                .andExpect(jsonPath("$.phoneNumber").value(DEFAULT_PHONE_NUMBER))
                .andExpect(jsonPath("$.scheduleDate").value(DEFAULT_SCHEDULE_DATE))
                .andExpect(jsonPath("$.[*].noticeType").value(hasItem(DEFAULT_NOTICE_TYPE)))
                .andExpect(jsonPath("$.[*].messageContent").value(hasItem(DEFAULT_MESSAGE_CONTENT)))
                .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)));
    }

    @Test
    @Transactional
    void getNonExistingNotice() throws Exception {
        mockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void deleteNotice() throws Exception {
        noticeRepository.saveAndFlush(notice);

        int databaseSizeBeforeDelete = noticeRepository.findAll().size();

        mockMvc
                .perform(delete(ENTITY_API_URL_ID, notice.getId()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        List<Notice> noticeList = noticeRepository.findAll();
        assertThat(noticeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
