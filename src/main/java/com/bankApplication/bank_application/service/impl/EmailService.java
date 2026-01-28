package com.bankApplication.bank_application.service.impl;

import com.bankApplication.bank_application.dto.EmailDetails;

public interface EmailService {

    void sendEmailAlert(EmailDetails emailDetails);
}
