package luxoft;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.FutureTask;

public class Top1000Test {

    @Test
    public void test10() {
        Top1000 top3 = new Top1000();
        top3.onEvent(1);
        top3.onEvent(3);
        top3.onEvent(2);

        Assertions.assertTrue(top3.getTop().equals(List.of(1, 2, 3)));
    }

    static class Worker implements Runnable {
        Top1000 top3;
        public Worker(Top1000 top1000) {
            this.top3 = top1000;
        }

        @Override
        public void run() {
            System.out.println("Start filling");
            top3.onEvent(1);
            top3.onEvent(2);

            try {
                System.out.println("Fill delay");
                Thread.sleep(1000);
            } catch (InterruptedException runtime) {
                throw new RuntimeException(runtime);
            }

            for (int index = 3; index <= 3; index++) {
                top3.onEvent(index);
            }
            System.out.println("Filling is completed");
        }
    }

    @Test
    public void test10WithDelay() throws InterruptedException {
        Top1000 top3 = new Top1000();
        // fill top1000
        Thread fillT = new Thread(new Worker(top3));
        fillT.start();

        Thread waitToFillTop1000 = new Thread(top3);
        waitToFillTop1000.start();

        waitToFillTop1000.join();

        Assertions.assertTrue(top3.getTop().equals(List.of(1, 2, 3)));
    }
}
