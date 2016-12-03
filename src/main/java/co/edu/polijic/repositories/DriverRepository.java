package co.edu.polijic.repositories;

import co.edu.polijic.domain.Driver;
import io.reactivex.Observable;

public interface DriverRepository {
    Observable<Driver> getDrivers();
}
