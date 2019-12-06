package cn.stylefeng.guns.modular.promotion.constant;
import cn.stylefeng.guns.core.common.constant.dictmap.base.AbstractDictMap;

/**
 * 用户钱包Map
 *
 * @author yaying.liu
 * @Date 2019-10-14 18:05:20
 */
public class WalletMap extends AbstractDictMap {

   @Override
      public void init() {
          put("walletId","用户钱包id");
      }

      @Override
      protected void initBeWrapped() {

      }
}