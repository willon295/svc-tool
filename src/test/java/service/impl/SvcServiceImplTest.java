package service.impl;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * 功能说明: <br>
 * 系统版本: 2.3.7.0 <br>
 * 开发人员: lyd
 * 开发时间: 2018-02-09<br>
 * <br>
 */
public class SvcServiceImplTest {
   SvcServiceImpl svcService=new SvcServiceImpl();

    @Test
    public void findSqlField() {
        svcService.findSqlField("UC_SE_BATCHMASSPAY_QUERY");
    }
}