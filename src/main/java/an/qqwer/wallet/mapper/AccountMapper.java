package an.qqwer.wallet.mapper;

import an.qqwer.wallet.bean.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AccountMapper {
    /**
     * 查询用户钱包：根据用户id查询。
     * @param userID 用户ID
     * @return  返回用户的账户对象，即钱包
     */
    Account queryActByUserId(@Param("userID") int userID);

    /**
     *  更新钱包余额
     * @param actId 钱包ID
     * @param money 更新后的余额
     * @return
     */
    int updateActMoneyByActID(@Param("actId") Integer actId, @Param("money") Integer money);

}
