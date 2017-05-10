
/*
 * eGov suite of products aim to improve the internal efficiency,transparency,
 * accountability and the service delivery of the government  organizations.
 *
 *  Copyright (C) 2016  eGovernments Foundation
 *
 *  The updated version of eGov suite of products as by eGovernments Foundation
 *  is available at http://www.egovernments.org
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program. If not, see http://www.gnu.org/licenses/ or
 *  http://www.gnu.org/licenses/gpl.html .
 *
 *  In addition to the terms of the GPL license to be adhered to in using this
 *  program, the following additional terms are to be complied with:
 *
 *      1) All versions of this program, verbatim or modified must carry this
 *         Legal Notice.
 *
 *      2) Any misrepresentation of the origin of the material is prohibited. It
 *         is required that all modified versions of this material be marked in
 *         reasonable ways as different from the original version.
 *
 *      3) This license does not grant any rights to any user of the program
 *         with regards to rights under trademark law for use of the trade names
 *         or trademarks of eGovernments Foundation.
 *
 *  In case of any queries, you can reach eGovernments Foundation at contact@egovernments.org.
 */

package org.egov.asset.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.egov.asset.contract.AssetRequest;
import org.egov.asset.model.Asset;
import org.egov.asset.model.AssetCriteria;
import org.egov.asset.model.Location;
import org.egov.asset.repository.builder.AssetQueryBuilder;
import org.egov.asset.repository.rowmapper.AssetRowMapper;
import org.egov.common.contract.request.RequestInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class AssetRepository {

	private static final Logger logger = LoggerFactory.getLogger(AssetRepository.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private AssetRowMapper assetRowMapper;

	@Autowired
	private AssetQueryBuilder assetQueryBuilder;

	public List<Asset> findForCriteria(AssetCriteria assetCriteria) {

		List<Object> preparedStatementValues = new ArrayList<>();
		String queryStr = assetQueryBuilder.getQuery(assetCriteria, preparedStatementValues);
		List<Asset> assets = null;
		try {
			logger.info("queryStr::" + queryStr + "preparedStatementValues::" + preparedStatementValues.toString());
			assets = jdbcTemplate.query(queryStr, preparedStatementValues.toArray(), assetRowMapper);
			logger.info("AssetRepository::" + assets);
		} catch (Exception ex) {
			logger.info("the exception from findforcriteria : " + ex);
		}
		return assets;
	}

	public String findAssetName(String tenantId, String name) {

		String queryStr = AssetQueryBuilder.FINDBYNAMEQUERY;
		logger.info("queryStr::" + queryStr + "preparedStatementValues::" + name + "tenantid" + tenantId);
		List<String> assetNameList = new ArrayList<>();
		try {
			assetNameList = jdbcTemplate.queryForList(queryStr, new Object[] { name, tenantId }, String.class);
			logger.info("AssetRepository::" + assetNameList);
		} catch (Exception ex) {
			logger.info("the exception from findbyname method indicates no duplicate assets available : " + ex);
		}
		if (assetNameList.isEmpty())
			return null;
		return assetNameList.get(0);
	}

	public String getAssetCode() {
		String query = "SELECT nextval('seq_egasset_assetcode')";
		Integer result = jdbcTemplate.queryForObject(query, Integer.class);
		logger.info("result:" + result);
		StringBuilder code = null;
		try {
			code = new StringBuilder(String.format("%06d", result));
		} catch (Exception ex) {
			logger.info("the exception from seq number gen for code : " + ex);
		}
		return code.toString();
	}

	public Asset create(AssetRequest assetRequest) {
		RequestInfo requestInfo = assetRequest.getRequestInfo();
		Asset asset = assetRequest.getAsset();

		String property = null;
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.setSerializationInclusion(Include.NON_NULL);
			Asset asset2 = new Asset();
			asset2.setAssetAttributes(asset.getAssetAttributes());
			property = objectMapper.writeValueAsString(asset2);
		} catch (JsonProcessingException e) {
			logger.info("the exception in insert from parsing attributes : " + e);
		}

		String query = assetQueryBuilder.getInsertQuery();

		String modeOfAcquisition = null;
		String status = null;

		if (asset.getModeOfAcquisition() != null)
			modeOfAcquisition = asset.getModeOfAcquisition().toString();

		if (asset.getStatus() != null)
			status = asset.getStatus().toString();

		Location location = asset.getLocationDetails();

		Object[] obj = new Object[] { asset.getAssetCategory().getId(), asset.getName(), asset.getCode(),
				asset.getDepartment().getId(), asset.getAssetDetails(), asset.getDescription(),
				asset.getDateOfCreation(), asset.getRemarks(), asset.getLength(), asset.getWidth(),
				asset.getTotalArea(), modeOfAcquisition, status, asset.getTenantId(), location.getZone(),
				location.getRevenueWard(), location.getStreet(), location.getElectionWard(), location.getDoorNo(),
				location.getPinCode(), location.getLocality(), location.getBlock(), property,
				requestInfo.getUserInfo().getId(), new Date(), requestInfo.getUserInfo().getId(), new Date(),
				asset.getGrossValue(), asset.getAccumulatedDepreciation(), asset.getAssetReference(),
				asset.getVersion() };
		try {
			jdbcTemplate.update(query, obj);
		} catch (Exception ex) {
			logger.info("the exception from insert query : " + ex);
		}

		return asset;
	}

	public Asset update(AssetRequest assetRequest) {

		RequestInfo requestInfo = assetRequest.getRequestInfo();
		Asset asset = assetRequest.getAsset();

		String property = null;
		try {

			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.setSerializationInclusion(Include.NON_NULL);
			Asset asset2 = new Asset();
			asset2.setAssetAttributes(asset.getAssetAttributes());
			property = objectMapper.writeValueAsString(asset2);

		} catch (JsonProcessingException e) {
			logger.info("exception from parsing the assetattributes : " + e);
		}

		String query = assetQueryBuilder.getUpdateQuery();

		logger.info("query::" + query);

		String modeOfAcquisition = null;
		String status = null;

		if (asset.getModeOfAcquisition() != null)
			modeOfAcquisition = asset.getModeOfAcquisition().toString();

		if (asset.getStatus() != null)
			status = asset.getStatus().toString();

		Location location = asset.getLocationDetails();

		Object[] obj = new Object[] { asset.getAssetCategory().getId(), asset.getName(), asset.getDepartment().getId(),
				asset.getAssetDetails(), asset.getDescription(), asset.getRemarks(), asset.getLength(),
				asset.getWidth(), asset.getTotalArea(), modeOfAcquisition, status, location.getZone(),
				location.getRevenueWard(), location.getStreet(), location.getElectionWard(), location.getDoorNo(),
				location.getPinCode(), location.getLocality(), location.getBlock(), property, requestInfo.getMsgId(),
				new Date(), asset.getGrossValue(), asset.getAccumulatedDepreciation(), asset.getAssetReference(),
				asset.getVersion(), asset.getCode(), asset.getTenantId() };
		try {
			logger.info("query1::" + query + "," + Arrays.toString(obj));
			int i = jdbcTemplate.update(query, obj);
			logger.info("output of update query : " + i);
		} catch (Exception ex) {
			logger.info("the exception from update asset : " + ex);
		}
		return asset;
	}
}