package gov.va.evss.interview.senior.dev;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;

/**
 * Spring configuration for the project.
 */
@Configuration
@ComponentScan(basePackages = "gov.va.evss.interview.senior.dev", excludeFilters = @Filter(Configuration.class))
public class CatalogManagerConfig {}
