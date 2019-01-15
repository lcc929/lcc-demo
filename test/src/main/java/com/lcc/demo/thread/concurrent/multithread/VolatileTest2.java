package com.lcc.demo.thread.concurrent.multithread;

/**
 * @author Lcc
 * @version 2018/10/30
 *
 * volatile可见性实验2.
 */
public class VolatileTest2 {

  public static void main(String[] args) throws InterruptedException {
    VolatileTestTask task = new VolatileTestTask();
    task.start();
    Thread.sleep(1000);
    task.stopRun();
    System.out.println(Thread.currentThread().getName() + "线程结束");
  }
}

class VolatileTestTask extends Thread {

  private volatile boolean isRun = true;

  @Override
  public void run() {
    System.out.println(Thread.currentThread().getName() + "开始进入死循环");
    while (isRun()) {

    }
    System.out.println(Thread.currentThread().getName() + "线程死循环结束");
  }

  public boolean isRun() {
    return isRun;
  }

  public void stopRun() {
    System.out.println(Thread.currentThread().getName() + "修改运行状态为暂停");
    this.isRun = false;
  }
}
