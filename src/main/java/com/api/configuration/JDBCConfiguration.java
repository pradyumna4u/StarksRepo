package com.api.configuration;

import javax.jms.JMSException;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.api.framework.Prop;

public class JDBCConfiguration {

	private static String className = Prop.getProp("db.className");
	private static String url = Prop.getProp("db.url");
	private static String userName = Prop.getProp("db.username");
	private static String password = Prop.getProp("db.password");

	private static DataSource setdataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(className);
		dataSource.setUrl(url);
		dataSource.setUsername(userName);
		dataSource.setPassword(password);
		return dataSource;
	}

	public static JdbcTemplate jdbcTemplate() throws JMSException {
		return new JdbcTemplate(JDBCConfiguration.setdataSource());
	}
}
