package com.praveen.rabbitmq.dao.impl;

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

import com.praveen.rabbitmq.config.PraveenLogRowMapper;
import com.praveen.rabbitmq.dao.PraveenLogDAO;
import com.praveen.rabbitmq.model.PraveenLog;

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
		System.out.println("Message Listener Pushed the Message "+praveenLog.getLogMessage()+" to mysql db ");
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
		praveenLog.setLogId(praveenLogId);		
		return praveenLog;
	}


	@Override
	@Transactional
	public List<PraveenLog> getAllLogs() {
		return jdbcTemplate.query("select * from PRAVEENLOG", new PraveenLogRowMapper());
	}
}