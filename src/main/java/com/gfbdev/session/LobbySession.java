package com.gfbdev.session;

import com.gfbdev.entity.Customer;
import com.gfbdev.entity.Provider;
import com.gfbdev.entity.Response;
import com.gfbdev.repository.CustomerRepository;
import com.gfbdev.repository.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LobbySession {
    private final ProviderRepository providerRepository;

    private final CustomerRepository customerRepository;

    private final ProviderSession providerSession;

    private final CustomerSession customerSession;

    @Autowired
    public LobbySession(ProviderRepository providerRepository, CustomerRepository customerRepository, ProviderSession providerSession, CustomerSession customerSession) {
        this.providerRepository = providerRepository;
        this.customerRepository = customerRepository;
        this.providerSession = providerSession;
        this.customerSession = customerSession;
    }

    public Response checkIn(String userID, String providerId) {
        try {
            Response providerResponse = providerSession.findProvider(providerId);
            Response customerResponse = customerSession.findCustomer(userID);
            if (!providerResponse.status) {
                return providerResponse;
            }
            if (!customerResponse.status) {
                return customerResponse;
            }

            Customer customer = (Customer) customerResponse.data;
            Provider provider = (Provider) providerResponse.data;

            provider.getLobby().getCustomerList().add(customer);
            return Response.ok("Checkin realizado com sucesso");

        } catch (Exception e) {
            e.printStackTrace();
            return Response.error(e.getMessage());
        }

    }

    public Response checkOut(String userID, String providerId) {
        try {
            Response providerResponse = providerSession.findProvider(providerId);
            Response customerResponse = customerSession.findCustomer(userID);
            if (!providerResponse.status) {
                return providerResponse;
            }
            if (!customerResponse.status) {
                return customerResponse;
            }

            Customer customer = (Customer) customerResponse.data;
            Provider provider = (Provider) providerResponse.data;
            provider.getLobby().getCustomerList().remove(customer);
            return Response.ok("Checkin realizado com sucesso");

        } catch (Exception e) {
            e.printStackTrace();
            return Response.error(e.getMessage());
        }
    }

    public Response getCustomers(String providerId) {
        try {
            Response responseProvider = providerSession.findProvider(providerId);
            if (responseProvider.status) {
                return responseProvider;
            }
            Provider provider = (Provider) responseProvider.data;
            return Response.ok(provider.getLobby());
        } catch (Exception e) {
            e.printStackTrace();
            return Response.error(e.getMessage());
        }
    }

}
