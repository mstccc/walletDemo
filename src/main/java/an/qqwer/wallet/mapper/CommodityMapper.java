package an.qqwer.wallet.mapper;

import an.qqwer.wallet.bean.Commodity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Configuration;

@Configuration
@Mapper
public interface CommodityMapper {

    /**
     * 根据商品id查询商品信息
     * @param id
     * @return  返回商品对象
     */
    Commodity selectCommodityByID(Integer id);

    /**
     * 更新商品数量
     * @param id 商品的id
     * @param count 更新后的数量
     * @return
     */
    int updateComCount(@Param("id") Integer id, @Param("count") Integer count);

}
