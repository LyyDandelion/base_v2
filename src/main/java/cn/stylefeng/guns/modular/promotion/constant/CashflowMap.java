package cn.stylefeng.guns.modular.promotion.constant;
import cn.stylefeng.guns.core.common.constant.dictmap.base.AbstractDictMap;

/**
 * 现金流Map
 *
 * @author yaying.liu
 * @Date 2019-10-15 11:12:37
 */
public class CashflowMap extends AbstractDictMap {

   @Override
      public void init() {
          put("cashflowId","现金流id");
      }

      @Override
      protected void initBeWrapped() {

      }
}