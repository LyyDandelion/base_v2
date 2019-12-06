package cn.stylefeng.guns.modular.com.wrapper;
import cn.stylefeng.roses.core.base.warpper.BaseControllerWrapper;
import cn.stylefeng.roses.kernel.model.page.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;
import java.util.Map;
/**
 * 地区Wrapper
 *
 * @author yaying.liu
 * @Date 2019-12-06 11:32:41
 */
public class RegionWrapper extends BaseControllerWrapper{

    public RegionWrapper(Map<String, Object> single) {
            super(single);
        }

        public RegionWrapper(List<Map<String, Object>> multi) {
            super(multi);
        }

        public RegionWrapper(Page<Map<String, Object>> page) {
            super(page);
        }

        public RegionWrapper(PageResult<Map<String, Object>> pageResult) {
            super(pageResult);
        }

        @Override
        protected void wrapTheMap(Map<String, Object> map) {

        }
}