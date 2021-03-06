package org.motechproject.carereporting.service;

import org.motechproject.carereporting.domain.AreaEntity;
import org.motechproject.carereporting.domain.DwQueryEntity;
import org.motechproject.carereporting.domain.IndicatorClassificationEntity;
import org.motechproject.carereporting.domain.IndicatorEntity;
import org.motechproject.carereporting.domain.IndicatorTypeEntity;
import org.motechproject.carereporting.domain.IndicatorValueEntity;
import org.motechproject.carereporting.domain.UserEntity;
import org.motechproject.carereporting.domain.dto.DwQueryDto;
import org.motechproject.carereporting.domain.dto.IndicatorCreationFormDto;
import org.motechproject.carereporting.domain.dto.IndicatorDto;
import org.motechproject.carereporting.domain.dto.QueryCreationFormDto;
import org.motechproject.carereporting.domain.dto.TrendIndicatorClassificationDto;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IndicatorService {

    String HAS_ROLE_CAN_CREATE_INDICATORS = "hasRole('CAN_CREATE_INDICATORS')";
    String HAS_ROLE_CAN_REMOVE_INDICATORS = "hasRole('CAN_REMOVE_INDICATORS')";
    String HAS_ROLE_CAN_EDIT_INDICATORS = "hasRole('CAN_EDIT_INDICATORS')";
    String HAS_ROLE_CAN_CREATE_CLASSIFICATIONS = "hasRole('CAN_CREATE_CLASSIFICATIONS')";
    String HAS_ROLE_CAN_EDIT_CLASSIFICATIONS = "hasRole('CAN_EDIT_CLASSIFICATIONS')";
    String HAS_ROLE_CAN_REMOVE_CLASSIFICATIONS = "hasRole('CAN_REMOVE_CLASSIFICATIONS')";

    Set<IndicatorEntity> getAllIndicators();

    Set<IndicatorEntity> getIndicatorsByClassificationId(Integer classificationId);

    IndicatorEntity getIndicatorById(Integer id);

    IndicatorEntity getIndicatorWithAllFields(Integer id);

    void createNewIndicator(IndicatorEntity indicatorEntity);

    IndicatorEntity createIndicatorEntityFromDto(IndicatorDto indicatorDto);

    @PreAuthorize(HAS_ROLE_CAN_EDIT_INDICATORS)
    void updateIndicator(IndicatorEntity indicatorEntity);

    void setComputedForIndicator(IndicatorEntity indicatorEntity, Boolean value);

    @PreAuthorize(HAS_ROLE_CAN_EDIT_INDICATORS)
    void updateIndicatorFromDto(Integer id, IndicatorDto indicatorDto);

    @PreAuthorize(HAS_ROLE_CAN_REMOVE_INDICATORS)
    void deleteIndicator(IndicatorEntity indicatorEntity);

    Set<IndicatorTypeEntity> getAllIndicatorTypes();

    IndicatorTypeEntity getIndicatorTypeById(Integer id);

    Set<IndicatorClassificationEntity> getAllIndicatorClassifications();

    IndicatorClassificationEntity getIndicatorClassificationById(Integer id);

    @PreAuthorize(HAS_ROLE_CAN_CREATE_CLASSIFICATIONS)
    void createNewIndicatorClassification(IndicatorClassificationEntity indicatorClassificationEntity);

    @PreAuthorize(HAS_ROLE_CAN_EDIT_CLASSIFICATIONS)
    void updateIndicatorClassification(IndicatorClassificationEntity indicatorClassificationEntity);

    @PreAuthorize(HAS_ROLE_CAN_REMOVE_CLASSIFICATIONS)
    void deleteIndicatorClassification(IndicatorClassificationEntity indicatorClassificationEntity);

    Set<IndicatorValueEntity> getAllIndicatorValues();

    void createNewIndicatorValue(IndicatorValueEntity indicatorValueEntity);

    void updateIndicatorValue(IndicatorValueEntity indicatorValueEntity);

    List<IndicatorValueEntity> getIndicatorValuesForArea(Integer indicatorId, Integer areaId, Integer frequencyId,
                                                         Date startDate, Date endDate, String category);

    Set<TrendIndicatorClassificationDto> getIndicatorsWithTrendsUnderUser(UserEntity user, Date startDate, Date endDate, Integer areaId, Integer frequencyId);

    Map<AreaEntity, Integer> getIndicatorTrendForChildAreas(Integer indicatorId, Integer parentAreaId, Integer frequencyId, Date startDate, Date endDate);

    byte[] getCaseListReportAsCsv(IndicatorEntity indicatorEntity, Integer areaId, Date fromDate, Date toDate);

    void calculateIndicator(IndicatorEntity indicatorEntity);

    void calculateAllIndicators(Integer classificationId);

    IndicatorCreationFormDto getIndicatorCreationFormDto();

    QueryCreationFormDto getIndicatorQueryCreationFormDto();

    Set<IndicatorEntity> getAllIndicatorsByUserAccess(UserEntity userEntity);

    List<IndicatorValueEntity> getIndicatorValuesForCsv(Integer indicatorId, Integer areaId, Integer frequencyId, Date startDate, Date endDate);

    Set<DwQueryEntity> getAllTopLevelDwQueries();

    DwQueryEntity getDwQueryById(Integer dwQueryId);

    DwQueryEntity getDwQueryByName(String dwQueryName);

    DwQueryDto getDwQueryDtoForQuery(Integer dwQueryId);

    void createNewDwQuery(DwQueryDto dwQueryDto);

    void deepCopyDwQueryAndSave(String dwQueryName, DwQueryEntity clonedEntity);

    void validateDwQuery(DwQueryEntity dwQueryEntity);

    void updateDwQuery(DwQueryEntity dwQueryEntity);

    void updateDwQueryFromDto(DwQueryDto dwQueryDto, Integer queryId);

    void deleteDwQuery(DwQueryEntity dwQueryEntity);

    String getDwQuerySqlString(DwQueryEntity dwQueryEntity);

}
