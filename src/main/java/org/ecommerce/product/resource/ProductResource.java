package org.ecommerce.product.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.ecommerce.product.entity.Product;
import org.ecommerce.product.service.ProductService;

import java.util.List;

@Path("/products")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductResource {

    @Inject
    ProductService service;

    @POST
    public Response create(Product product) {
        try {
            service.addProduct(product);
            return Response.status(Response.Status.CREATED).build(); // 201
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to add product: " + e.getMessage())
                    .build(); // 500
        }
    }

    @GET
    public Response getAll() {
        try {
            List<Product> products = service.getAllProducts();
            return Response.ok(products).build(); // 200
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to fetch products")
                    .build(); // 500
        }
    }
}


