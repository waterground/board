package com.sjh.web.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mchange.v2.c3p0.DriverManagerDataSource;

@Configuration
public class DBConfig {
	private String user = "root"; // 사용자 이름
	private String password = "123456"; // PW
	private String db = "board"; // DB이름
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/" + db +"?characterEncoding=euckr&useUnicode=true&mysqlEncoding=euckr";
	
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClass(driver);
		dataSource.setJdbcUrl(url);
		dataSource.setUser(user);
		dataSource.setPassword(password);
		return dataSource;
	}
}
