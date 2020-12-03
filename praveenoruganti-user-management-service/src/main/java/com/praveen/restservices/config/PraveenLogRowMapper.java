package com.praveen.restservices.config;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.praveen.restservices.model.PraveenLog;

public class PraveenLogRowMapper implements RowMapper<PraveenLog>{
	
	@Override
	public PraveenLog mapRow(ResultSet rs, int rowNum) throws SQLException {
		PraveenLog praveenLog = new PraveenLog();		
		praveenLog.setLogId(rs.getInt("PRAVEENLOG_ID"));
		praveenLog.setLogMessage(rs.getString("PRAVEENLOG_MESSAGE"));
		praveenLog.setLogDate(rs.getDate("PRAVEENLOG_DATE"));
		
		return praveenLog;
	}

}
