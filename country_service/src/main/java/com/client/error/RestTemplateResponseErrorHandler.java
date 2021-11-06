package com.client.error;

import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

@Component
public class RestTemplateResponseErrorHandler 
  implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse httpResponse) 
      throws IOException {
        return (
          httpResponse.getStatusCode().series() == org.springframework.http.HttpStatus.Series.CLIENT_ERROR 
          || httpResponse.getStatusCode().series() == org.springframework.http.HttpStatus.Series.SERVER_ERROR);
    }

    @Override
    public void handleError(ClientHttpResponse httpResponse) 
      throws IOException {
        if (httpResponse.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR) {
            throw new RuntimeException("Server Error: " + httpResponse.getStatusCode().getReasonPhrase());
        } else if (httpResponse.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR) {
            if (httpResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
              throw new RuntimeException("Not Found");
            } else throw new RuntimeException("Client Error: " + httpResponse.getStatusCode().getReasonPhrase());
        }
    }
  }