package cn.beerate.cache;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.Serializable;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The Cache. Mainly an interface to memcached or EhCache.
 * <p>
 * Note: When specifying expiration == "0s" (zero seconds) the actual expiration-time may vary between different cache implementations
 */
public class Cache{


    private static final Log logger = LogFactory.getLog(EhCacheImpl.class);

    /**
     * The underlying cache implementation
     */
    private static ICache cacheImpl;

    /**
     * Add an element only if it doesn't exist.
     *
     * @param key        Element key
     * @param value      Element value
     * @param expiration Ex: 10s, 3mn, 8h
     */
    public static void add(String key, Object value, String expiration) {
        checkSerializable(value);
        cacheImpl.add(key, value, parseDuration(expiration));
    }

    /**
     * Add an element only if it doesn't exist, and return only when
     * the element is effectively cached.
     *
     * @param key        Element key
     * @param value      Element value
     * @param expiration Ex: 10s, 3mn, 8h
     * @return If the element an eventually been cached
     */
    public static boolean safeAdd(String key, Object value, String expiration) {
        checkSerializable(value);
        return cacheImpl.safeAdd(key, value, parseDuration(expiration));
    }

    /**
     * Add an element only if it doesn't exist and store it indefinitely.
     *
     * @param key   Element key
     * @param value Element value
     */
    public static void add(String key, Object value) {
        checkSerializable(value);
        cacheImpl.add(key, value, parseDuration(null));
    }

    /**
     * Set an element.
     *
     * @param key        Element key
     * @param value      Element value
     * @param expiration Ex: 10s, 3mn, 8h
     */
    public static void set(String key, Object value, String expiration) {
        checkSerializable(value);
        cacheImpl.set(key, value, parseDuration(expiration));
    }

    /**
     * Set an element and return only when the element is effectively cached.
     *
     * @param key        Element key
     * @param value      Element value
     * @param expiration Ex: 10s, 3mn, 8h
     * @return If the element an eventually been cached
     */
    public static boolean safeSet(String key, Object value, String expiration) {
        checkSerializable(value);
        return cacheImpl.safeSet(key, value, parseDuration(expiration));
    }

    /**
     * Set an element and store it indefinitely.
     *
     * @param key   Element key
     * @param value Element value
     */
    public static void set(String key, Object value) {
        checkSerializable(value);
        cacheImpl.set(key, value, parseDuration(null));
    }

    /**
     * Replace an element only if it already exists.
     *
     * @param key        Element key
     * @param value      Element value
     * @param expiration Ex: 10s, 3mn, 8h
     */
    public static void replace(String key, Object value, String expiration) {
        checkSerializable(value);
        cacheImpl.replace(key, value, parseDuration(expiration));
    }

    /**
     * Replace an element only if it already exists and return only when the
     * element is effectively cached.
     *
     * @param key        Element key
     * @param value      Element value
     * @param expiration Ex: 10s, 3mn, 8h
     * @return If the element an eventually been cached
     */
    public static boolean safeReplace(String key, Object value, String expiration) {
        checkSerializable(value);
        return cacheImpl.safeReplace(key, value, parseDuration(expiration));
    }

    /**
     * Replace an element only if it already exists and store it indefinitely.
     *
     * @param key   Element key
     * @param value Element value
     */
    public static void replace(String key, Object value) {
        checkSerializable(value);
        cacheImpl.replace(key, value, parseDuration(null));
    }

    /**
     * Increment the element value (must be a Number).
     *
     * @param key Element key
     * @param by  The incr value
     * @return The new value
     */
    public static long incr(String key, int by) {
        return cacheImpl.incr(key, by);
    }

    /**
     * Increment the element value (must be a Number) by 1.
     *
     * @param key Element key
     * @return The new value
     */
    public static long incr(String key) {
        return cacheImpl.incr(key, 1);
    }

    /**
     * Decrement the element value (must be a Number).
     *
     * @param key Element key
     * @param by  The decr value
     * @return The new value
     */
    public static long decr(String key, int by) {
        return cacheImpl.decr(key, by);
    }

    /**
     * Decrement the element value (must be a Number) by 1.
     *
     * @param key Element key
     * @return The new value
     */
    public static long decr(String key) {
        return cacheImpl.decr(key, 1);
    }

    /**
     * Retrieve an object.
     *
     * @param key The element key
     * @return The element value or null
     */
    public static Object get(String key) {
        return cacheImpl.get(key);
    }

    /**
     * Bulk retrieve.
     *
     * @param key List of keys
     * @return Map of keys & values
     */
    public static Map<String, Object> get(String... key) {
        return cacheImpl.get(key);
    }

    /**
     * Delete an element from the cache.
     *
     * @param key The element key
     */
    public static void delete(String key) {
        cacheImpl.delete(key);
    }

    /**
     * Delete an element from the cache and return only when the
     * element is effectively removed.
     *
     * @param key The element key
     * @return If the element an eventually been deleted
     */
    public static boolean safeDelete(String key) {
        return cacheImpl.safeDelete(key);
    }

    /**
     * Clear all data from cache.
     */
    public static void clear() {
        cacheImpl.clear();
    }

    /**
     * Convenient clazz to get a value a class type;
     *
     * @param <T>   The needed type
     * @param key   The element key
     * @param clazz The type class
     * @return The element value or null
     */
    @SuppressWarnings("unchecked")
    public static <T> T get(String key, Class<T> clazz) {
        return (T) cacheImpl.get(key);
    }

    /**
     * Initialize the cache system.
     */
    public synchronized static void init() {
        if(cacheImpl==null){
            logger.info("initialize the cache system");
            cacheImpl = EhCacheImpl.newInstance();
            return;
        }

        logger.info(" the cache system was initialized");
    }

    /**
     * Stop the cache system.
     */
    public static void stop() {
        cacheImpl.stop();
    }

    /**
     * Utility that check that an object is serializable.
     */
    private static void checkSerializable(Object value) {
        if (value != null && !(value instanceof Serializable)) {
            throw new NullPointerException("Cannot cache a non-serializable value of type " + value.getClass().getName());
        }
    }

    /**
     * Parse a duration
     *
     * @param duration 3h, 2mn, 7s
     * @return The number of seconds
     */
    private static int parseDuration(String duration) {

        Pattern days = Pattern.compile("^([0-9]+)d$");
        Pattern hours = Pattern.compile("^([0-9]+)h$");
        Pattern minutes = Pattern.compile("^([0-9]+)mi?n$");
        Pattern seconds = Pattern.compile("^([0-9]+)s$");

        if (duration == null) {
            return 60 * 60 * 24 * 30;
        }
        int toAdd = -1;
        if (days.matcher(duration).matches()) {
            Matcher matcher = days.matcher(duration);
            matcher.matches();
            toAdd = Integer.parseInt(matcher.group(1)) * (60 * 60) * 24;
        } else if (hours.matcher(duration).matches()) {
            Matcher matcher = hours.matcher(duration);
            matcher.matches();
            toAdd = Integer.parseInt(matcher.group(1)) * (60 * 60);
        } else if (minutes.matcher(duration).matches()) {
            Matcher matcher = minutes.matcher(duration);
            matcher.matches();
            toAdd = Integer.parseInt(matcher.group(1)) * (60);
        } else if (seconds.matcher(duration).matches()) {
            Matcher matcher = seconds.matcher(duration);
            matcher.matches();
            toAdd = Integer.parseInt(matcher.group(1));
        }
        if (toAdd == -1) {
            throw new IllegalArgumentException("Invalid duration pattern : " + duration);
        }
        return toAdd;
    }

}
