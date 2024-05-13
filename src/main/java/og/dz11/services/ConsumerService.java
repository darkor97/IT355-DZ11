package og.dz11.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.jms.Message;

public interface ConsumerService {

    public void ReceiveMessage(String message) throws JsonProcessingException;
}
