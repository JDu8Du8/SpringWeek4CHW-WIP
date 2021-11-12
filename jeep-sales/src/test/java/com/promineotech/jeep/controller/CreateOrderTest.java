package com.promineotech.jeep.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import com.promineotech.jeep.controller.support.CreateOrderTestSupport;
import com.promineotech.jeep.entity.JeepModel;
import com.promineotech.jeep.entity.Order;
import lombok.Getter;
import lombok.Setter;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Sql(scripts = {
    "classpath:flyway/migrations/V1.0__Jeep_Schema.sql",
    "classpath:flyway/migrations/V1.1__Jeep_Data.sql"}, 
    config = @SqlConfig(encoding = "utf-8"))


class CreateOrderTest extends CreateOrderTestSupport{
  
  @LocalServerPort
  protected int serverPort;
  
  @Autowired
  @Setter
  @Getter
  public TestRestTemplate restTemplate;
  
  String uri = String.format("http://localhost:%d/orders", serverPort);
  
  @Test
  void testCreateOrderReturnsSuccess201() {
    String body = createOrderBody();
    
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    
    HttpEntity<String> bodyEntity = new HttpEntity<>(body, headers);
    
    ResponseEntity<Order> response = getRestTemplate().exchange(uri,
        HttpMethod.POST, bodyEntity, Order.class);
   
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    assertThat(response.getBody()).isNotNull();

    Order order = response.getBody();
    assertThat(order.getCustomer().getCustomerId()).isEqualTo("ATTAWAY_HECKTOR");
    assertThat(order.getModel().getModelId()).isEqualTo(JeepModel.GLADIATOR);
    assertThat(order.getModel().getTrimLevel()).isEqualTo("Sport Altitude");
    assertThat(order.getModel().getNumDoors()).isEqualTo(4);
    assertThat(order.getColor().getColorId()).isEqualTo("EXT_NACHO");
    assertThat(order.getEngine().getEngineId()).isEqualTo("3_6_GAS");
    assertThat(order.getTire().getTireId()).isEqualTo("265_MICHELIN");
    assertThat(order.getOptions()).hasSize(7);
    }

    protected String createOrderBody() {
      // @formatter:off
      return "{\n"
          + "   \"customer\":\"ATTAWAY_HECKTOR\",\n"
          + "   \"model\":\"GLADIATOR\",\n"
          + "   \"trim\":\"Sport Altitude\",\n"
          + "   \"doors\":4,\n"
          + "   \"color\":\"EXT_NACHO\",\n"
          + "   \"engine\":\"3_6_GAS\",\n"
          + "   \"tire\":\"265_MICHELIN\",\n"
          + "   \"options\":[\n"
          + "      \"DOOR_QUAD_4\",\n"
          + "      \"EXT_AEV_LIFT\",\n"
          + "      \"EXT_WARN_WINCH\",\n"
          + "      \"EXT_WARN_BUMPER_FRONT\",\n"
          + "      \"EXT_WARN_BUMPER_REAR\",\n"
          + "      \"EXT_ARB_COMPRESSOR\",\n"
          + "      \"TOP_MOPAR_TOP_MESH\"\n"
          + "   ]\n"
          + "}";
        //@formatter:on
    }
}
