package spittr.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import spittr.Spittle;


@Repository
public class JdbcSpittleRepository implements SpittleRepository {
	private JdbcOperations jdbcOperations;
	
	@Autowired
	public JdbcSpittleRepository(JdbcOperations jdbc) {
		this.jdbcOperations = jdbc;
	}

	@Override
	public List<Spittle> findSpittles(long max, int count) {
		    return jdbcOperations.query(
		        "select id, message, created_at, latitude, longitude" +
		        " from Spittle" +
		        " where id < ?" +
		        " order by created_at desc limit ?",
		        new SpittleRowMapper(), max, count);
		  }
	
	
	@Override
	public Spittle findOne(long id) {
	    return jdbcOperations.queryForObject(
	        "select id, message, created_at, latitude, longitude" +
	        " from Spittle" +
	        " where id = ?",
	        new SpittleRowMapper(), id);
	  }
	
	public static class SpittleRowMapper implements RowMapper<Spittle> {

		@Override
		public Spittle mapRow(ResultSet rs, int rowNum) throws SQLException {
		      return new Spittle(
		          rs.getLong("id"),
		          rs.getString("message"), 
		          rs.getDate("created_at"), 
		          rs.getDouble("longitude"), 
		          rs.getDouble("latitude"));
		}
	}

}
