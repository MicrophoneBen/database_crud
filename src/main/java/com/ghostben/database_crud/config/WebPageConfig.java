package com.ghostben.database_crud.config;

import com.ghostben.database_crud.config.component.LoginHanderlerInterceptor;
import com.ghostben.database_crud.config.component.MyLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
*@author    : Microphoneben
*@date      : 2018/9/17
*@description : WebPageConfig
*
*/
//想要让我们的这个配置启动器起作用，我们一定要添加configuration注解

@Configuration
public class WebPageConfig extends WebMvcConfigurerAdapter {

    /**
     * {@inheritDoc}
     * <p>This implementation is empty.
     *
     * @param registry
     */

    //  super.addViewControllers(registry);
    //  用来定向加载，以及转发我们的WebPage

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index.html").setViewName("index");
    }


    /**
    *@author    : Microphoneben
    *@date      : 2018/9/17
    *@description :   webMvcConfigurerAdapter方法 里面使用新建一个内部类，然后重写页面加载方法，作为一个
     *               WebMvcConfigurerAdapater返回，然后添加到我们的springboot容器中，与原来的springboot组件一起起作用
     *
     *   要让这个组件起作用我们还必须添加Bean注解或者Component注解
    */

    //@Component 这个注解不能添加在方法上

    @Bean
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter(){

        WebMvcConfigurerAdapter myAdapter = new WebMvcConfigurerAdapter() {
             //   super.addViewControllers(registry);

            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/login.html").setViewName("index");
                registry.addViewController("/main.html").setViewName("login");
            }
        };
        return myAdapter;
    }

    @Bean
    public LocaleResolver localeResolver(){
       return new MyLocaleResolver();
    }

    //注册拦截器，让他起作用
        //super.addInterceptors(registry);

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //  这里的/** 表示拦截任意多层路径下的任意请求
        registry.addInterceptor(new LoginHanderlerInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/index.html","/","login.html","/user/login");
    }
}
