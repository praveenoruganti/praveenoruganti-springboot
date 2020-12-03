package com.praveen.restservices.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.praveen.restservices.config.PraveenLogRowMapper;
import com.praveen.restservices.dao.PraveenLogDAO;
import com.praveen.restservices.handler.CreateLogEvent;
import com.praveen.restservices.model.PraveenLog;

@Repository
public class PraveenLogDAOImpl implements PraveenLogDAO {
	
	private JdbcTemplate jdbcTemplate;
	@Autowired
	PraveenLogDAOImpl(DataSource dataSource1) {
		this.jdbcTemplate = new JdbcTemplate(dataSource1);
	}
	
	@Autowired
	ApplicationEventPublisher publisher;

	@Override
	@Transactional
	public PraveenLog createLog(PraveenLog praveenLog) throws Exception {
		final String sql = "insert into PRAVEENLOG(PRAVEENLOG_MESSAGE,PRAVEENLOG_DATE) values(?,?)";
		KeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, praveenLog.getLogMessage());
				ps.setDate(2, praveenLog.getLogDate());			
				return ps;
			}
		}, holder);
		int praveenLogId = holder.getKey().intValue();
		System.out.println("Publishing Create Log Event");
		praveenLog.setLogId(praveenLogId);
		CreateLogEvent cle= new CreateLogEvent(this,"Praveen");
		publisher.publishEvent(cle);
		return praveenLog;
	}


	@Override
	@Transactional
	public List<PraveenLog> getAllLogs() {
		return jdbcTemplate.query("select * from PRAVEENLOG", new PraveenLogRowMapper());
	}
}