package org.rxjava.operator.compose;


import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ignoreElement {
    public static void main(String[] args) {
        Observable.just(1,2,3)
                // 此操作符会过滤onNext，只留下onComplete和onError
                .ignoreElements()
                // 🦐beta操作
                .andThen(Observable.just("a", "b", "c"))
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        System.out.println("complete");
                    }
                });
    }
}
