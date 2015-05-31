package ie.cit.adf.config;

import ie.cit.adf.aspects.TracingAspect;

import javax.sql.DataSource;

import org.postgresql.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ImportResource("classpath:beans.xml")
@ComponentScan(basePackages = "ie.cit.adf.dao ie.cit.adf.service")
@EnableAspectJAutoProxy
@EnableTransactionManagement
@PropertySource("classpath:config.properties")
@EnableWebSecurity
public class RootApplicationContext extends WebSecurityConfigurerAdapter {
	@Autowired
	DataSource ds;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/todo/**").hasRole("USER").and()
				.formLogin().defaultSuccessUrl("/todo/all").and().httpBasic()
				.and().csrf().disable();

		http.authorizeRequests().anyRequest().authenticated().and().formLogin()
				.loginPage("/login").permitAll();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.jdbcAuthentication().dataSource(ds)
				.passwordEncoder(new BCryptPasswordEncoder());
	}

	@Autowired
	Environment env;

	@Bean
	public PlatformTransactionManager transactionManager(DataSource ds) {
		return new DataSourceTransactionManager(ds);
	}

	@Bean
	public TracingAspect tracingAspect() {
		return new TracingAspect();
	}

	// Production DB.
	@Bean(name = "dataSource")
	@Profile("cloud")
	public DataSource dataSource() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName(Driver.class.getName());
		ds.setUrl(env.getProperty("db.url"));
		ds.setUsername(env.getProperty("db.username"));
		ds.setPassword(env.getProperty("db.password"));
		return ds;
	}

	// Testing DB.
	@Bean(name = "dataSource")
	@Profile("dev")
	public DataSource dataSourceDev() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName(Driver.class.getName());
		ds.setUrl("jdbc:postgresql://localhost:5432/tododb");
		return ds;
	}

}