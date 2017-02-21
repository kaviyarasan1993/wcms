package org.egov.egf.persistence.repository;


import org.egov.egf.persistence.entity.Function;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


@Repository 
public interface FunctionRepository extends JpaRepository<Function,java.lang.Long>,JpaSpecificationExecutor<Function>  {

Function findByName(String name);

Function findByCode(String code);

}