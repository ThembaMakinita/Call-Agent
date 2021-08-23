package com.jtm.assessment.callcentre.database.repo;

import com.jtm.assessment.callcentre.database.entity.Agent;
import com.jtm.assessment.callcentre.database.model.AgentPage;
import com.jtm.assessment.callcentre.database.model.AgentSearchCriteria;
import org.apache.catalina.valves.rewrite.InternalRewriteMap;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class AgentCriteriaRepository {

    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    public AgentCriteriaRepository (EntityManager entityManager){
        this.entityManager = entityManager;
        this.criteriaBuilder =entityManager.getCriteriaBuilder();
    }

    public Page<Agent> findAllWithFilters(AgentPage agentPage, AgentSearchCriteria agentSearchCriteria){

        CriteriaQuery<Agent> criteraQuery = criteriaBuilder.createQuery(Agent.class);
        Root<Agent> agentRoot = criteraQuery.from(Agent.class);
        Predicate predicate = getPredicate(agentSearchCriteria,agentRoot);
        criteraQuery.where(predicate);
        setOrder(agentPage,criteraQuery,agentRoot);
        TypedQuery<Agent> typedQuery = entityManager.createQuery(criteraQuery);
        typedQuery.setFirstResult(agentPage.getPageNumber() * agentPage.getPageSize());
        typedQuery.setMaxResults(agentPage.getPageSize());

        Pageable pageable = getPagable(agentPage);
        long agentCount = getAgentCount(predicate);
        return new PageImpl<>(typedQuery.getResultList(),pageable,agentCount);
    }

    private long getAgentCount(Predicate predicate) {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<Agent> countRoot = countQuery.from(Agent.class);
        countQuery.select(criteriaBuilder.count(countRoot)).where(predicate);
        return entityManager.createQuery(countQuery).getSingleResult();
    }

    private Predicate getPredicate(AgentSearchCriteria agentSearchCriteria, Root<Agent> agentRoot) {

        List<Predicate> predicates = new ArrayList<>();

        if(Objects.nonNull(agentSearchCriteria.getFirstName())){
            predicates.add(criteriaBuilder.like( agentRoot.get("firstName"),"%" + agentSearchCriteria.getFirstName()+ "%" ));
        }
        if(Objects.nonNull(agentSearchCriteria.getLastName())){
            predicates.add(criteriaBuilder.like(agentRoot.get("lastName"),"%" + agentSearchCriteria.getLastName()+ "%" ));
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private void setOrder(AgentPage agentPage, CriteriaQuery<Agent> criteraQuery, Root<Agent> agentRoot) {
        if(agentPage.getSortDirection().equals(Sort.Direction.ASC)){
            criteraQuery.orderBy(criteriaBuilder.asc(agentRoot.get(agentPage.getSortBy())));
        }
        else{
            criteraQuery.orderBy(criteriaBuilder.desc(agentRoot.get(agentPage.getSortBy())));
        }
    }

    private Pageable getPagable(AgentPage agentPage) {
        Sort sort = Sort.by(agentPage.getSortDirection(),agentPage.getSortBy());
        return PageRequest.of(agentPage.getPageNumber(),agentPage.getPageSize(),sort);
    }
}
