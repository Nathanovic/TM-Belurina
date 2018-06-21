package persistence.tour;

import model.Tour;
import persistence.BaseDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TourDAO extends BaseDAO {

    public boolean insertTour(Tour tour){
        try(Connection connection = super.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO tour VALUES (?, ?)");
            preparedStatement.setString(1, tour.getName());
            preparedStatement.setInt(2, tour.getYear());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Tour> getAllTours() {
        List<Tour> tours = new ArrayList<>();
        try (Connection connection = super.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM tour");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                tours.add(new Tour(resultSet.getString("name"), resultSet.getInt("year")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tours;
    }

}
