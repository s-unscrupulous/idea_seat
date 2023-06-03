package service.impl;

import service.ScheduledService;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;


public class ScheduledServiceImpl implements ScheduledService {
    /**
     * 唯一timer
     */
    private Timer timer;

    private int delay;

    public ActionListener listener;

    /**
     * 定时计数的线程池
     */
    private ScheduledExecutorService service;

    public ScheduledExecutorService getService() {
        if (service == null || service.isShutdown()) {
            service = Executors.newSingleThreadScheduledExecutor();
        }
        return service;
    }

    public void setService(ScheduledExecutorService service) {
        this.service = service;
    }

    private Timer getTimer() {
        return timer;
    }

    private void setTimer(Timer timer) {
        this.timer = timer;
    }

    private ScheduledServiceImpl() {
        this.timer = createEmptyTask();
    }

    private static class ScheduledServiceHolder {
        private static final ScheduledServiceImpl INSTANCE = new ScheduledServiceImpl();
    }

    /**
     * instance
     *
     * @return {@link ScheduledServiceImpl}
     */
    public static ScheduledServiceImpl getInstance() {
        return ScheduledServiceHolder.INSTANCE;
    }

    /**
     * 创建一个空任务
     *
     * @return empty task timer
     */
    private Timer createEmptyTask() {
        return new Timer(0, null);
    }

    /**
     * 添加任务
     *
     * @param delay    延迟以及周期
     * @param listener 事件
     */
    @Override
    public void addTask(int delay, ActionListener listener) {
        this.listener = listener;
        this.delay = delay;
        synchronized (getInstance().getTimer()) {
            if (isTaskRunning()) {
                removeTask();
            }
            getInstance().setTimer(new Timer(delay, listener));
        }
    }

    /**
     * 延迟任务
     */
    @Override
    public void addTask(int delayTime, boolean flag) {
        if (flag)
            getInstance().setTimer(new Timer(delayTime + delay, listener));
        else
            getInstance().setTimer(new Timer(delayTime, listener));
        this.start();
    }

    /**
     * 移除任务
     */
    @Override
    public void removeTask() {
        synchronized (getInstance().getTimer()) {
            //停止任务
            stop();
            //置空
            getInstance().setTimer(createEmptyTask());
        }
    }

    /**
     * 是否有定时任务正在执行
     *
     * @return 是/否
     */
    @Override
    public boolean isTaskRunning() {
        return !isEmptyTaskTimer() && getInstance().getTimer().isRunning();
    }

    /**
     * 判断是否是空任务定时器
     *
     * @return 是/否
     */
    @Override
    public boolean isEmptyTaskTimer() {
        return getInstance().getTimer().getDelay() == 0
                && (getInstance().getTimer().getActionListeners() == null
                || getInstance().getTimer().getActionListeners().length == 0);
    }

    /**
     * 开始执行任务
     */
    @Override
    public void start() {
        getInstance().getTimer().start();
    }

    /**
     * 停止任务
     */
    @Override
    public void stop() {
        getInstance().getTimer().stop();
    }

    /**
     * 延迟执行任务
     */
    @Override
    public void delay(int delayTime) {
        getInstance().getTimer().setDelay(delayTime);
    }
}
