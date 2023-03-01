package cn.qqwer.wallet.controller;

import an.qqwer.wallet.application;
import an.qqwer.wallet.bean.Account;
import an.qqwer.wallet.LogUtil;
import an.qqwer.wallet.controller.MainController;
import an.qqwer.wallet.service.AccountService;
import an.qqwer.wallet.service.CommodityService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;

@SpringBootTest(classes = application.class)
public class MainControllerTest {
    @Autowired
    AccountService accountService;

    @Autowired
    MainController mainController;

    @Autowired
    CommodityService commodityService;

    @Test
    public void test1(){
        System.out.println(commodityService.queryCommodityByID(2002));
    }
    @Test
    public void test2(){
        final Account account = accountService.queryActByUserId(2002);
        System.out.println(account);
    }

    @Test
    public void shoppingTest(){
        String result = mainController.shopping(1001, 2001, 2);
        System.out.println(result);
    }

    @Test
    public void queryMoneyDetailed() throws IOException {
        LogUtil.appendLog("1001", "购买商品", 100, 4900);
        LogUtil.appendLog("1001", "购买商品", 50, 4850);
        LogUtil.appendLog("1001", "购买商品", 150, 4700);
        LogUtil.appendLog("1001", "购买商品", 150, 4500);

//        File logFile = LogUtil.getLogFile("1001");
//
//        FileInputStream fis = new FileInputStream(logFile);
//        InputStreamReader isr = new InputStreamReader(fis);
//        BufferedReader br = new BufferedReader(isr);
//
//        StringBuilder sb = new StringBuilder();
//        String str = null;
//        while ((str = br.readLine()) != null){
//            sb.append(str);
//            sb.append("\n");
//        }
//        System.out.println(sb);
//
//        br.close();
//        isr.close();
//        fis.close();
    }
}
