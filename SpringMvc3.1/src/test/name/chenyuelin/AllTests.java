package test.name.chenyuelin;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test.name.chenyuelin.controller.TControllerTestCase;
import test.name.chenyuelin.service.TServiceTestCase;

@RunWith(Suite.class)
@SuiteClasses({TServiceTestCase.class,
  TServiceTestCase.class,
  TControllerTestCase.class})
public class AllTests {

}
