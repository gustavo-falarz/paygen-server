package com.gfbdev.session;

import com.gfbdev.Messages;
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

    public Response add(Transaction transaction) {
        try {
            Response responseCustomer = customerSession.findCustomer(transaction.getCustomerId());
            if (!responseCustomer.status) {
                return responseCustomer;
            }

            Response responseProvider = providerSession.findProvider(transaction.getProviderId());
            if (!responseProvider.status) {
                return responseProvider;
            }

            Customer customer = (Customer) responseCustomer.getData();
            Provider provider = (Provider) responseProvider.getData();

            customer.getPurchases().add(transaction);
            provider.getSales().add(transaction);

            customerRepository.save(customer);
            providerRepository.save(provider);
            return Response.ok(Messages.getInstance().getString("messages.success.transaction-saved"));

        } catch (Exception e) {
            return Response.error(e.getMessage());
        }
    }
}


