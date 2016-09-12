package com.dw.practWeb.model;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import rx.Scheduler;
import rx.plugins.RxJavaSchedulersHook;
import rx.schedulers.Schedulers;

public class MyRxJavaSchedulersHook extends RxJavaSchedulersHook {
  private ExecutorService executor;

  @Override
  public Scheduler getComputationScheduler() {
    System.out.println("getComputationScheduler invoked");
    executor = Executors.newFixedThreadPool(10);
    return Schedulers.from(executor);
  }

  public void shutDown() {
    executor.shutdown();
  }
}
