package cn.beerate.cache;

import org.junit.Test;

public class CacheTest {

    @Test
    public void testAdd(){
        Cache.init();
        Cache.add("key","123456");
        System.out.println(Cache.get("key",String.class));
    }

    @Test
    public void testAdd1(){
        Cache.init();
        Cache.add("key","123456","10s");
        try {
            Thread.sleep(9999L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("-----------"+Cache.get("key",String.class));
    }
}
