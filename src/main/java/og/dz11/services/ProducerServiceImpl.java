package og.dz11.services;

import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProducerServiceImpl implements ProducerService {
    private final JmsTemplate jmsTemplate;

    @Override
    public void SendMessage(String message) {
        jmsTemplate.convertAndSend("queue",message);
    }
}
