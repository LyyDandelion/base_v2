package cn.stylefeng.guns.core.interceptor;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截配置
 */
@SpringBootConfiguration
public class InterceptorConfiguration implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册拦截器
        InterceptorRegistration ir = registry.addInterceptor(loginInterceptor());
        // 配置拦截的路径
        ir.addPathPatterns("/api/**");
        // 配置不拦截的路径
        ir.excludePathPatterns("/api/login");//登录
        ir.excludePathPatterns("/api/register");//注册
        ir.excludePathPatterns("/api/forgetPwd");//忘记密码
        ir.excludePathPatterns("/api/getInvite");//短信验证码
        ir.excludePathPatterns("/api/version");//版本
        ir.excludePathPatterns("/api/upload/img");//图片上传
        ir.excludePathPatterns("/api/personal/**");//处理外部回调api
        ir.excludePathPatterns("/api/common/**");//公共api
        ir.excludePathPatterns("/api/visitor/**");//游客访问api

    }

    @Bean
    public LoginInterceptor loginInterceptor() {
        return new LoginInterceptor();
    }

}
