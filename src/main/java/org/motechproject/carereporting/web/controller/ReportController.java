package org.motechproject.carereporting.web.controller;

import org.motechproject.carereporting.domain.ReportEntity;
import org.motechproject.carereporting.domain.ReportTypeEntity;
import org.motechproject.carereporting.domain.forms.ReportForm;
import org.motechproject.carereporting.exception.CareApiRuntimeException;
import org.motechproject.carereporting.exception.EntityException;
import org.motechproject.carereporting.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;
import java.util.Set;

@RequestMapping(value = "/api/report")
@Controller
public class ReportController {

    @Autowired
    private ReportService reportService;

    @RequestMapping(value = "/type", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Set<ReportTypeEntity> getAllReportTypes() {
        return reportService.findAllReportTypes();
    }

    @RequestMapping(value = "/{reportId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ReportEntity getReportById(@PathVariable Integer reportId) {
        return reportService.findReportById(reportId);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Set<ReportEntity> getAllReports() {
        return reportService.findAllReports();
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void saveReport(@RequestBody @Valid ReportForm reportForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new CareApiRuntimeException(bindingResult.getFieldErrors());
        }
        try {
            reportService.createNewReport(
                    reportForm.getName(),
                    reportForm.getIndicatorId(),
                    reportForm.getReportTypeId());
        } catch (EntityException e) {
            bindingResult.rejectValue("name", "Duplicate.reportForm.name");
            throw new CareApiRuntimeException(bindingResult.getFieldErrors(), e);
        }
    }

    @RequestMapping(value = "/{reportId}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateReport(@PathVariable Integer reportId, @RequestBody @Valid ReportForm reportForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new CareApiRuntimeException(bindingResult.getFieldErrors());
        }
        try {
            reportService.updateReport(
                    reportId,
                    reportForm.getName(),
                    reportForm.getIndicatorId(),
                    reportForm.getReportTypeId());
        } catch (EntityException e) {
            bindingResult.rejectValue("name", "Duplicate.reportForm.name");
            throw new CareApiRuntimeException(bindingResult.getFieldErrors(), e);
        }
    }

    @RequestMapping(value = "/{reportId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteReport(@PathVariable Integer reportId) {
        reportService.deleteReportById(reportId);
    }

}