package dev.ercan.poc.hexagonal.adapter.soap.config;

import dev.ercan.jaxws.spring.binding.SoapServiceBinding;
import dev.ercan.jaxws.spring.factory.SoapServiceFactory;
import dev.ercan.jaxws.spring.servlet.SoapServiceServlet;
import dev.ercan.poc.hexagonal.adapter.soap.service.CalculatorSoapService;
import dev.ercan.poc.hexagonal.calculator.domain.port.in.CalculateUseCase;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SoapServiceConfig {

  @Bean
  public ServletRegistrationBean<SoapServiceServlet> soapServiceServlet() {
    SoapServiceServlet soapWsServlet = new SoapServiceServlet();
    ServletRegistrationBean<SoapServiceServlet> bean = new ServletRegistrationBean<>(soapWsServlet);
    bean.setLoadOnStartup(1);
    return bean;
  }

  @Bean
  public CalculatorSoapService calculatorSoapService(CalculateUseCase calculateUseCase) {
    return new CalculatorSoapService(calculateUseCase);
  }

  @Bean
  public SoapServiceBinding calculatorServiceBinding(CalculatorSoapService calculatorSoapService)
      throws Exception {
    SoapServiceFactory soapServiceFactory = new SoapServiceFactory();
    soapServiceFactory.setBean(calculatorSoapService);

    SoapServiceBinding soapServiceBinding = new SoapServiceBinding();
    soapServiceBinding.setUrl("/SOAP/CalculatorService");
    soapServiceBinding.setService(soapServiceFactory.getObject());

    return soapServiceBinding;
  }

}