package org.egov.egf.bill.domain.model;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BillRegisterSearch extends BillRegister {

    private String ids;

    private String sortBy;

    private Integer pageSize;

    private Integer offset;

    private String glcode;

    private String glcodes;

    private BigDecimal debitAmount;

    private BigDecimal creditAmount;

    private String accountDetailTypeId;

    private String accountDetailKeyId;

    private BigDecimal subLedgerAmount;

    private String types;

    private String names;

    private String billNumbers;

    private Date billFromDate;

    private Date billToDate;

    private String fundCode;

    private String functionCode;

    private String functionaryCode;

    private String fundSourceCode;

    private String schemeCode;

    private String subSchemeCode;

    private String statusCode;

    private String statusCodes;

    private String locationCode;

    private String departmentCode;

    private String departmentCodes;
}