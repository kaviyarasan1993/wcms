package org.egov.works.commons.domain.model;

import java.math.BigDecimal;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * An Object that holds Overhead Rates for a given Overhead
 */
@ApiModel(description = "An Object that holds Overhead Rates for a given Overhead")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-10-24T13:08:31.335Z")

public class OverheadRate {
	@JsonProperty("id")
	private String id = null;

	@JsonProperty("tenantId")
	private String tenantId = null;

	@JsonProperty("overhead")
	private Overhead overhead = null;

	@JsonProperty("startDate")
	private Long startDate = null;

	@JsonProperty("endDate")
	private Long endDate = null;

	@JsonProperty("lumpsumAmount")
	private BigDecimal lumpsumAmount = null;

	@JsonProperty("percentage")
	private Double percentage = null;

	@JsonProperty("auditDetails")
	private AuditDetails auditDetails = null;

	public OverheadRate id(String id) {
		this.id = id;
		return this;
	}

	/**
	 * Unique Identifier of the Overhead Rate
	 * 
	 * @return id
	 **/
	@ApiModelProperty(value = "Unique Identifier of the Overhead Rate")

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public OverheadRate tenantId(String tenantId) {
		this.tenantId = tenantId;
		return this;
	}

	/**
	 * Tenant id of the Overhead Rate
	 * 
	 * @return tenantId
	 **/
	@ApiModelProperty(required = true, value = "Tenant id of the Overhead Rate")
	@NotNull

	@Size(min = 4, max = 128)
	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public OverheadRate overhead(Overhead overhead) {
		this.overhead = overhead;
		return this;
	}

	/**
	 * Get overhead
	 * 
	 * @return overhead
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	@Valid

	public Overhead getOverhead() {
		return overhead;
	}

	public void setOverhead(Overhead overhead) {
		this.overhead = overhead;
	}

	public OverheadRate startDate(Long startDate) {
		this.startDate = startDate;
		return this;
	}

	/**
	 * Epoch time of the Start date of Overhead Rates
	 * 
	 * @return startDate
	 **/
	@ApiModelProperty(required = true, value = "Epoch time of the Start date of Overhead Rates")
	@NotNull

	public Long getStartDate() {
		return startDate;
	}

	public void setStartDate(Long startDate) {
		this.startDate = startDate;
	}

	public OverheadRate endDate(Long endDate) {
		this.endDate = endDate;
		return this;
	}

	/**
	 * Epoch time of the end date of Overhead Rates
	 * 
	 * @return endDate
	 **/
	@ApiModelProperty(required = true, value = "Epoch time of the end date of Overhead Rates")
	@NotNull

	public Long getEndDate() {
		return endDate;
	}

	public void setEndDate(Long endDate) {
		this.endDate = endDate;
	}

	public OverheadRate lumpsumAmount(BigDecimal lumpsumAmount) {
		this.lumpsumAmount = lumpsumAmount;
		return this;
	}

	/**
	 * Lumpsum Amount to be applied for the given overhead, Either of the
	 * Lumpsum Amount or Percentage is mandatory
	 * 
	 * @return lumpsumAmount
	 **/
	@ApiModelProperty(value = "Lumpsum Amount to be applied for the given overhead, Either of the Lumpsum Amount or Percentage is mandatory")

	@Valid

	public BigDecimal getLumpsumAmount() {
		return lumpsumAmount;
	}

	public void setLumpsumAmount(BigDecimal lumpsumAmount) {
		this.lumpsumAmount = lumpsumAmount;
	}

	public OverheadRate percentage(Double percentage) {
		this.percentage = percentage;
		return this;
	}

	/**
	 * Percentage to be applied for the given overhead, Either of the Lumpsum
	 * Amount or Percentage is mandatory
	 * 
	 * @return percentage
	 **/
	@ApiModelProperty(value = "Percentage to be applied for the given overhead, Either of the Lumpsum Amount or Percentage is mandatory")

	public Double getPercentage() {
		return percentage;
	}

	public void setPercentage(Double percentage) {
		this.percentage = percentage;
	}

	public OverheadRate auditDetails(AuditDetails auditDetails) {
		this.auditDetails = auditDetails;
		return this;
	}

	/**
	 * Get auditDetails
	 * 
	 * @return auditDetails
	 **/
	@ApiModelProperty(value = "")

	@Valid

	public AuditDetails getAuditDetails() {
		return auditDetails;
	}

	public void setAuditDetails(AuditDetails auditDetails) {
		this.auditDetails = auditDetails;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		OverheadRate overheadRate = (OverheadRate) o;
		return Objects.equals(this.id, overheadRate.id) && Objects.equals(this.tenantId, overheadRate.tenantId)
				&& Objects.equals(this.overhead, overheadRate.overhead)
				&& Objects.equals(this.startDate, overheadRate.startDate)
				&& Objects.equals(this.endDate, overheadRate.endDate)
				&& Objects.equals(this.lumpsumAmount, overheadRate.lumpsumAmount)
				&& Objects.equals(this.percentage, overheadRate.percentage)
				&& Objects.equals(this.auditDetails, overheadRate.auditDetails);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, tenantId, overhead, startDate, endDate, lumpsumAmount, percentage, auditDetails);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class OverheadRate {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    tenantId: ").append(toIndentedString(tenantId)).append("\n");
		sb.append("    overhead: ").append(toIndentedString(overhead)).append("\n");
		sb.append("    startDate: ").append(toIndentedString(startDate)).append("\n");
		sb.append("    endDate: ").append(toIndentedString(endDate)).append("\n");
		sb.append("    lumpsumAmount: ").append(toIndentedString(lumpsumAmount)).append("\n");
		sb.append("    percentage: ").append(toIndentedString(percentage)).append("\n");
		sb.append("    auditDetails: ").append(toIndentedString(auditDetails)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
