package com.lcc.demo.thread.test;

/**
 * @author lcc
 * @version 2018/12/14
 */
public class ThreadWaitAndNotifyTest {

  public static void main(String[] args) throws InterruptedException {

    Thread thread1 = new Thread(new Job(), "thread1");
    Thread thread2 = new Thread(new Job(), "thread2");

    System.out.println("新建线程：thread1---" + thread1.getState());
    System.out.println("新建线程：thread2---" + thread2.getState());

    thread1.start();
    thread2.start();

    System.out.println("启动线程：thread1---" + thread1.getState());
    System.out.println("启动线程：thread2---" + thread2.getState());

    thread1.join();
    thread2.join();
  }

  private static class Job implements Runnable {

    @Override
    public synchronized void run() {
      try {
        while (true) {
          System.out.println("当前执行线程：" + Thread.currentThread().getName());
          if ("thread1".equals(Thread.currentThread().getName())) {
            Thread.sleep(1000);
            System.out.println("线程1开始等待");
            this.wait();
            System.out.println("线程1状态：" + Thread.currentThread().getState());
          } else {
            Thread.sleep(1000);
            System.out.println("线程2开始通知线程1醒来");
            this.notifyAll();
          }
        }
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

//  public static void main(String[] args) throws InterruptedException {
//    Car car = new Car();
//    ThreadPoolExecutor pool = LccThreadPool.getTheadPool();
//    pool.execute(new WaxOff(car));
//    pool.execute(new WaxOn(car));
//    Thread.sleep(5000);
//    LccThreadPool.shutDown();
//  }
}

class Car {

  private boolean waxOn = false;

  public synchronized void waxed() {
    waxOn = true;
    notify();
  }

  public synchronized void buffed() {
    waxOn = false;
    notify();
  }

  public synchronized void waitForWaxing() throws InterruptedException {
    while (!waxOn) {
      wait();
    }
  }

  public synchronized void waitForBuffing() throws InterruptedException {
    while (waxOn) {
      wait();
    }
  }
}

class WaxOn implements Runnable {

  private Car car;

  public WaxOn(Car car) {
    this.car = car;
  }

  @Override
  public void run() {
    while (!Thread.interrupted()) {
      try {
        System.out.println("正在涂蜡");
        Thread.sleep(2000);
        car.waxed();
        car.waitForBuffing();
      } catch (InterruptedException e) {
        System.out.println("涂蜡任务结束");
        return;
      }
      System.out.println("涂蜡完毕");
    }
  }
}

class WaxOff implements Runnable {

  private Car car;

  public WaxOff(Car car) {
    this.car = car;
  }

  @Override
  public void run() {
    while (!Thread.interrupted()) {
      try {
        car.waitForWaxing();
        System.out.println("正在抛光");
        Thread.sleep(2000);
        car.buffed();
      } catch (InterruptedException e) {
        System.out.println("抛光任务结束");
        return;
      }
      System.out.println("抛光完毕");
    }
  }
}
