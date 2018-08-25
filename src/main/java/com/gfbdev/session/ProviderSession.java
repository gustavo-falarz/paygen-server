package com.gfbdev.session;

import com.gfbdev.Messages;
import com.gfbdev.entity.*;
import com.gfbdev.entity.dto.ImagesDTO;
import com.gfbdev.repository.ProviderRepository;
import com.gfbdev.utils.Constants;
import com.gfbdev.utils.Password;
import com.gfbdev.utils.StringUtils;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

import static com.gfbdev.service.EmailService.sendEmailNewUser;

@Component
public class ProviderSession {

    @Autowired
    ProviderRepository providerRepository;

    public Response findProvider(String id) {
        try {
            Provider provider = providerRepository.findOne(id);
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
            Provider provider = providerRepository.findByEmail(email);
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
            Provider existing = providerRepository.findByEmail(provider.getEmail());
            if (existing != null) {
                return Response.error(Messages.getInstance().getString("messages.error.provider-already-registered"));
            }
            provider.setId(null);
            provider.setConsumptions(new ArrayList<>());
            provider.setStatus(User.Status.PENDING);
            Lobby lobby = new Lobby();
            lobby.setCustomerList(new ArrayList<>());
            provider.setLobby(lobby);
            provider.setSales(new ArrayList<>());
            provider.setEmployees(new ArrayList<>());
            provider.setItems(new ArrayList<>());
            String randomCode = StringUtils.generateRandomCode();
            String encrypted = Password.getInstance().getEncryptor().encryptPassword(randomCode);
            provider.setPassword(encrypted);
            String message = String.format(Constants.MESSAGE_ACCOUNT_ACTIVATION,
                    randomCode);
            sendEmailNewUser(provider.getEmail(), message);
            providerRepository.save(provider);
            return Response.ok("Cadastro salvo com sucesso, sua candidatura será avaliada em até 3 dias úteis, assim que o obitvermos os resultados entraremos em contato");
        } catch (Exception e) {
            e.printStackTrace();
            return Response.error(e.getMessage());
        }
    }

    public Response listEmployees(String providerid) {
        try {
            Response responseProvider = findProvider(providerid);
            if (!responseProvider.status) {
                return responseProvider;
            }

            Provider provider = (Provider) responseProvider.data;
            return Response.ok(provider.getEmployees());
        } catch (Exception e) {
            return Response.error(e.getMessage());
        }
    }

    public Response findAll() {
        try {
            return Response.ok(providerRepository.findAll());
        } catch (Exception e) {
            e.printStackTrace();
            return Response.error(e.getMessage());
        }
    }

    public Response getImages(String providerId) {
        try {
            Response responseProvider = findProvider(providerId);
            if (!responseProvider.status) {
                return responseProvider;
            }
            Provider provider = (Provider) responseProvider.data;
            return Response.ok(new ImagesDTO(String.valueOf(provider.getInfo().getBanner()),
                    String.valueOf(provider.getInfo().getLogo())));
        } catch (Exception e) {
            e.printStackTrace();
            return Response.error(e.getMessage());
        }
    }

    public Response setImages(String providerId, ImagesDTO dto) {
        try {
            Response responseProvider = findProvider(providerId);
            if (!responseProvider.status) {
                return responseProvider;
            }
            Provider provider = (Provider) responseProvider.data;
            provider.getInfo().setBanner(dto.getBanner());
            provider.getInfo().setLogo(dto.getLogo());
            providerRepository.save(provider);

            return Response.ok(Messages.getInstance().getString("messages.success.images-set"));
        } catch (Exception e) {
            e.printStackTrace();
            return Response.error(e.getMessage());
        }
    }
}
