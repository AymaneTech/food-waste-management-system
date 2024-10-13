package net.foodeals.order.application.services.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.foodeals.order.application.dtos.requests.CouponRequest;
import net.foodeals.order.application.services.CouponService;
import net.foodeals.order.domain.entities.Coupon;
import net.foodeals.order.domain.exceptions.CouponNotFoundException;
import net.foodeals.order.domain.repositories.CouponRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {

    private final CouponRepository repository;
    private final ModelMapper mapper;

    @Override
    public List<Coupon> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<Coupon> findAll(Integer pageNumber, Integer pageSize) {
        return repository.findAll(PageRequest.of(pageNumber, pageSize));
    }

    @Override
    public Coupon findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new CouponNotFoundException(id));
    }

    @Override
    public Coupon create(CouponRequest request) {
        final Coupon coupon = mapper.map(request, Coupon.class);
        // todo: add subentity
        return repository.save(coupon);
    }

    @Override
    public Coupon update(UUID id, CouponRequest request) {
        final Coupon coupon = findById(id);
        mapper.map(request, coupon);
        return repository.save(coupon);
    }


    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id))
            throw new CouponNotFoundException(id);

        repository.softDelete(id);
    }

    public Coupon toggleIsEnabled(UUID id) {
        final Coupon coupon = findById(id);
        coupon.toggleIsEnabled();
        return repository.save(coupon);
    }
}
