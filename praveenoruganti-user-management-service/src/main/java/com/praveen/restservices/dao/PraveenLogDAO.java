package com.praveen.restservices.dao;

import java.util.List;

import com.praveen.restservices.model.PraveenLog;

public interface PraveenLogDAO {
	abstract PraveenLog createLog(final PraveenLog praveenLog) throws Exception;
	abstract List<PraveenLog> getAllLogs();

}
