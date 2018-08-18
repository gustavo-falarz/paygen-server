package com.gfbdev.session;

import com.gfbdev.Messages;
import com.gfbdev.entity.Lobby;
import com.gfbdev.entity.Provider;
import com.gfbdev.entity.Response;
import com.gfbdev.repository.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ProviderSession {

    @Autowired
    ProviderRepository repository;

    public Response findProvider(String id) {
        try {
            Provider provider = repository.findOne(id);
            if (provider == null) {
                return Response.error(Messages.getInstance().getString("messages.error.provider-not-found"));
            } else {
                return Response.ok(provider);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.error(e.getMessage());
        }
    }

    public Response findProviderByEmail(String email) {
        try {
            Provider provider = repository.findByEmail(email);
            if (provider == null) {
                return Response.error(Messages.getInstance().getString("messages.error.provider-not-found"));
            }

            return Response.ok(provider);

        } catch (Exception e) {
            e.printStackTrace();
            return Response.error(e.getMessage());
        }
    }

    public Response addProvider(Provider provider) {
        try {
            Provider existing = repository.findByEmail(provider.getEmail());
            if (existing != null) {
                return Response.error(Messages.getInstance().getString("messages.error.provider-already-registered"));
            }
            provider.setConsumptions(new ArrayList<>());
            Lobby lobby = new Lobby();
            lobby.setCustomerList(new ArrayList<>());
            provider.setLobby(lobby);
            provider.setSales(new ArrayList<>());
            provider.setEmployees(new ArrayList<>());
            provider.setItems(new ArrayList<>());
            repository.save(provider);
            return Response.ok("Cadastro salvo com sucesso, sua candidatura será avaliada em até 3 dias úteis, assim que o obitvermos os resultados entraremos em contato");
        } catch (Exception e) {
            e.printStackTrace();
            return Response.error(e.getMessage());
        }
    }

    public Response findAll() {
        try {
            return Response.ok(repository.findAll());
        } catch (Exception e) {
            e.printStackTrace();
            return Response.error(e.getMessage());
        }
    }
}
