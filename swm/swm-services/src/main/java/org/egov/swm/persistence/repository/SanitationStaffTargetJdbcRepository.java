package org.egov.swm.persistence.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.egov.swm.domain.model.CollectionPoint;
import org.egov.swm.domain.model.CollectionPointSearch;
import org.egov.swm.domain.model.Pagination;
import org.egov.swm.domain.model.SanitationStaffTarget;
import org.egov.swm.domain.model.SanitationStaffTargetMap;
import org.egov.swm.domain.model.SanitationStaffTargetSearch;
import org.egov.swm.persistence.entity.SanitationStaffTargetEntity;
import org.egov.tracer.model.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class SanitationStaffTargetJdbcRepository {

	@Autowired
	public JdbcTemplate jdbcTemplate;

	@Autowired
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public SanitationStaffTargetMapJdbcRepository sanitationStaffTargetMapJdbcRepository;

	@Autowired
	public CollectionPointJdbcRepository collectionPointJdbcRepository;

	public Pagination<SanitationStaffTarget> search(SanitationStaffTargetSearch searchRequest) {

		String searchQuery = "select * from egswm_sanitationstafftarget :condition  :orderby ";

		Map<String, Object> paramValues = new HashMap<>();
		StringBuffer params = new StringBuffer();

		if (searchRequest.getSortBy() != null && !searchRequest.getSortBy().isEmpty()) {
			validateSortByOrder(searchRequest.getSortBy());
			validateEntityFieldName(searchRequest.getSortBy(), SanitationStaffTargetSearch.class);
		}

		String orderBy = "order by targetNo";
		if (searchRequest.getSortBy() != null && !searchRequest.getSortBy().isEmpty()) {
			orderBy = "order by " + searchRequest.getSortBy();
		}

		if (searchRequest.getTargetNo() != null) {
			if (params.length() > 0) {
				params.append(" and ");
			}
			params.append("targetNo in (:targetNo)");
			paramValues.put("targetNo", searchRequest.getTargetNo());
		}

		if (searchRequest.getTargetNos() != null) {
			if (params.length() > 0) {
				params.append(" and ");
			}
			params.append("targetNo in (:targetNos)");
			paramValues.put("targetNos", new ArrayList<String>(Arrays.asList(searchRequest.getTargetNos().split(","))));
		}
		if (searchRequest.getTenantId() != null) {
			if (params.length() > 0) {
				params.append(" and ");
			}
			params.append("tenantId =:tenantId");
			paramValues.put("tenantId", searchRequest.getTenantId());
		}

		if (searchRequest.getRouteCode() != null) {
			if (params.length() > 0) {
				params.append(" and ");
			}
			params.append("route =:route");
			paramValues.put("route", searchRequest.getRouteCode());
		}

		if (searchRequest.getSwmProcessCode() != null) {
			if (params.length() > 0) {
				params.append(" and ");
			}
			params.append("swmProcess =:swmProcess");
			paramValues.put("swmProcess", searchRequest.getSwmProcessCode());
		}

		if (searchRequest.getEmployeeCode() != null) {
			if (params.length() > 0) {
				params.append(" and ");
			}
			params.append("employee =:employee");
			paramValues.put("employee", searchRequest.getEmployeeCode());
		}

		Pagination<SanitationStaffTarget> page = new Pagination<>();
		if (searchRequest.getOffset() != null) {
			page.setOffset(searchRequest.getOffset());
		}
		if (searchRequest.getPageSize() != null) {
			page.setPageSize(searchRequest.getPageSize());
		}

		if (params.length() > 0) {

			searchQuery = searchQuery.replace(":condition", " where " + params.toString());

		} else

			searchQuery = searchQuery.replace(":condition", "");

		searchQuery = searchQuery.replace(":orderby", orderBy);

		page = (Pagination<SanitationStaffTarget>) getPagination(searchQuery, page, paramValues);
		searchQuery = searchQuery + " :pagination";

		searchQuery = searchQuery.replace(":pagination",
				"limit " + page.getPageSize() + " offset " + page.getOffset() * page.getPageSize());

		BeanPropertyRowMapper row = new BeanPropertyRowMapper(SanitationStaffTargetEntity.class);

		List<SanitationStaffTarget> sanitationStaffTargetList = new ArrayList<>();

		List<SanitationStaffTargetEntity> sanitationStaffTargetEntities = namedParameterJdbcTemplate
				.query(searchQuery.toString(), paramValues, row);

		SanitationStaffTarget sst;
		StringBuffer cpCodes = new StringBuffer();
		SanitationStaffTargetMap sstm;
		List<SanitationStaffTargetMap> collectionPoints;
		CollectionPointSearch cps;
		Pagination<CollectionPoint> collectionPointList;
		for (SanitationStaffTargetEntity sanitationStaffTargetEntity : sanitationStaffTargetEntities) {

			sstm = SanitationStaffTargetMap.builder().sanitationStaffTarget(sanitationStaffTargetEntity.getTargetNo())
					.build();
			sstm.setTenantId(sanitationStaffTargetEntity.getTenantId());
			collectionPoints = sanitationStaffTargetMapJdbcRepository.search(sstm);
			if (collectionPoints != null)
				for (SanitationStaffTargetMap map : collectionPoints) {
					if (cpCodes.length() > 0)
						cpCodes.append(",");
					cpCodes.append(map.getCollectionPoint());
				}
			cps = new CollectionPointSearch();
			cps.setCodes(cpCodes.toString());
			collectionPointList = collectionPointJdbcRepository.search(cps);
			sst = sanitationStaffTargetEntity.toDomain();
			sst.setCollectionPoints(collectionPointList.getPagedData());
			sanitationStaffTargetList.add(sst);

		}

		page.setTotalResults(sanitationStaffTargetList.size());

		page.setPagedData(sanitationStaffTargetList);

		return page;
	}

	public Pagination<?> getPagination(String searchQuery, Pagination<?> page, Map<String, Object> paramValues) {
		String countQuery = "select count(*) from (" + searchQuery + ") as x";
		Long count = namedParameterJdbcTemplate.queryForObject(countQuery.toString(), paramValues, Long.class);
		Integer totalpages = (int) Math.ceil((double) count / page.getPageSize());
		page.setTotalPages(totalpages);
		page.setCurrentPage(page.getOffset());
		return page;
	}

	public void validateSortByOrder(final String sortBy) {
		List<String> sortByList = new ArrayList<String>();
		if (sortBy.contains(",")) {
			sortByList = Arrays.asList(sortBy.split(","));
		} else {
			sortByList = Arrays.asList(sortBy);
		}
		for (String s : sortByList) {
			if (s.contains(" ")
					&& (!s.toLowerCase().trim().endsWith("asc") && !s.toLowerCase().trim().endsWith("desc"))) {

				throw new CustomException(s.split(" ")[0],
						"Please send the proper sortBy order for the field " + s.split(" ")[0]);
			}
		}

	}

	public void validateEntityFieldName(String sortBy, final Class<?> object) {
		List<String> sortByList = new ArrayList<String>();
		if (sortBy.contains(",")) {
			sortByList = Arrays.asList(sortBy.split(","));
		} else {
			sortByList = Arrays.asList(sortBy);
		}
		Boolean isFieldExist = Boolean.FALSE;
		for (String s : sortByList) {
			for (int i = 0; i < object.getDeclaredFields().length; i++) {
				if (object.getDeclaredFields()[i].getName().equals(s.contains(" ") ? s.split(" ")[0] : s)) {
					isFieldExist = Boolean.TRUE;
					break;
				} else {
					isFieldExist = Boolean.FALSE;
				}
			}
			if (!isFieldExist) {
				throw new CustomException(s.contains(" ") ? s.split(" ")[0] : s, "Please send the proper Field Names ");

			}
		}

	}

}