
package bitcamp.java89.ems2.dao;

import java.util.ArrayList;

import bitcamp.java89.ems2.domain.Manager;

public interface ManagerDao {
  public boolean exist(int memberNo) throws Exception;
  ArrayList<Manager> getList() throws Exception;
}