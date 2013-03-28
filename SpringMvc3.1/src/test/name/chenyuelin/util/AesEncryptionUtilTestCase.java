package test.name.chenyuelin.util;

import static org.junit.Assert.*;

import name.chenyuelin.util.AppUtil;

import org.junit.Test;

public class AesEncryptionUtilTestCase {

  @Test
  public void test() {
    System.out.println(AppUtil.encryptTextToBase64("root"));
  }

}
