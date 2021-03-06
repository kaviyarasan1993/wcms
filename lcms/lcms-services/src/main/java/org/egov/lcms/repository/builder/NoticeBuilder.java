package org.egov.lcms.repository.builder;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.egov.lcms.models.NoticeSearchCriteria;

/** 
 * 
 * @author			Date			eGov-JIRA ticket			Commit message
 * ---------------------------------------------------------------------------
 * Yosadhara	31st Oct 2017								Initial commit of  Notice QueryBuilder
 * Yosadhara    08th Nov 2017                               Modified ORDER BY clause based on lastmodifiedtime
 */
public class NoticeBuilder {
		
	private static final String BASE_QUERY = "SELECT * from egov_lcms_notice ";
	
	/**
	 * This method is to build SELECT query to serch Notice
	 * 
	 * @param noticeSearchCriteria
	 * @param preparedStatementValues
	 * @return String
	 */
	public static String getSearchQuery(NoticeSearchCriteria noticeSearchCriteria,
			List<Object> preparedStatementValues) {

		StringBuffer selectQuery = new StringBuffer(BASE_QUERY);
		addWhereClause(selectQuery, preparedStatementValues, noticeSearchCriteria);
		addOrderByClause(selectQuery, noticeSearchCriteria);
		addPagingClause(selectQuery, preparedStatementValues, noticeSearchCriteria);

		return selectQuery.toString();
	}
	
	/**
	 * This method is to append WHERE clause and condtions to SELECT Query
	 * 
	 * @param selectQuery
	 * @param preparedStatementValues
	 * @param noticeSearchCriteria
	 */
	private static void addWhereClause(StringBuffer selectQuery, List<Object> preparedStatementValues,
			NoticeSearchCriteria noticeSearchCriteria) {

		if (noticeSearchCriteria.getTenantId() == null && noticeSearchCriteria.getCode() == null
				&& noticeSearchCriteria.getExhibitNo() == null && noticeSearchCriteria.getApplicant() == null
				&& noticeSearchCriteria.getDefendant() == null && noticeSearchCriteria.getAdvocateName() == null
				&& noticeSearchCriteria.getNoticeType() == null)
			return;

		selectQuery.append(" WHERE");

		if (noticeSearchCriteria.getTenantId() != null) {

			selectQuery.append(" LOWER(tenantId) =LOWER(?)");
			preparedStatementValues.add(noticeSearchCriteria.getTenantId());
		}

		if (noticeSearchCriteria.getExhibitNo() != null) {

			selectQuery.append(" AND exhibitNo = ?");
			preparedStatementValues.add(noticeSearchCriteria.getExhibitNo());
		}

		if (noticeSearchCriteria.getApplicant() != null) {

			selectQuery.append(" AND applicant = ?");
			preparedStatementValues.add(noticeSearchCriteria.getApplicant());
		}

		if (noticeSearchCriteria.getDefendant() != null) {

			selectQuery.append(" AND defendant = ?");
			preparedStatementValues.add(noticeSearchCriteria.getDefendant());
		}

		if (noticeSearchCriteria.getAdvocateName() != null) {

			selectQuery.append(" AND advocateName = ?");
			preparedStatementValues.add(noticeSearchCriteria.getAdvocateName());
		}

		if (noticeSearchCriteria.getNoticeType() != null) {

			selectQuery.append(" AND noticeType = ?");
			preparedStatementValues.add(noticeSearchCriteria.getNoticeType());
		}

		if (noticeSearchCriteria.getCode() != null) {

			selectQuery.append(" AND code IN ("
					+ Stream.of(noticeSearchCriteria.getCode()).collect(Collectors.joining("','", "'", "'")) + ")");
		}
	}
	
	/**
	 * This method is to append ORDER BY clause to SELECT query
	 * 
	 * @param selectQuery
	 * @param noticeSearchCriteria
	 */
	private static void addOrderByClause(StringBuffer selectQuery, NoticeSearchCriteria noticeSearchCriteria) {

		selectQuery.append(" ORDER BY ");

		if (noticeSearchCriteria.getSort() != null && noticeSearchCriteria.getSort().length > 0) {

			int orderBycount = 1;
			StringBuffer orderByCondition = new StringBuffer();

			for (String order : noticeSearchCriteria.getSort()) {
				if (orderBycount < noticeSearchCriteria.getSort().length)
					orderByCondition.append(order + ",");
				else
					orderByCondition.append(order);
				orderBycount++;
			}

			if (orderBycount > 1)
				orderByCondition.append(" desc");

			selectQuery.append(orderByCondition.toString());
		} else {
			selectQuery.append(" lastmodifiedtime desc ");
		}
	}
	
	/**
	 * This method is to append offset, and limit to SELECT query
	 * 
	 * @param selectQuery
	 * @param preparedStatementValues
	 * @param noticeSearchCriteria
	 */
	private static void addPagingClause(StringBuffer selectQuery, List<Object> preparedStatementValues,
			NoticeSearchCriteria noticeSearchCriteria) {

		int pageNumber = noticeSearchCriteria.getPageNumber();
		int pageSize = noticeSearchCriteria.getPageSize();
		int offset = 0;
		int limit = 0;
		limit = pageNumber * pageSize;

		if (pageNumber <= 1)
			offset = (limit - pageSize);
		else
			offset = (limit - pageSize) + 1;

		selectQuery.append(" offset ?  limit ?");
		preparedStatementValues.add(offset);
		preparedStatementValues.add(limit);

	}

}
