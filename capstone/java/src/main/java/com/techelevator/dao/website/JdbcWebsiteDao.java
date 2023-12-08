package com.techelevator.dao.website;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JdbcWebsiteDao implements WebsiteDao {

    private JdbcTemplate jdbcTemplate;

}
