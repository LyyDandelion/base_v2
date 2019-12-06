package cn.stylefeng.guns.modular.promotion.wrapper;

import cn.stylefeng.roses.core.base.warpper.BaseControllerWrapper;
import cn.stylefeng.roses.kernel.model.page.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;
import java.util.Map;

/**
 * 现金流Wrapper
 *
 * @author yaying.liu
 * @Date 2019-10-15 11:12:37
 */
public class CashflowWrapper extends BaseControllerWrapper{

    public CashflowWrapper(Map<String, Object> single) {
            super(single);
        }

        public CashflowWrapper(List<Map<String, Object>> multi) {
            super(multi);
        }

        public CashflowWrapper(Page<Map<String, Object>> page) {
            super(page);
        }

        public CashflowWrapper(PageResult<Map<String, Object>> pageResult) {
            super(pageResult);
        }

        @Override
        protected void wrapTheMap(Map<String, Object> map) {

        }
}