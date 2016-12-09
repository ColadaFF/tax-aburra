package co.edu.polijic.services;

import co.edu.polijic.domain.Ride;
import co.edu.polijic.repositories.RidesRepository;
import io.reactivex.Observable;
import io.reactivex.Single;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.sql.*;

@ComponentScan
@Component
public class RideServices implements RidesRepository {
    private final BasicDataSource dataSource;
    private final String generatedColumns[] = {"IDSERVICE"};

    @Autowired
    public RideServices(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public Observable<Ride> listRides(int limit, int page) {
        return Observable.create(observer -> {
            try (
                    Connection connection = dataSource.getConnection();
                    PreparedStatement statement = connection.prepareStatement("SELECT * FROM RIDES");
                    ResultSet resultSet = statement.executeQuery();
            ) {
                while (resultSet.next()) {
                    Ride ride = new Ride(
                            resultSet.getLong(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getDate(4),
                            resultSet.getDate(5),
                            resultSet.getLong(6),
                            resultSet.getLong(7)
                    );
                    observer.onNext(ride);
                }
            } catch (Exception e) {
                observer.onError(e);
            } finally {
                observer.onComplete();
            }
        });
    }

    @Override
    public Single<Ride> getRide(Long id) {
        return Single.create(observer -> {
            try (
                    Connection connection = dataSource.getConnection();
                    PreparedStatement statement = connection.prepareStatement("SELECT * FROM RIDES WHERE IDSERVICE = ?");
            ) {
                statement.setLong(1, id);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    Ride ride = new Ride(
                            resultSet.getLong(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getDate(4),
                            resultSet.getDate(5),
                            resultSet.getLong(6),
                            resultSet.getLong(7)
                    );
                    observer.onSuccess(ride);
                } else {
                    observer.onError(new IllegalArgumentException("No ride found with id: " + id));
                }
            } catch (Exception e) {
                observer.onError(e);
            }
        });
    }

    @Override
    public Single<Ride> saveRide(Ride ride) {
        return Single.create(observer -> {
            try (
                    Connection connection = dataSource.getConnection();
                    PreparedStatement statement = connection.prepareStatement("INSERT INTO RIDES(" +
                            "IDSERVICE, ADDRESS, RESPONSIBLENAME, INITRIDEDATE, DRIVERS_IDDRIVER, CALLS_IDCALL" +
                            ") VALUES(RIDES_SEQ.nextval, ?, ?, ?, ?, ?)", generatedColumns);
            ) {
                statement.setString(1, ride.getAddress());
                statement.setString(2, ride.getResponsibleName());
                statement.setDate(3, ride.getInitDate());
                statement.setLong(4, ride.getIdDriver());
                statement.setLong(5, ride.getIdCall());
                int affectedRows = statement.executeUpdate();

                if (affectedRows == 0) {
                    observer.onError(new SQLException("Creating user failed, no rows affected."));
                }
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    Long rowId = generatedKeys.getLong(1);
                    ride.setId(rowId);
                    observer.onSuccess(ride);
                } else {
                    observer.onError(new SQLException("Creating user failed, no ID obtained."));
                }
            } catch (Exception e) {
                observer.onError(e);
            }
        });
    }

    @Override
    public Single<Ride> updateRide(Ride ride) {
        return Single.create(observer -> {
            try (
                    Connection connection = dataSource.getConnection();
                    PreparedStatement statement = connection.prepareStatement("UPDATE RIDES " +
                            "SET ADDRESS = ?, RESPONSIBLENAME = ?, INITRIDEDATE = ?, ENDRIDEDATE = ?, DRIVERS_IDDRIVER = ? " +
                            "WHERE IDSERVICE = ?", generatedColumns);
            ) {

                statement.setString(1, ride.getAddress());
                statement.setString(2, ride.getResponsibleName());
                statement.setDate(3, ride.getInitDate());
                statement.setDate(4, ride.getEndDate());
                statement.setLong(5, ride.getIdDriver());
                statement.setLong(6, ride.getId());
                int affectedRows = statement.executeUpdate();
                getRide(ride.getId())
                        .subscribe((ride1, throwable) -> {
                            if (throwable != null) {
                                observer.onError(throwable);
                            } else {
                                observer.onSuccess(ride1);
                            }
                        });
            } catch (Exception e) {
                observer.onError(e);
            }
        });
    }

    @Override
    public Single<Ride> deleteRide(Ride ride) {
        return Single.create(observer -> {
            try (
                    Connection connection = dataSource.getConnection();
                    PreparedStatement statement = connection.prepareStatement("" +
                            "DELETE RIDES WHERE IDSERVICE = ?", generatedColumns);
            ) {

                statement.setLong(1, ride.getId());
                int affectedRows = statement.executeUpdate();
                observer.onSuccess(ride);
            } catch (Exception e) {
                observer.onError(e);
            }
        });
    }
}
