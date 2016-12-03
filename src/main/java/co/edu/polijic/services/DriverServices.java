package co.edu.polijic.services;

import co.edu.polijic.domain.Driver;
import co.edu.polijic.repositories.DriverRepository;
import io.reactivex.Observable;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@ComponentScan
@Component
public class DriverServices implements DriverRepository {
    private final BasicDataSource dataSource;
    private final String generatedColumns[] = {"IDSERVICE"};

    @Autowired
    public DriverServices(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public Observable<Driver> getDrivers() {
        return Observable.create(observer -> {
            try (
                    Connection connection = dataSource.getConnection();
                    PreparedStatement statement = connection.prepareStatement("SELECT d.cabs_plate, p.names, p.lastnames, d.idDriver FROM DRIVERS d, PERSONS p WHERE d.PERSONS_identification=p.identification");
                    ResultSet resultSet = statement.executeQuery();
            ) {
                while (resultSet.next()) {
                    Driver driver = new Driver(
                            resultSet.getLong(4),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(1)
                    );
                    observer.onNext(driver);
                }
            } catch (Exception e) {
                observer.onError(e);
            } finally {
                observer.onComplete();
            }
        });
    }
}
