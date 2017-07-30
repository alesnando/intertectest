package cr.intertec.user.persistencia.implementacion;

import java.util.Map;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import cr.intertec.user.dominio.User;
import cr.intertec.user.exception.UserException;
import cr.intertec.user.persistencia.IUserDao;

/**
 * 
 * Implementation of methods for connection to database
 *
 */
public class UserDAOImpl implements IUserDao {

	private JdbcTemplate jdbcTemplate;
	private DataSource dataSource;
	private ApplicationContext context;
	private SimpleJdbcCall procFindByUserName;
	private SimpleJdbcCall procGetProperty;

	/**
	 * Get user by username
	 */
	@Override
	public User findByUserName(String userName) {
		SqlParameterSource in = new MapSqlParameterSource()
                .addValue("userName", userName);
        Map<String, Object> out = procFindByUserName.execute(in);
        User user =null;
        if(out.get("out_id") != null){
        	user = new User();
        	user.setId((int)out.get("out_id"));
            user.setFirstName((String) out.get("out_firstname"));
            user.setLastName((String) out.get("out_lastname"));
            user.setUserName((String) out.get("out_username"));
        }        
		return user;
	}
	
	/**
	 * Get property value by key
	 */
	@Override
	public String getProperty(String key) throws UserException {
		SqlParameterSource in = new MapSqlParameterSource()
                .addValue("in_key", key);
        Map<String, Object> out = procGetProperty.execute(in);
        String value =null;
        if(out.get("out_value") != null){        	
            value = (String) out.get("out_value");
        }        
		return value;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.setJdbcTemplate(new JdbcTemplate(dataSource));
		this.procFindByUserName = new SimpleJdbcCall(dataSource)
                .withProcedureName("findByUserName");
		this.procGetProperty = new SimpleJdbcCall(dataSource)
                .withProcedureName("get_property");	
	}

	public UserDAOImpl getUserDAOImpl() {
		context = new ClassPathXmlApplicationContext("jdbc-config.xml");
		return (UserDAOImpl) context.getBean("userDAO");
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	
}
