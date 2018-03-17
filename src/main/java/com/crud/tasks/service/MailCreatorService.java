package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.config.CompanyConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailCreatorService {
    @Autowired
    private CompanyConfig companyConfig;

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message){
        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("preview_message", "How are You today? ");
        context.setVariable("goodbye_message", "C ya later. ");
        context.setVariable("tasks_url", "http://localhost:8888/crud");
        context.setVariable("button","Visit website");
        context.setVariable("company_details", companyConfig.getCompanyName()
                + " " + companyConfig.getCompanyGoal()
                + " " + companyConfig.getCompanyEmail()
                + " " + companyConfig.getCompanyPhone());
        context.setVariable("admin_name", adminConfig.getAdminName());
        return templateEngine.process("mail/created-trello-card-mail", context);
    }
}
