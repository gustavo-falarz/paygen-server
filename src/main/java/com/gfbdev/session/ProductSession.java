package com.gfbdev.session;

import com.gfbdev.Messages;
import com.gfbdev.entity.Item;
import com.gfbdev.entity.Product;
import com.gfbdev.entity.Provider;
import com.gfbdev.entity.Response;
import com.gfbdev.repository.ProductRepository;
import com.gfbdev.repository.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Headtrap on 15/07/2017.
 */
@Component
public class ProductSession {

    private final
    ProductRepository productRepository;

    private final
    ProviderRepository providerRepository;

    private final
    ProviderSession providerSession;

    @Autowired
    public ProductSession(ProductRepository productRepository, ProviderRepository providerRepository, ProviderSession providerSession) {
        this.productRepository = productRepository;
        this.providerRepository = providerRepository;
        this.providerSession = providerSession;
    }

    public Response listProducts(String providerId) {
        try {
            Response responseProvider = providerSession.findProvider(providerId);
            if (!responseProvider.status) {
                return responseProvider;
            }
            Provider provider = (Provider) responseProvider.data;
            return Response.ok(provider.getItems());
        } catch (Exception e) {
            e.printStackTrace();
            return Response.error(e.getMessage());
        }
    }

    public Response addProduct(String providerId, Product product) {
        Response responseProvider = providerSession.findProvider(providerId);
        if (!responseProvider.status) {
            return responseProvider;
        }

        Provider provider = (Provider) responseProvider.data;
        if (provider.getItems().contains(product)){
            provider.getItems().remove(product);
        }

        Product definitive = product;
        if (product.getId()== null || product.getId().equals("")){
            definitive = build(product);
        }

        productRepository.save(definitive);
        provider.getItems().add(definitive);
        providerRepository.save(provider);

        return Response.ok(Messages.getInstance().getString("messages.success.product-saved"));
    }

    public Response findProduct(String providerId, String query) {
        try {
            Response responseProvider = providerSession.findProvider(providerId);
            if (!responseProvider.status) {
                return responseProvider;
            }
            Provider provider = (Provider) responseProvider.data;
            List<Item> items = new ArrayList<>();
            for (Item i : provider.getItems()) {
                if (i.getDescription().toLowerCase().contains(query.toLowerCase())) {
                    items.add(i);
                }
            }
            if (items.size() == 0) {
                return Response.error(Messages.getInstance().getString("messages.error.no-items-found"));
            }
            return Response.ok(items);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.error(e.getMessage());
        }
    }

    public Response findProduct(String id) {
        try {
            Item item = productRepository.findOne(id);
            if (item == null) {
                return Response.error(Messages.getInstance().getString("messages.error.no-items-found"));
            }
            return Response.ok(item);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.error(e.getMessage());
        }
    }

    public Product build(Product product){
        Product building = new Product();
        building.setAmount(product.getAmount());
        building.setValue(product.getValue());
        building.setPicture(product.getPicture());
        building.setDescription(product.getDescription());
        building.setName(product.getName());
        building.setPrice(product.getPrice());
        return building;
    }
}
