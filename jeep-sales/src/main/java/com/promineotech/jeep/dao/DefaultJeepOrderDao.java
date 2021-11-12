package com.promineotech.jeep.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.promineotech.jeep.entity.Color;
import com.promineotech.jeep.entity.Customer;
import com.promineotech.jeep.entity.Engine;
import com.promineotech.jeep.entity.Jeep;
import com.promineotech.jeep.entity.JeepModel;
import com.promineotech.jeep.entity.Option;
import com.promineotech.jeep.entity.Order;
import com.promineotech.jeep.entity.OrderRequest;
import com.promineotech.jeep.entity.Tire;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@Service
public abstract class DefaultJeepOrderDao implements JeepOrderDao {
  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;
    
     public Order createOrder(Order orderRequest) {
      log.debug("Order = {}", orderRequest);
      return orderRequest;
      
    }

    public Customer fetchCustomer(String customerId) {
      String sql = ""
          + "SELECT * "
          + "FROM customers "
          + "WHERE customer_id = :customer_id";
      
     Map<String,Object> params = new HashMap<>();
     params.put("customer_id", customerId);
     
     return jdbcTemplate.query(sql,  params, new ResultSetExtractor<Customer>() {
       @Override
       public Customer extractData(ResultSet rs)
         throws SQLException {
         return null;
       }
     });
  }

    @Override
    public List<Option> fetchOptions(List<String> optionIds) {
      // TODO Auto-generated method stub
      return null;
    }

    @Override
    public Optional<Jeep> fetchModel(JeepModel model, String trim, int doors) {
      // TODO Auto-generated method stub
      return null;
    }

    @Override
    public Optional<Color> fetchColor(String colorId) {
      // TODO Auto-generated method stub
      return null;
    }

    @Override
    public Optional<Engine> fetchEngine(String engineId) {
      // TODO Auto-generated method stub
      return null;
    }

    @Override
    public Optional<Tire> fetchTire(String tireId) {
      // TODO Auto-generated method stub
      return null;
    }
}
  
