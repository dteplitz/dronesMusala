package com.example.dronesv2.repository;

import com.example.dronesv2.model.Drone;
import com.example.dronesv2.model.DroneState;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
public class JpaDroneRepository implements DroneRepository{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Drone save(Drone drone) {
        entityManager.persist(drone);
        return drone;
    }

    @Transactional
    public Drone update(Drone drone) {
        entityManager.merge(drone);
        return drone;
    }


    @Override
    public Optional<Drone> findBySerialNumber(String serialNumber) {
        TypedQuery<Drone> query = entityManager.createQuery("SELECT d FROM Drone d WHERE d.serialNumber = :serialNumber", Drone.class);
        query.setParameter("serialNumber", serialNumber);
        return query.getResultStream().findFirst();
    }

    @Override
    public <S extends Drone> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public List<Drone> findAll() {
        TypedQuery<Drone> query = entityManager.createQuery("SELECT d FROM Drone d", Drone.class);
        return query.getResultList();
    }

    @Override
    public List<Drone> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public Optional<Drone> findById(Long id) {
        Drone drone = entityManager.find(Drone.class, id);
        return Optional.ofNullable(drone);
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public void deleteById(Long id) {
        Optional<Drone> droneOptional = findById(id);
        droneOptional.ifPresent(drone -> entityManager.remove(drone));
    }

    @Override
    public void delete(Drone entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Drone> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public void deleteBySerialNumber(String serialNumber) {
        Optional<Drone> droneOptional = findBySerialNumber(serialNumber);
        droneOptional.ifPresent(drone -> entityManager.remove(drone));
    }

    @Override
    public List<Drone> findByState(DroneState state) {
        return entityManager.createQuery("SELECT d FROM Drone d WHERE d.state = :state", Drone.class)
                .setParameter("state", state)
                .getResultList();
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Drone> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Drone> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Drone> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Drone getOne(Long aLong) {
        return null;
    }

    @Override
    public Drone getById(Long aLong) {
        return null;
    }

    @Override
    public Drone getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Drone> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Drone> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Drone> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Drone> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Drone> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Drone> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Drone, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public List<Drone> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Drone> findAll(Pageable pageable) {
        return null;
    }
}