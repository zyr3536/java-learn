package eventbus;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.Executors;

/**
 * 东西是好东西，但是已经被guava标记成unstable了，应该就是存在一点问题的，项目里面最好不要用
 */
public class Client {
    public static void main(String[] args) throws Exception {
        Client client = new Client();
//        client.simpleTest();
        client.asyncTest();
    }

    public void simpleTest() throws Exception {
      EventBus eventBus = new EventBus();
      EventListener listener = new EventListener();
      eventBus.register(listener);
      eventBus.post(new MessageEvent1("message 1 : 1"));
      eventBus.post(new MessageEvent2("message 2 : 1"));
      eventBus.post(new MessageEvent2("message 1 : 2"));
      eventBus.post(new Object());
      Thread.sleep(10000);
    }

    public void asyncTest() throws Exception {
        AsyncEventBus eventBus = new AsyncEventBus(Executors.newFixedThreadPool(2));
        EventListener listener = new EventListener();
        eventBus.register(listener);
        eventBus.post(new MessageEvent1("message 1 : 1"));
        eventBus.post(new MessageEvent2("message 2 : 1"));
        eventBus.post(new MessageEvent2("message 1 : 2"));
        eventBus.post(new Object());
        Thread.sleep(10000);
    }

    /**
     * 具体事件类1
     */
    @Getter
    @Setter
    @AllArgsConstructor
    public static class MessageEvent1 {
        private String message;
    }

    /**
     * 具体事件类2
     */
    @Getter
    @Setter
    @AllArgsConstructor
    public static class MessageEvent2 {
        private String message;
    }

    public static class EventListener {
        @Subscribe
        public void listen(MessageEvent1 event1) {
            System.out.println(Thread.currentThread().getName());
            System.out.println(event1.getMessage());
        }
        @Subscribe
        public void listen(MessageEvent2 event2) {
            System.out.println(Thread.currentThread().getName());
            System.out.println(event2.getMessage());
        }

        @Subscribe
        public void listen(DeadEvent deadEvent) {
            System.out.println(Thread.currentThread().getName());
            System.out.println("dead event!!!");
        }
    }
}
