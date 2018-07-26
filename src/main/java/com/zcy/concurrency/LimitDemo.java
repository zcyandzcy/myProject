package com.zcy.concurrency;


import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author zcy
 * @apiNote 单机下的高并发下的限流处理
 */
public class LimitDemo {

    /**
     * 限流神器：Guava RateLimiter
     * <p>
     * Guava不仅仅在集合、缓存、异步回调等方面功能强大，而且还给我们封装好了限流的API！
     * <p>
     * Guava RateLimiter基于令牌桶算法，我们只需要告诉RateLimiter系统限制的QPS是多少，
     * 那么RateLimiter将以这个速度往桶里面放入令牌，然后请求的时候，
     * 通过tryAcquire()方法向RateLimiter获取许可（令牌）。
     */


    public static ConcurrentHashMap<String, RateLimiter> resourceRateLimiter = new ConcurrentHashMap<>();

    static {
        createResourceLimiter("order", 50);
    }

    public static void createResourceLimiter(String resource, double qps) {
        if (resourceRateLimiter.contains(resource)) {
            resourceRateLimiter.get(resource).setRate(qps);
        } else {
            RateLimiter rateLimiter = RateLimiter.create(qps);
            resourceRateLimiter.putIfAbsent(resource, rateLimiter);
        }
    }


    public static void main(String[] args) {
        for (int i = 0; i < 5000; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if (resourceRateLimiter.get("order").tryAcquire(10, TimeUnit.MILLISECONDS)) {
                        System.out.println("执行业务逻辑");
                    } else {
                        System.out.println("限流");
                    }
                }
            }).start();
        }
    }


}
