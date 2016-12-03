package co.edu.polijic.repositories;

import co.edu.polijic.domain.Ride;
import io.reactivex.Observable;
import io.reactivex.Single;

public interface RidesRepository {
    Observable<Ride> listRides(int limit, int page);
    Single<Ride> getRide(Long id);
    Single<Ride> saveRide(Ride ride);
    Single<Ride> updateRide(Ride ride);
    Single<Ride> deleteRide(Ride ride);
}
