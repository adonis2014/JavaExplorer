package name.chenyuelin.security;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;

public class MySqlPasswordEncoder implements PasswordEncoder {
	private static final String ENCODE_PD_SCRIPT="select password(?) pd;";
	
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public String encode(CharSequence rawPassword) {
		String encodePd=jdbcTemplate.queryForObject(ENCODE_PD_SCRIPT,String.class,rawPassword.toString());
		return encodePd;
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		String encodePd=jdbcTemplate.queryForObject(ENCODE_PD_SCRIPT,String.class,rawPassword.toString());
		return encodePd.equals(encodedPassword);
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}
