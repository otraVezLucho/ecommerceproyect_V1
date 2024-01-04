package com.ecommerce.beta1.service;

import com.ecommerce.beta1.model.DetalleOrden;
import com.ecommerce.beta1.repository.IDetalleOrdenRepository;
import com.ecommerce.beta1.repository.IOrdenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetalleOrdenServiceImp implements IDetalleOrdenService{
    @Autowired
    private IDetalleOrdenRepository detalleOrdenRepository;
    @Override
    public DetalleOrden save(DetalleOrden detalleOrden) {
        return detalleOrdenRepository.save(detalleOrden);
    }
}
