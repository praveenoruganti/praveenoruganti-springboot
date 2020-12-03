package com.praveen.shedlock;

import static springfox.documentation.builders.PathSelectors.regex;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.apache.logging.log4j.util.Strings;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.context.request.async.DeferredResult;

import com.fasterxml.classmate.TypeResolver;
import com.google.common.base.Predicate;

import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.provider.jdbctemplate.JdbcTemplateLockProvider;
import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.DocExpansion;
import springfox.documentation.swagger.web.ModelRendering;
import springfox.documentation.swagger.web.OperationsSorter;
import springfox.documentation.swagger.web.TagsSorter;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableScheduling
@EnableSchedulerLock(defaultLockAtMostFor = "${app.shedlock.defaultLockAtMostFor}")
public class ShedlockApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShedlockApplication.class);
	}
	

    @Bean
    public LockProvider lockProvider(DataSource dataSource) {
        return new JdbcTemplateLockProvider(dataSource);
    }
	
	@Bean
	public Docket shedlockApplicationDocket(Environment environment, TypeResolver typeResolver) {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(new ApiInfo(environment.getRequiredProperty("apidocs.info.title"),
						environment.getRequiredProperty("apidocs.info.description"),
						environment.getRequiredProperty("apidocs.info.version"), Strings.EMPTY,
						new Contact("Praveen Oruganti Tech Team", "https://praveenorugantitech.blogspot.com/",
								"praveenorugantitech@gmail.com"),
						Strings.EMPTY, Strings.EMPTY, new ArrayList<>()))
				.select().apis(RequestHandlerSelectors.any()).paths(paths()).build().pathMapping("/")
				.directModelSubstitute(LocalDate.class, String.class).genericModelSubstitutes(ResponseEntity.class)
				.alternateTypeRules(AlternateTypeRules.newRule(
						typeResolver.resolve(DeferredResult.class,
								typeResolver.resolve(ResponseEntity.class, WildcardType.class)),
						typeResolver.resolve(WildcardType.class)))
				.useDefaultResponseMessages(false);
	}

	private Predicate<String> paths() {
		return regex("/api/v1_0/.*");
	}

	@Bean
	@ConditionalOnMissingBean
	public TypeResolver typeResolver() {
		return new TypeResolver();
	}

	@Bean
	UiConfiguration uiConfig(Environment environment) {
		return UiConfigurationBuilder.builder().deepLinking(true).displayOperationId(false).defaultModelsExpandDepth(1)
				.defaultModelExpandDepth(1).defaultModelRendering(ModelRendering.EXAMPLE).displayRequestDuration(false)
				.docExpansion(DocExpansion.NONE).filter(false).maxDisplayedTags(null)
				.operationsSorter(OperationsSorter.ALPHA).showExtensions(false).tagsSorter(TagsSorter.ALPHA)
				.supportedSubmitMethods(
						environment.getProperty("app.disable.tryOutOption", Boolean.class, Boolean.TRUE)
								? UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS
								: UiConfiguration.Constants.NO_SUBMIT_METHODS)
				.validatorUrl(null).build();
	}

}
