package an.qqwer.wallet.controller;

import an.qqwer.wallet.bean.Account;
import an.qqwer.wallet.bean.Commodity;
import an.qqwer.wallet.LogUtil;
import an.qqwer.wallet.service.AccountService;
import an.qqwer.wallet.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;

@RestController
public class MainController {

    @Autowired
    AccountService accountService;

    @Autowired
    CommodityService commodityService;

    /**
     * 根据用户id查询账户余额
     * @param userID
     * @return
     */
    @RequestMapping(value = "/queryMoney", method = RequestMethod.GET)
    public Account queryMoney(Integer userID){
        Account account = accountService.queryActByUserId(userID);
        return account;
    }

    /**
     * 2、用户消费100元的接口
     * @param userID 用户ID
     * @param itemID 用户消费的商品的ID
     * @return
     */
    @RequestMapping(value = "/shopping", method = RequestMethod.GET)
    public String shopping(Integer userID, Integer itemID, Integer count){
        // 商品数量校验
        if (count < 1 && count > 999) return "商品数量错误！";

        Commodity commodity = commodityService.queryCommodityByID(itemID);
        Account account = accountService.queryActByUserId(userID);

        // 条件判断
        // 判断库存是否足够，若不够，则直接响应浏览器
        if (commodity.getCount() < count) return "购买失败，商品库存不足！";

        // 判断余额是否足够，不够则直接响应浏览器
        int total = commodity.getMoney() * count;
        if (account.getMoney() < total) return "购买失败，余额不足！";

        // 余额满足，商品数量满足，开始下单
        int res = commodityService.consumerCommodity(commodity, account, count);
        if (res == 1){
            // 余额流水记录
            LogUtil.appendLog(String.valueOf(userID),"购买商品:", total, account.getMoney() - total);
            return "下单成功！";
        }
        return "失败，请重试！";
    }

    /**
     * 3、用户退款20元接口
     */
    @RequestMapping(value = "/backMoney", method = RequestMethod.GET)
    public String backMoney(Integer userID, Integer backMoney){

        Account account = accountService.queryActByUserId(userID);
        if (account == null) return "操作失败！";

        // 判断用户余额是否足够
        if (account.getMoney() > backMoney){
            int res = accountService.updateActMoneyByActID(account.getId(), account.getMoney() + backMoney);
            if (res == 1){
                LogUtil.appendLog(String.valueOf(userID), "申请退款", backMoney, account.getMoney() + backMoney);
                return "退款申请成功！";
            }
        }
        return "操作失败！";
    }

    /**
     * 4、查询用户钱包金额变动明细的接口
     *      余额变动明细这里使用文件的方式保存，因为如果存进MySql明显不合适。
     * @param userID 用户id
     * @return 返回字符串
     */
    @RequestMapping(value = "/queryMoneyDetailed", method = RequestMethod.GET)
    public String queryMoneyDetailed(String userID){
        File logFile = LogUtil.getLogFile(userID);
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        try {
            fis = new FileInputStream(logFile);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);

            String str = null;
            while ((str = br.readLine()) != null){
                sb.append(str);
                sb.append("\n");
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                br.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(sb);
        return sb.toString();
    }
}
