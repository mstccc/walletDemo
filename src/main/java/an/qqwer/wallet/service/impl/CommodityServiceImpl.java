package an.qqwer.wallet.service.impl;

import an.qqwer.wallet.bean.Account;
import an.qqwer.wallet.bean.Commodity;
import an.qqwer.wallet.mapper.AccountMapper;
import an.qqwer.wallet.mapper.CommodityMapper;
import an.qqwer.wallet.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommodityServiceImpl implements CommodityService {
    @Autowired
    CommodityMapper commodityMapper;

    @Autowired
    AccountMapper accountMapper;

    @Override
    public Commodity queryCommodityByID(Integer id) {
        return commodityMapper.selectCommodityByID(id);
    }

    @Override
    public int updateComCount(Integer id, Integer count) {
        return commodityMapper.updateComCount(id, count);
    }

    @Transactional
    @Override
    public int consumerCommodity(Commodity commodity, Account account, Integer count) {
        // 更新商品库存
        commodityMapper.updateComCount(commodity.getId(), commodity.getCount() - count);
        // 商品总价
        int total = commodity.getMoney() * count;
        // 更新钱包余额
        accountMapper.updateActMoneyByActID(account.getId(), account.getMoney() - total);

        return 1;
    }
}
