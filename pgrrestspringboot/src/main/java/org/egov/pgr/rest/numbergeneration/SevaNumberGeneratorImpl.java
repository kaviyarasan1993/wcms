package org.egov.pgr.rest.numbergeneration;

import java.io.Serializable;
import java.sql.SQLException;

import org.egov.pgr.persistence.utils.DBSequenceGenerator;
import org.egov.pgr.persistence.utils.DateUtils;
import org.egov.pgr.persistence.utils.SequenceNumberGenerator;
import org.hibernate.exception.SQLGrammarException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.StringUtils.upperCase;

@Service
public class SevaNumberGeneratorImpl implements SevaNumberGenerator {

	 private static final String APP_NUMBER_SEQ_PREFIX = "SEQ_APPLICATION_NUMBER%s";
	   
	    @Autowired
	    private DBSequenceGenerator dbSequenceGenerator;

	    @Autowired
	    private SequenceNumberGenerator sequenceNumberGenerator;

	    @Transactional
	    public String generate() throws Exception {
	        try {
	            final String currentYear = DateUtils.currentDateToYearFormat();
	            final String sequenceName = String.format(APP_NUMBER_SEQ_PREFIX, currentYear);
	            Serializable sequenceNumber;
	            try {
	                sequenceNumber = sequenceNumberGenerator.getNextSequence(sequenceName);
	            } catch (final SQLGrammarException e) {
	                sequenceNumber = dbSequenceGenerator.createAndGetNextSequence(sequenceName);
	            }
	            return String.format("%05d-%s-%s", sequenceNumber, currentYear, upperCase(randomAlphabetic(2)));
	        } catch (final SQLException e) {
	            throw new Exception("Error occurred while generating Application Number", e);
	        }
	    }
}
