package cn.fan.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

	@Bean
	public RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder) {
		/**
		 * 类似于
		 *	gateway:
		 *       routes:
		 *         - id: path_route_fantuan      	#路由的ID，没有固定规则但要求唯一，建议配合服务名
		 *           uri:http://news.baidu.com/  	#匹配后提供服务的路由地址
		 *           predicates:
		 *             - Path=/guonei	    		#断言,路径相匹配的进行路由
		 */
		RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
		routes.route("path_route_fantuan",
				r -> r.path("/guoji").uri("http://news.baidu.com/")).build();
		return routes.build();
	}
}
