package org.motechproject.carereporting.xml.mapping;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "type")
@XmlEnum
public enum WhereConditionType {
    @XmlEnumValue("valueComparison") VALUE_COMPARISON,
    @XmlEnumValue("dateDiff") DATE_DIFF,
    @XmlEnumValue("fieldComparison") FIELD_COMPARISON,
    @XmlEnumValue("dateWithOffsetDiff") DATE_WITH_OFFSET_DIFF,
    @XmlEnumValue("dateRange") DATE_RANGE,
    @XmlEnumValue("enumRange") ENUM_RANGE
}