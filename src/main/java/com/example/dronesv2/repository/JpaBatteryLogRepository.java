package com.example.dronesv2.repository;

import com.example.dronesv2.model.BatteryLog;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
public class JpaBatteryLogRepository implements BatteryLogRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<BatteryLog> findById(Long id) {
        BatteryLog battery = entityManager.find(BatteryLog.class, id);
        return Optional.ofNullable(battery);
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public <S extends BatteryLog> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public List<BatteryLog> findAll() {
        TypedQuery<BatteryLog> query = entityManager.createQuery("SELECT b FROM BatteryLog b", BatteryLog.class);
        return query.getResultList();
    }

    @Override
    public List<BatteryLog> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(BatteryLog entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends BatteryLog> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public BatteryLog save(BatteryLog batteryLog) {
        entityManager.persist(batteryLog);
        return batteryLog;
    }

    @Override
    public List<BatteryLog> findBatteryLogsByDroneSerialNumberIs(String droneSerialNumber) {
        TypedQuery<BatteryLog> query = entityManager.createQuery("SELECT b FROM BatteryLog b WHERE b.droneSerialNumber = :droneSerialNumber", BatteryLog.class);
        query.setParameter("droneSerialNumber", droneSerialNumber);
        return query.getResultList();
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends BatteryLog> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends BatteryLog> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<BatteryLog> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public BatteryLog getOne(Long aLong) {
        return null;
    }

    @Override
    public BatteryLog getById(Long aLong) {
        return null;
    }

    @Override
    public BatteryLog getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends BatteryLog> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends BatteryLog> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends BatteryLog> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends BatteryLog> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends BatteryLog> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends BatteryLog> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends BatteryLog, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public List<BatteryLog> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<BatteryLog> findAll(Pageable pageable) {
        return null;
    }
}
