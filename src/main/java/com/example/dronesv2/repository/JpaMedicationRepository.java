package com.example.dronesv2.repository;

import com.example.dronesv2.model.Medication;
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
public class JpaMedicationRepository implements MedicationRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Medication> findByCode(String code) {
        TypedQuery<Medication> query = entityManager.createQuery("SELECT m FROM Medication m WHERE m.code = :code", Medication.class);
        query.setParameter("code", code);
        return query.getResultStream().findFirst();
    }

    @Override
    public <S extends Medication> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Medication> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<Medication> findAll() {
        TypedQuery<Medication> query = entityManager.createQuery("SELECT m FROM Medication m", Medication.class);
        return query.getResultList();
    }

    @Override
    public List<Medication> findAllById(Iterable<Long> longs) {
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
    public void delete(Medication entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Medication> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Medication save(Medication medication) {
        entityManager.persist(medication);
        return medication;
    }

    @Override
    public void deleteByCode(String code) {
        Optional<Medication> medicationOptional = findByCode(code);
        medicationOptional.ifPresent(medication -> entityManager.remove(medication));
    }

    @Override
    public Medication update(Medication medication) {
        entityManager.merge(medication);
        return medication;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Medication> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Medication> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Medication> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Medication getOne(Long aLong) {
        return null;
    }

    @Override
    public Medication getById(Long aLong) {
        return null;
    }

    @Override
    public Medication getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Medication> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Medication> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Medication> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Medication> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Medication> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Medication> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Medication, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public List<Medication> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Medication> findAll(Pageable pageable) {
        return null;
    }
}