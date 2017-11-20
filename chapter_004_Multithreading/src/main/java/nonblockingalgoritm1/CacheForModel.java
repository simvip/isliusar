package nonblockingalgoritm1;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BiFunction;


/**
 * Created by Ivan Sliusar on 16.11.2017.
 * Red Line Soft corp.
 */
@ThreadSafe
public class CacheForModel {
    /**
     * Pool
     */
    @GuardedBy("this")
    private ConcurrentMap<Integer, Model> storage;

    /**
     * Construct.
     */
    public CacheForModel() {
        this.storage = new ConcurrentHashMap<>();
    }

    /**
     * Add in to storage.
     *
     * @param value Model
     */
    public void add(Model value) {
        storage.putIfAbsent(value.hashCode(), value);
    }

    /**
     * Update value.
     *
     * @param updateValue Model
     */
    public void update(Model updateValue) {

        storage.computeIfPresent(updateValue.hashCode(), new BiFunction<Integer, Model, Model>() {
            @Override
            public Model apply(Integer integer, Model valueInStorage) {
                if (updateValue.getVersion() != valueInStorage.getVersion()) {
                    try {
                        throw new OptimisticException("Error collision! Model update other thread");
                    } catch (OptimisticException e) {
                        e.printStackTrace();
                    }
                }
                return updateValue;
            }
        });
    }

    /**
     * Delete value from storage.
     *
     * @param value Model.
     */
    public void delete(Model value) {
        storage.remove(value.hashCode());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();


        for (Integer key : storage.keySet()) {
            sb.append(storage.get(key).getName() + ";");
            sb.append(System.lineSeparator());
        }
        return "CacheForModel " + sb.toString();

    }

    private class OptimisticException extends Throwable {
        public OptimisticException(String s) {
            System.out.println(s);
        }
    }
}
