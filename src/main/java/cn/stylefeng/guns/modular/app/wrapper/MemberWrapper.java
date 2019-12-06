package cn.stylefeng.guns.modular.app.wrapper;
import cn.stylefeng.roses.core.base.warpper.BaseControllerWrapper;
import cn.stylefeng.roses.kernel.model.page.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;
import java.util.Map;
/**
 * 用户信息Wrapper
 *
 * @author yaying.liu
 * @Date 2019-12-06 09:50:50
 */
public class MemberWrapper extends BaseControllerWrapper{

    public MemberWrapper(Map<String, Object> single) {
            super(single);
        }

        public MemberWrapper(List<Map<String, Object>> multi) {
            super(multi);
        }

        public MemberWrapper(Page<Map<String, Object>> page) {
            super(page);
        }

        public MemberWrapper(PageResult<Map<String, Object>> pageResult) {
            super(pageResult);
        }

        @Override
        protected void wrapTheMap(Map<String, Object> map) {

        }
}