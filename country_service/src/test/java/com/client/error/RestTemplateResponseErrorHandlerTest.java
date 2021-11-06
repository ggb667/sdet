package com.client.error;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.springframework.http.HttpStatus;
import org.springframework.mock.http.client.MockClientHttpResponse;    

public class RestTemplateResponseErrorHandlerTest {

    @Test
    public void testHasError() {
        MockClientHttpResponse cerServer = new MockClientHttpResponse("Error".getBytes(StandardCharsets.UTF_8), HttpStatus.INTERNAL_SERVER_ERROR);
        MockClientHttpResponse cerClient = new MockClientHttpResponse("Error".getBytes(StandardCharsets.UTF_8), HttpStatus.BAD_REQUEST);
        RestTemplateResponseErrorHandler rtreht = new RestTemplateResponseErrorHandler();
        try{
            assertTrue(rtreht.hasError(cerServer));
            assertTrue(rtreht.hasError(cerClient));
        }catch(IOException shouldNotExist){
            assertFalse(true);
        }
    }

}
    