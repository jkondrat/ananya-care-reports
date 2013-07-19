package org.motechproject.carereporting.domain.dto;

import org.codehaus.jackson.map.annotate.JsonView;
import org.motechproject.carereporting.domain.views.TrendJsonView;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class TrendIndicatorCategoryDto implements Serializable {

    protected static final long serialVersionUID = 0L;

    @JsonView(TrendJsonView.class)
    private final Set<IndicatorWithTrendDto> indicators = new HashSet<>();

    @JsonView(TrendJsonView.class)
    private final String name;

    public TrendIndicatorCategoryDto(String name) {
        this.name = name;
    }

    public Set<IndicatorWithTrendDto> getIndicators() {
        return indicators;
    }

    public String getName() {
        return name;
    }

}