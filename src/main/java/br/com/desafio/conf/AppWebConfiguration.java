package br.com.desafio.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import br.com.desafio.controllers.HomeController;
import br.com.desafio.dao.TaskDao;

/*
 * @author Renato Muniz
 * Classe que substitui o arquivo dispatcher-servlet.xml do Spring
 * 
 */

@EnableWebMvc
@ComponentScan(basePackageClasses={HomeController.class,TaskDao.class})
public class AppWebConfiguration extends WebMvcConfigurerAdapter{
	
	/*
	 * /(non-Javadoc)
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter#addResourceHandlers(org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry)
	 */
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**").addResourceLocations("/css/").setCachePeriod(31556926);
        registry.addResourceHandler("/javascript/**").addResourceLocations("/javascript/").setCachePeriod(31556926);
    }
	
	/*
	 * (non-Javadoc)
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter#configureDefaultServletHandling(org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer)
	 */
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
	
	
	/*
	 * @return InternalResourceViewResolver
	 * Metodo utilizado para mapear os diretorios referentes as views e arquivos jsp
	 * Toda classe anotada com @bean se torna gerenciável pelo container do Spring
	 */
	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver  resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

}
