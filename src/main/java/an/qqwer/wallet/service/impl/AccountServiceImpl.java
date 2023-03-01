package an.qqwer.wallet.service.impl;

import an.qqwer.wallet.bean.Account;
import an.qqwer.wallet.mapper.AccountMapper;
import an.qqwer.wallet.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountMapper accountMapper;

    @Override
    public Account queryActByUserId(Integer userID) {
        return accountMapper.queryActByUserId(userID);
    }

    // 更新钱包余额
    @Transactional
    @Override
    public int updateActMoneyByActID(Integer actId, Integer money) {
        return accountMapper.updateActMoneyByActID(actId, money);
    }

}
