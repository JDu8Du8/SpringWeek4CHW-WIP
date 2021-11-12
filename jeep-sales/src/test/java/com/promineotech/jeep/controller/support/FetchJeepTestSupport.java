package com.promineotech.jeep.controller.support;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.Map;
import org.springframework.http.HttpStatus;

public class FetchJeepTestSupport {

  
  protected void assertErrorMessageUnknown(Map<String, Object> error) {
    /**
     * @param error
     * 
     */
    // @formatter:off
    assertThat(error)
    .containsKey("message")
    .containsEntry("status code", HttpStatus.NOT_FOUND.value())
    .containsKey("uri")
    .containsKey("timestamp")
    .containsEntry("reason", HttpStatus.NOT_FOUND.getReasonPhrase());
    // @formatter: on
  }
  
  /**
   * 
   * @param error
   * @param status
   */
  protected void assertErrorMessageValid(Map<String, Object> error, HttpStatus status) {
    // @formatter:off
    assertThat(error)
    .containsKey("message")
    .containsEntry("status code", status.BAD_REQUEST.value())
    .containsKey("uri")
    .containsKey("timestamp")
    .containsEntry("reason", status.BAD_REQUEST.getReasonPhrase());
    // @formatter: on
  }
  
}
