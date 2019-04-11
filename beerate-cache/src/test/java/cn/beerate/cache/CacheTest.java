package cn.beerate.cache;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class CacheTest {

    @Test
    public void testAdd(){
        Cache.init();
        Cache.set("key","123456");
        String key = Cache.get("key",String.class);
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

    @Test
    public void testAdd2(){
        Cache.init();

        Map<String,Object> map = new HashMap<>();
        map.put("a","a");
        map.put("b","b");
        Cache.set("map",map);
        Map map1 = Cache.get("map",Map.class);
        System.out.println(map1);
        map1.put("a","aa");
        map1.put("c","c");
        map1.remove("a");
        map1.remove("b");
        Cache.set("map",null);
        Map map2 = Cache.get("map",Map.class);
        System.out.println(map2);
    }

}
