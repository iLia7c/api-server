package luxoft;

import java.util.*;
import java.util.concurrent.*;

import static java.util.concurrent.Executors.newFixedThreadPool;

/**
 * Service which returns top (with maximum value) 1000 elements from endless data source of incoming elements.
 * <p>
 * P.S. Declarations of methods could be change accordingly without changing their names.
 */
public class Top1000<T extends Comparable> implements Runnable {

    private static final int MAX_ELEMENTS = 3;

    // to store top 1000
    // endless storage to store everything

    List<T> top1000Storage;
    PriorityQueue<T> top1000;
    TreeSet<T> top1000TreeSet;

    /**
     * Would be called in case of incoming element. One incoming element would be passed as argument
     */ {
        // bring up top 1000 from storage
        // initialize top 1000 storage
        top1000Storage = new CopyOnWriteArrayList<>();
        top1000 = new PriorityQueue<>(1000);
        top1000TreeSet = new TreeSet<>();
     }

     public void run() {
         System.out.println("Endless wait to fill storage");
         while(top1000Storage.size() < MAX_ELEMENTS) {
             try {
                 Thread.sleep(1000);
             } catch (InterruptedException runtime) {
                 throw new RuntimeException();
             }
         }
         System.out.println("Top storage is filled");
     }

    public void onEvent(T value) {
        // record to endless
        tryToPutToTop1000(value);
    }

    private void tryToPutToTop1000(T value) {
        top1000.add(value);

        if (top1000.size() > 1000) {
            top1000.poll();
        }

        top1000TreeSet.clear();
        top1000TreeSet.addAll(top1000);
    }

    /**
     * try to put to top 1000 in ascending order
     * NOW -> O(n)
     * binary search O(log2N)
     * @param value
     */
    private void tryToPutToTop1000_2(T value) {
        // replace with binary
        int index = top1000Storage.size() - 1;

        /* binary search start
        for (; index >= 0; index--) {
            // to insert in increasing order
            if (top1000Storage.get(index).compareTo(value) <= 0) {
                break;
            }
        }*/

        if (index >= 0) {
            top1000Storage.add(index + 1, value);

        }
        if (top1000Storage.size() > MAX_ELEMENTS) {
            top1000Storage.remove(0);
        }
        // first iteration when storage is empty
        if (index < 0 && top1000Storage.size() < MAX_ELEMENTS) {
            top1000Storage.add(0, value);
        }
    }

    /**
     * Returns top (with maximum value) 1000 elements. Could be called anytime.
     * Elements should be in ascending order
     * return copy
     */
    public List<T> getTop2() {

        return List.copyOf(top1000Storage);
    }

    public List<T> getTop() {
        return new ArrayList<>(top1000TreeSet);
    }
}