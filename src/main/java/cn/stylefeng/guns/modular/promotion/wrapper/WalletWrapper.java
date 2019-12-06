package cn.stylefeng.guns.modular.promotion.wrapper;

import cn.stylefeng.roses.core.base.warpper.BaseControllerWrapper;
import cn.stylefeng.roses.kernel.model.page.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;
import java.util.Map;

/**
 * 用户钱包Wrapper
 *
 * @author yaying.liu
 * @Date 2019-10-14 18:05:20
 */
public class WalletWrapper extends BaseControllerWrapper{

    public WalletWrapper(Map<String, Object> single) {
            super(single);
        }

        public WalletWrapper(List<Map<String, Object>> multi) {
            super(multi);
        }

        public WalletWrapper(Page<Map<String, Object>> page) {
            super(page);
        }

        public WalletWrapper(PageResult<Map<String, Object>> pageResult) {
            super(pageResult);
        }

        @Override
        protected void wrapTheMap(Map<String, Object> map) {

        }
}