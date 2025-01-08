package guestbook.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import guestbook.repository.template.JdbcContext;
import guestbook.vo.GuestbookVo;

@Repository
public class GuestbookRepository {
	private JdbcContext jdbcContext;
	private DataSource dataSource;
	
	public GuestbookRepository(JdbcContext jdbcContext, DataSource dataSource) {
		this.jdbcContext=jdbcContext;
		this.dataSource=dataSource;
	}

	public int insert(GuestbookVo vo) {
		return jdbcContext.update("insert into guestbook values (null,?,?,?,now())", 
				vo.getName(),vo.getPassword(),vo.getContents());
	}
	
	public List<GuestbookVo> findAll(){
		return jdbcContext.query("select id, name, contents, date_format(reg_date, '%Y-%m-%d %h:%i:%s') as regDate from guestbook order by reg_date desc",
				new BeanPropertyRowMapper<>(GuestbookVo.class));
	}
	
	public int deleteByIdAndPassword(Long id, String password) {
		return jdbcContext.update("delete from guestbook where id=? and password=?", 
				id, password);
	}
	
	public GuestbookVo findById(Long id) {
		return jdbcContext.queryForObject("select id, name, contents, date_format(reg_date, '%Y-%m-%d') as regDate from guestbook where id=?",new Object[] {id},new BeanPropertyRowMapper<>(GuestbookVo.class));
	}

}
