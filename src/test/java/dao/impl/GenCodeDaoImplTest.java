package dao.impl;

import bean.SystemData;
import bean.gencode.TableInfo;
import dao.DaoFactory;
import dao.GenCodeDao;
import org.junit.Before;
import org.junit.Test;

import javax.sound.midi.Soundbank;
import java.util.List;

import static org.junit.Assert.*;

/**
 * 功能说明:
 * 系统版本:
 * @author: lyd
 * 开发时间: 2018-09-30
 */
public class GenCodeDaoImplTest {
    GenCodeDao dao;
    @Before
    public void init(){
         dao= DaoFactory.getGenCodeDao();
        try {
            SystemData.init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getTableInfo() {
        try {
            TableInfo tableInfo=dao.getTableInfo("t_bd_currencyrates");
            System.out.println(tableInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getTables() {
        try {
            List<TableInfo> tableInfoList=dao.getTables();
            for (TableInfo tableInfo : tableInfoList){
                System.out.println(tableInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}