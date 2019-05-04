package rxjava.backpressure;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import rxjava.common.MyUtils;

/**
 * 没有背压，随着事件的推移，导致OOM
 */
public class OOMTest {
    public static void main(String[] args) {
        OOMTest client = new OOMTest();
        client.testOOM();
    }

    public void testOOM() {
        Observable.create(emitter -> {
            for (int i = 0;; ++i) {
                emitter.onNext(i);
            }
        }).observeOn(Schedulers.newThread())
                .subscribe(o -> {
            MyUtils.sleep(1000);
        });
    }
}
