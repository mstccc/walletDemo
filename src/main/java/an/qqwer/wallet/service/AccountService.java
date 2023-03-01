package an.qqwer.wallet.service;


import an.qqwer.wallet.bean.Account;

public interface AccountService {

    /**
     * 查询用户钱包：根据用户id查询。
     * @param userID 用户id
     * @return  钱包对象
     */
    Account queryActByUserId(Integer userID);

    /**
     *  更新钱包余额
     * @param actId 钱包ID
     * @param money 更新后的余额
     * @return
     */
    int updateActMoneyByActID(Integer actId, Integer money);
}
