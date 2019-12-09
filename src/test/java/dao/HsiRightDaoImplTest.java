package dao;

import bean.HsiRight;
import bean.HsiRightTest;
import dao.impl.HsiRightDaoImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by lyd on 2017-06-29.
 */
public class HsiRightDaoImplTest {
    HsiRightDao hsiRightDao=null;
    @Before
    public  void before() throws Exception {
        hsiRightDao=new HsiRightDaoImpl();
    }

    @Test
    public void testgetAllData(){
        try {
            List<HsiRight> hsiRightList=hsiRightDao.getAllData();
            System.out.println(hsiRightList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
