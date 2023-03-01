package an.qqwer.wallet.service;

import an.qqwer.wallet.bean.Account;
import an.qqwer.wallet.bean.Commodity;
import org.apache.ibatis.annotations.Param;

public interface CommodityService {

    /**
     * 根据商品id查询商品信息
     * @param id
     * @return
     */
    Commodity queryCommodityByID(Integer id);

    /**
     * 更新商品数量
     * @param id 商品的id
     * @param count 更新后的数量
     * @return
     */
    int updateComCount(@Param("id") Integer id, @Param("count") Integer count);

    /**
     * 消费商品
      * @param commodity   商品
     * @param account     钱包
     * @param count     消费数量
     * @return
     */
    int consumerCommodity(Commodity commodity, Account account, Integer count);

}
