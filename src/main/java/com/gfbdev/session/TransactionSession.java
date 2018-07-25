package com.gfbdev.session;

import com.gfbdev.entity.*;
import com.gfbdev.entity.dto.TransactioDTO;
import com.gfbdev.repository.CustomerRepository;
import com.gfbdev.repository.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Headtrap on 28/08/2017.
 */
@Component
public class TransactionSession {

    private final
    CustomerSession customerSession;

    private final
    ProviderSession providerSession;

    private final
    CustomerRepository customerRepository;

    private final
    ProviderRepository providerRepository;

    @Autowired
    public TransactionSession(CustomerSession customerSession, ProviderSession providerSession, CustomerRepository customerRepository, ProviderRepository providerRepository) {
        this.customerSession = customerSession;
        this.providerSession = providerSession;
        this.customerRepository = customerRepository;
        this.providerRepository = providerRepository;
    }

    public Response add(TransactioDTO dto) {
        try {
            Response responseCustomer = customerSession.findCustomer(dto.getCustomer().getId());
            Response responseProvider = providerSession.findProvider(dto.getProvider().getId());
            if (!responseCustomer.status) {
                return responseCustomer;
            }
            if (!responseProvider.status) {
                return responseProvider;
            }

            Customer customer = (Customer) responseCustomer.getData();
            Provider provider = (Provider) responseProvider.getData();

            customer.getPurchases().add(dto.getTransaction());
            provider.getSales().add(dto.getTransaction());

            customerRepository.save(customer);
            providerRepository.save(provider);
            return Response.ok("Venda realizada com sucesso.");

        } catch (Exception e) {
            return Response.error(e.getMessage());
        }
    }
}


