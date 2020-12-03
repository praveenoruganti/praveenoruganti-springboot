package com.praveen.rabbitmq.dao;

import java.util.List;

import com.praveen.rabbitmq.model.PraveenLog;

public interface PraveenLogDAO {
	abstract PraveenLog createLog(final PraveenLog praveenLog) throws Exception;
	abstract List<PraveenLog> getAllLogs();

}
