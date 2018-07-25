package com.gfbdev.session;

import com.gfbdev.Messages;
import com.gfbdev.entity.Consumption;
import com.gfbdev.entity.Provider;
import com.gfbdev.entity.Response;
import com.gfbdev.entity.dto.ConsumptionDTO;
import com.gfbdev.repository.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ConsumptionSession {

    private final
    CustomerSession customerSession;

    private final
    ProviderSession providerSession;

    private final
    ProviderRepository providerRepository;

    @Autowired
    public ConsumptionSession(CustomerSession customerSession, ProviderSession providerSession,
                              ProviderRepository providerRepository) {
        this.customerSession = customerSession;
        this.providerSession = providerSession;
        this.providerRepository = providerRepository;
    }


    public Response addConsumption(ConsumptionDTO dto) {
        try {
            Response responseCustomer = customerSession.findCustomer(dto.getCustomer().getId());
            if (!responseCustomer.status) {
                return responseCustomer;
            }
            Response responseProvider = customerSession.findCustomer(dto.getCustomer().getId());
            if (!responseCustomer.status) {
                return responseProvider;
            }
            Consumption consumption = new Consumption();
            consumption.setItems(dto.getItems());
            consumption.setCustomer(dto.getCustomer());
            consumption.setItems(new ArrayList<>());

            Provider provider = (Provider) responseProvider.getData();
            provider.getConsumptions().add(consumption);
            return Response.ok(providerRepository.save(provider));
        } catch (Exception e) {
            e.printStackTrace();
            return Response.error(e.getMessage());
        }
    }

    public Response getConsumption(ConsumptionDTO dto) {
        try {
            Response responseCustomer = customerSession.findCustomer(dto.getCustomer().getId());
            if (!responseCustomer.status) {
                return responseCustomer;
            }
            Response responseProvider = customerSession.findCustomer(dto.getCustomer().getId());
            if (!responseCustomer.status) {
                return responseProvider;
            }

            Provider provider = (Provider) responseProvider.getData();
            for (Consumption c : provider.getConsumptions()) {
                if (c.getCustomer().equals(dto.getCustomer())) {
                    return Response.ok(c);
                }
            }
            return Response.error(Messages.getInstance().getString("messages.error.consumption-not-found"));
        } catch (Exception e) {
            e.printStackTrace();
            return Response.error(e.getMessage());
        }
    }

    public Response addItem(ConsumptionDTO dto) {
        try {
            Response responseCustomer = customerSession.findCustomer(dto.getCustomer().getId());
            if (!responseCustomer.status) {
                return responseCustomer;
            }
            Response responseProvider = customerSession.findCustomer(dto.getCustomer().getId());
            if (!responseProvider.status) {
                return responseProvider;
            }

            Response responseConsumption = getConsumption(dto);
            if (!responseConsumption.status) {
                return responseConsumption;
            }

            Consumption consumption = (Consumption) responseConsumption.getData();
            consumption.getItems().addAll(dto.getItems());
            return Response.ok(Messages.getInstance().getString("messages.info.item-added"));
        } catch (Exception e) {
            e.printStackTrace();
            return Response.error(e.getMessage());
        }
    }

    public Response removeItem(ConsumptionDTO dto) {
        try {
            Response responseCustomer = customerSession.findCustomer(dto.getCustomer().getId());
            if (!responseCustomer.status) {
                return responseCustomer;
            }
            Response responseProvider = customerSession.findCustomer(dto.getCustomer().getId());
            if (!responseProvider.status) {
                return responseProvider;
            }

            Response responseConsumption = getConsumption(dto);
            if (!responseConsumption.status) {
                return responseConsumption;
            }

            Consumption consumption = (Consumption) responseConsumption.getData();
            consumption.getItems().removeAll(dto.getItems());
            return Response.ok(Messages.getInstance().getString("messages.info.item-removed"));
        } catch (Exception e) {
            e.printStackTrace();
            return Response.error(e.getMessage());
        }
    }
}