package it.unical.inf.ae.core.service;

import it.unical.inf.ae.data.dao.UserRepository;
import it.unical.inf.ae.data.service.OrderService;
import it.unical.inf.ae.data.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExternalService {

    private final UserService userService;

    private final OrderService orderService;

    private final UserRepository userRepository;

    public void test() {
        //
    }

}
