package com.gfbdev.session;

import com.gfbdev.entity.*;
import com.gfbdev.repository.CustomerRepository;
import com.gfbdev.repository.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.gfbdev.Messages.getInstance;

@Component
public class LobbySession {
    private final ProviderRepository providerRepository;

    private final CustomerRepository customerRepository;

    private final ConsumptionSession consumptionSession;

    private final ProviderSession providerSession;

    private final CustomerSession customerSession;

    @Autowired
    public LobbySession(ProviderRepository providerRepository, CustomerRepository customerRepository, ConsumptionSession consumptionSession, ProviderSession providerSession, CustomerSession customerSession) {
        this.providerRepository = providerRepository;
        this.customerRepository = customerRepository;
        this.consumptionSession = consumptionSession;
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

            if (provider.getLobby().getCustomerList().contains(customer)) {
                return Response.error(getInstance().getString("messages.error.user-already-checked-in"));
            }

            Response responseConsumption = consumptionSession.addConsumption(customer, provider);
            if (!responseConsumption.status) {
                return Response.error(getInstance().getString("messages.error.problems-adding-consumption"));
            }

            provider.getLobby().getCustomerList().add(customer);
            providerRepository.save(provider);

            customer.setCheckedIn(new CheckedIn(provider.getId(), provider.getName(), provider.getInfo()));
            customerRepository.save(customer);
            return Response.ok(getInstance().getString("messages.info.user-checked-in"));

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

            if (!provider.getLobby().getCustomerList().contains(customer)) {
                return Response.error("messages.error.user-not-checked-in");
            }

            Response responseConsumption = consumptionSession.getConsumption(userID, providerId);
            if (!responseConsumption.status) {
                return Response.error(getInstance().getString("messages.error.problems-finding-consumption"));
            }
            Consumption consumption = (Consumption) responseConsumption.data;
            if (consumption.getItems().size() != 0) {
                return Response.error(getInstance().getString("messages.error.problems-deleting-consumption"));
            }
            provider.getLobby().getCustomerList().remove(customer);
            provider.getConsumptions().remove(consumption);
            providerRepository.save(provider);

            customer.setCheckedIn(new CheckedIn("", "", new ProviderInfo()));
            customerRepository.save(customer);

            return Response.ok(getInstance().getString("messages.info.user-checked-out"));

        } catch (Exception e) {
            e.printStackTrace();
            return Response.error(e.getMessage());
        }
    }

    public Response getCustomers(String providerId) {
        try {
            Response responseProvider = providerSession.findProvider(providerId);
            if (!responseProvider.status) {
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
