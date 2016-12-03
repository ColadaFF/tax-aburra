package co.edu.polijic.repositories;

import co.edu.polijic.domain.Call;
import io.reactivex.Observable;
import io.reactivex.Single;

public interface CallsRepository {
    Observable<Call> getCalls();
    Single<Call> getCall(Long id);
    Single<Call> createCall(Call call);
}
