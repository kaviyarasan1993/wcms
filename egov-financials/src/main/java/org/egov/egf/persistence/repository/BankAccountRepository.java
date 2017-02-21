package org.egov.egf.persistence.repository;


import org.egov.egf.persistence.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


@Repository 
public interface BankAccountRepository extends JpaRepository<BankAccount,java.lang.Long>,JpaSpecificationExecutor<BankAccount>  {

}