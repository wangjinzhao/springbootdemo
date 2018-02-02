package com.wangjinzhao.springbootdemo.monitor;


import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadMonitor {
    static Logger logger = LoggerFactory.getLogger("monitor");
    private static Server server = null;

    public static void setServer(Server server) {
        ThreadMonitor.server = server;
    }

    public static void init() throws Exception {
        logger.info("start jetty thread monitor...");
        new Thread(new Runnable() {
            @SuppressWarnings("unchecked")
            public void run() {
                for (; ; ) {
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                    }
                    try {
                        if (server == null || server.getThreadPool() == null)
                            continue;
                        final QueuedThreadPool pool = (QueuedThreadPool) server.getThreadPool();
                        int threads = pool.getThreads();
                        int idle = pool.getIdleThreads();
                        int queueSize = pool.getQueueSize();
                        int maxThreads = pool.getMaxThreads();
                        int work = threads - idle;
                        logger.info("monitor jetty threads created:{},work:{},idle:{},queueSize:{},maxThreads:{}.", threads,
                                work, idle, queueSize, maxThreads);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}



