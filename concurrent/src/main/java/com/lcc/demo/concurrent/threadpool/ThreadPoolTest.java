package com.lcc.demo.concurrent.threadpool;

import java.util.concurrent.Executor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Lcc
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/10/26
 */
@Component
public class ThreadPoolTest {

  @Autowired
  private Executor executor;


}
