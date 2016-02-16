package db.migration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.flywaydb.core.api.migration.jdbc.JdbcMigration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class V3_0_1__set_mobileNumber_in_customer_table implements JdbcMigration {
  private static Logger logger = LoggerFactory.getLogger(V3_0_1__set_mobileNumber_in_customer_table.class);

  @Override
  public void migrate(Connection connection) throws Exception {
    try {
      logger.info("getting started V3_0_1__set_mobileNumber_in_customer_table migration");
      setMobileNumber(connection);
      logger.info("V3_0_1__set_mobileNumber_in_customer_table java migration completed successfully");
    } catch (Exception e) {
      logger.info("V3_0_1__set_mobileNumber_in_customer_table java migration failed ", e);
      throw new RuntimeException(e);
    }
  }

  void setMobileNumber(Connection connection) throws Exception {
    ResultSet resultSet = null;
    PreparedStatement preparedStatement = null;

    try {
      /*
       * preparedStatement = connection.prepareStatement("SELECT * FROM customer"); resultSet =
       * preparedStatement.executeQuery();
       */

      resultSet = connection.prepareStatement("SELECT id FROM customer").executeQuery();

      while (resultSet.next()) {
        Long id = resultSet.getLong("id");
        preparedStatement = connection.prepareStatement("UPDATE customer SET mobileNumber = ? WHERE id = ?");
        preparedStatement.setString(1, "1234567891");
        preparedStatement.setLong(2, id);

        Boolean isSuccess = (preparedStatement.executeUpdate() > 0) ? true : false;

        if (isSuccess) {
          logger.debug("setMobileNumber() :: given customer with id {} mobile number updated successfully", id);
          continue;
        }
        logger.debug("setMobileNumber() :: given customer with id {} mobile number failed to update", id);
      }
    } finally {
      // DbUtils.close(preparedStatement);
      // DbUtils.close(resultSet);
      // DbUtils.close(connection);
    }
  }
}
