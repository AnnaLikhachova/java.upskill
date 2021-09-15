package com.likhachova.util;

import com.likhachova.dao.jdbc.JdbcGenreDao;
import com.likhachova.service.GenreService;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/dispatcher-servlet.xml")
public class CacheGenreManagerTest {

    private static final Logger logger = LoggerFactory.getLogger(CacheGenreManagerTest.class);

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private GenreService genreService;

    @Test
    void getContext() {
        assertTrue(webApplicationContext.getServletContext() instanceof MockServletContext);
    }

    @BeforeEach
    public void before() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        genreService = mockMvc.getDispatcherServlet().getWebApplicationContext().getBean(GenreService.class);
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setUrl("jdbc:h2:~/movies1;DB_CLOSE_DELAY=-1");
        dataSource.setUser("sa");
        dataSource.setPassword("");
        assertNotNull(dataSource);
        JdbcGenreDao genreDao = new JdbcGenreDao(dataSource);
        genreService.setGenreDao(genreDao);
    }

    @Test
    @DisplayName("Get all genres and check cache")
    public void test_getAllGenres() {
        logger.info("getting genres from database:");
        genreService.findAll();
        logger.info("getting genres from cache:");
        genreService.findAll().forEach(u -> logger.info("{}", u.toString()));
    }
}
