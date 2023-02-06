package task01.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import task01.dao.MagazineDao;
import task01.domain.Magazine;
import task01.utils.ConnectionUtils;

public class MagazineDaoImpl implements MagazineDao {

	private static String READ_ALL = "select * from magazine";
	private static String CREATE = "insert into magazine(`name`,`description`,`publish_date`,`subscribe_price`) values (?,?,?,?)";
	private static String READ_BY_ID = "select * from magazine where id=?";
	private static String UPDATE_BY_ID = "update magazine set name=?, description=?, publish_date=?, subscribe_price=? where id=?";
	private static String DELETE_BY_ID = "delete from magazine where id=?";

	private Connection connection;
	private PreparedStatement statement;
	
	private static Logger logger = Logger.getLogger(MagazineDaoImpl.class);

	public MagazineDaoImpl() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		connection = ConnectionUtils.openConnection();
	}

	@Override
	public Magazine create(Magazine magazine) {
		try {
			statement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, magazine.getName());
			statement.setString(2, magazine.getDescription());
			statement.setDate(3, new Date(magazine.getPublishDate().getTime()));
			statement.setDouble(4, magazine.getSubscribePrice());
			statement.executeUpdate();

			ResultSet rs = statement.getGeneratedKeys();
			rs.next();
			magazine.setId(rs.getInt(1));
		} catch (SQLException e) {
			logger.error(e);
		}
		
		return magazine;
	}

	@Override
	public List<Magazine> readAll() {
		List<Magazine> listOfMagazines = new ArrayList<>();
		try {
			statement = connection.prepareStatement(READ_ALL);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				Integer magazineId = result.getInt("id");
				String magazineName = result.getString("name");
				String magazineDescription = result.getString("description");
				Date publishDate = result.getDate("publish_date");
				Double subscribePrice = result.getDouble("subscribe_price");

				listOfMagazines.add(new Magazine(magazineId, magazineName, magazineDescription, publishDate, subscribePrice));
			}
		} catch (SQLException e) {
			logger.error(e);
		}

		return listOfMagazines;
	}

	@Override
	public Magazine read(Integer id) {
		Magazine magazine = null;
		try {
			statement = connection.prepareStatement(READ_BY_ID);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				Integer magazineId = result.getInt("id");
				String magazineName = result.getString("name");
				String magazineDescription = result.getString("description");
				Date publishDate = result.getDate("publish_date");
				Double subscribePrice = result.getDouble("subscribe_price");

				magazine = new Magazine(magazineId, magazineName, magazineDescription, publishDate, subscribePrice);
			}
		} catch (SQLException e) {
			logger.error(e);
		}

		return magazine;
	}

	@Override
	public Magazine update(Magazine magazine) {
		try {
			statement = connection.prepareStatement(UPDATE_BY_ID);
			statement.setString(1, magazine.getName());
			statement.setString(2, magazine.getDescription());
			statement.setDate(3, new Date(magazine.getPublishDate().getTime()));
			statement.setDouble(4, magazine.getSubscribePrice());
			statement.setInt(5, magazine.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			logger.error(e);
		}
		
		return magazine;
	}

	@Override
	public void delete(Integer id) {
		try {
			statement = connection.prepareStatement(DELETE_BY_ID);
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			logger.error(e);
		}
	}

}