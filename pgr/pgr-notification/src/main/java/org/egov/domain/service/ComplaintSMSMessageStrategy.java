package org.egov.domain.service;

import org.egov.domain.model.SMSMessageContext;
import org.egov.domain.model.ServiceType;
import org.egov.domain.model.SevaRequest;
import org.egov.domain.model.Tenant;
import org.trimou.util.ImmutableMap;

import java.util.Map;

public class ComplaintSMSMessageStrategy implements SMSMessageStrategy {
    private static final String NAME = "name";
    private static final String NUMBER = "number";
    private static final String STATUS = "status";
    private static final String SMS_COMPLAINT_SERVICE_REQUEST = "sms_complaint_service_request";

    @Override
    public boolean matches(SevaRequest sevaRequest, ServiceType serviceType) {
        return serviceType.isComplaintType();
    }

    @Override
    public SMSMessageContext getMessageContext(SevaRequest sevaRequest, ServiceType serviceType, Tenant tenant) {
        final Map<Object, Object> map = ImmutableMap.of(
            NAME, serviceType.getName(),
            NUMBER, sevaRequest.getCrn(),
            STATUS, sevaRequest.getStatusName().toLowerCase()
        );
        return new SMSMessageContext(SMS_COMPLAINT_SERVICE_REQUEST, map);
    }
}


