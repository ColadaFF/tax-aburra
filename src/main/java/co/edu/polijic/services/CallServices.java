package co.edu.polijic.services;

import co.edu.polijic.domain.Call;
import co.edu.polijic.domain.Driver;
import co.edu.polijic.repositories.CallsRepository;
import io.reactivex.Observable;
import io.reactivex.Single;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@ComponentScan
@Component
public class CallServices implements CallsRepository {
    private final BasicDataSource dataSource;
    private final String generatedColumns[] = {"idCall"};

    @Autowired
    public CallServices(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Observable<Call> getCalls() {
        return Observable.create(observer -> {
            try (
                    Connection connection = dataSource.getConnection();
                    PreparedStatement statement = connection.prepareStatement("SELECT * FROM CALLS");
                    ResultSet resultSet = statement.executeQuery();
            ) {
                while (resultSet.next()) {
                    Call call = new Call(
                            resultSet.getLong(1),
                            resultSet.getLong(2),
                            resultSet.getDate(3)
                    );
                    observer.onNext(call);
                }
            } catch (Exception e) {
                observer.onError(e);
            } finally {
                observer.onComplete();
            }
        });
    }

    @Override
    public Single<Call> getCall(Long id) {
        return null;
    }

    @Override
    public Single<Call> createCall(Call call) {
        return Single.create(observer -> {
            try (
                    Connection connection = dataSource.getConnection();
                    PreparedStatement statement = connection.prepareStatement("INSERT INTO CALLS(" +
                            "IDCALL, PHONENUMBER, \"date\"" +
                            ") VALUES(SYSTEM.CALLS_SEQ.nextval, ?, ?)", generatedColumns);
            ) {
                statement.setLong(1, call.getPhoneNumber());
                statement.setDate(2, call.getDate());
                int affectedRows = statement.executeUpdate();
                if (affectedRows == 0) {
                    observer.onError(new SQLException("Creating call failed, no rows affected."));
                }
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    Long rowId = generatedKeys.getLong(1);
                    call.setId(rowId);
                    observer.onSuccess(call);
                } else {
                    observer.onError(new SQLException("Creating user call, no ID obtained."));
                }
            } catch (Exception e) {
                observer.onError(e);
            }
        });
    }
}
