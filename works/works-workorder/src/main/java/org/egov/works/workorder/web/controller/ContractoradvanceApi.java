/**
 * NOTE: This class is auto generated by the swagger code generator program (2.2.3).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package org.egov.works.workorder.web.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.egov.tracer.model.ErrorRes;
import org.egov.works.workorder.web.contract.ContractorAdvanceRequisitionRequest;
import org.egov.works.workorder.web.contract.ContractorAdvanceRequisitionResponse;
import org.egov.works.workorder.web.contract.RequestInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-01-04T13:02:13.606Z")

@Api(value = "contractoradvance", description = "the contractoradvance API")
public interface ContractoradvanceApi {

    @ApiOperation(value = "Create new Contractor Advance Requisition(s).", notes = "To create new Contractor Advance Requisition Form in the system. API supports bulk creation with max limit as defined in the Contractor Advance Requisition Form Request. Please note that either whole batch succeeds or fails, there's no partial batch success. To create one Contractor Advance Requisition Form, please pass array with one Contractor Advance Requisition Form object. ", response = ContractorAdvanceRequisitionResponse.class, tags = {
            "Contractor Advance Requisition", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "ReponseInfo with Contractor Advance Requisition Form(s) created successfully", response = ContractorAdvanceRequisitionResponse.class),
            @ApiResponse(code = 400, message = "Contractor Advance Requisition Form(s) creation failed", response = ErrorRes.class) })

    @RequestMapping(value = "/contractoradvance/_create", method = RequestMethod.POST)
    ResponseEntity<ContractorAdvanceRequisitionResponse> contractoradvanceCreatePost(
            @ApiParam(value = "Details for the new Contractor Advance Requisition Form(s) + RequestInfo meta data.", required = true) @Valid @RequestBody ContractorAdvanceRequisitionRequest contractorAdvanceRequisitionRequest);

    @ApiOperation(value = "Get the list of Advance Requisition(s) defined in the system.", notes = "Search and get Advance Requisition(s) based on defined search criteria. Currently search parameters are only allowed as HTTP query params.  In case multiple parameters are passed Advance Requisition(s) will be searched as an AND combination of all the parameters.  Maximum result size is restricted based on the maxlength of Advance Requisition as defined in AdvanceRequisitionResponse model.  Search results will be sorted by the sortProperty Provided in the parameters ", response = ContractorAdvanceRequisitionResponse.class, tags = {
            "Contractor Advance Requisition", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Advance Requisition(s) Retrieved Successfully", response = ContractorAdvanceRequisitionResponse.class),
            @ApiResponse(code = 400, message = "Invalid input.", response = ErrorRes.class) })

    @RequestMapping(value = "/contractoradvance/_search", method = RequestMethod.POST)
    ResponseEntity<ContractorAdvanceRequisitionResponse> contractoradvanceSearchPost(
            @NotNull @ApiParam(value = "Unique id for a tenant.", required = true) @RequestParam(value = "tenantId", required = true) String tenantId,
            @ApiParam(value = "Parameter to carry Request metadata in the request body") @Valid @RequestBody RequestInfo requestInfo,
            @Min(0) @Max(100) @ApiParam(value = "Number of records returned.", defaultValue = "20") @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize,
            @ApiParam(value = "Page number", defaultValue = "1") @RequestParam(value = "pageNumber", required = false, defaultValue = "1") Integer pageNumber,
            @ApiParam(value = "This takes any field from the Object seperated by comma and asc,desc keywords. example name asc,code desc or name,code or name,code desc", defaultValue = "id") @RequestParam(value = "sortBy", required = false, defaultValue = "id") String sortBy,
            @Size(max = 50) @ApiParam(value = "Comma separated list of Ids of Contractor Advance Requistion to get the Contractor Advance Requistions") @RequestParam(value = "ids", required = false) List<String> ids,
            @Size(max = 50) @ApiParam(value = "Comma separated list of Contractor Advance Requistion Numbers of  Contractor Advance Requistion to get the Contractor Advance Requistions") @RequestParam(value = "advanceRequisitionNumbers", required = false) List<String> advanceRequisitionNumbers,
            @Size(max = 50) @ApiParam(value = "Comma separated list of Letter of Acceptance Numbers") @RequestParam(value = "loaNumbers", required = false) List<String> loaNumbers,
            @ApiParam(value = "Epoch time from Advance Requisition Created Date") @RequestParam(value = "fromDate", required = false) Long fromDate,
            @ApiParam(value = "Epoch time till the Advance Requisition Created Date") @RequestParam(value = "toDate", required = false) Long toDate,
            @Size(max = 50) @ApiParam(value = "Comma separated list of Status of the Advance Requisition") @RequestParam(value = "statuses", required = false) List<String> statuses,
            @Size(max = 50) @ApiParam(value = "Comma separated list of Advance Requisition Bill Numbers") @RequestParam(value = "advanceBillNumbers", required = false) List<String> advanceBillNumbers,
            @Size(max = 50) @ApiParam(value = "Comma separated list of Names of the contractor to which Advance Requisition belongs to") @RequestParam(value = "contractorNames", required = false) List<String> contractorNames,
            @Size(max = 50) @ApiParam(value = "Comma separated list of codes of the contractor to which Advance Requisition belongs to") @RequestParam(value = "contractorCodes", required = false) List<String> contractorCodes);

    @ApiOperation(value = "update existing Contractor Advance Requisition(s).", notes = "To update existing Contractor Advance Requisition Form in the system. API supports bulk updation with max limit as defined in the Contractor Advance Requisition Form Request. Please note that either whole batch succeeds or fails, there's no partial batch success. To update one Contractor Advance Requisition Form, please pass array with one Contractor Advance Requisition Form object. ", response = ContractorAdvanceRequisitionResponse.class, tags = {
            "Contractor Advance Requisition", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "ReponseInfo with Contractor Advance Requisition Form(s) updated successfully", response = ContractorAdvanceRequisitionResponse.class),
            @ApiResponse(code = 400, message = "Contractor Advance Requisition Form(s) updation failed", response = ErrorRes.class) })

    @RequestMapping(value = "/contractoradvance/_update", method = RequestMethod.POST)
    ResponseEntity<ContractorAdvanceRequisitionResponse> contractoradvanceUpdatePost(
            @ApiParam(value = "Details of the Contractor Advance Requisition Form(s) + RequestInfo meta data.", required = true) @Valid @RequestBody ContractorAdvanceRequisitionRequest contractorAdvanceRequisitionRequest);

}