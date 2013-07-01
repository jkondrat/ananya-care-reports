package org.motechproject.carereporting.service;

import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.motechproject.carereporting.domain.IndicatorEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:testContext.xml")
public class IndicatorServiceTest extends AbstractTransactionalJUnit4SpringContextTests {

    private final static String TEST_INDICATOR_1_NAME = "TEST_INDICATOR_1";
    private final static String TEST_INDICATOR_1_UPDATED_NAME = "TEST_INDICATOR_1_UPDATED";
    private final static String TEST_INDICATOR_2_NAME = "TEST_INDICATOR_2";
    private final static int EXPECTED_INDICATOR_LIST_SIZE = 2;

    @Autowired
    private IndicatorService indicatorService;

    @Autowired
    private SessionFactory sessionFactory;

    @Before
    public void initialize() {
        sessionFactory.getCurrentSession()
                .createQuery("delete from IndicatorEntity");
    }

    @Test
    public void testCreateNewIndicator() {
        IndicatorEntity indicatorEntity = constructIndicator(TEST_INDICATOR_1_NAME);
        indicatorService.createNewIndicator(indicatorEntity);

        IndicatorEntity indicatorEntityResult = indicatorService.findIndicatorById(indicatorEntity.getId());
        assertNotNull(indicatorEntityResult);
    }

    @Test
    public void testUpdateIndicator() {
        IndicatorEntity indicatorEntity = constructIndicator(TEST_INDICATOR_1_NAME);
        indicatorService.createNewIndicator(indicatorEntity);

        indicatorEntity.setName(TEST_INDICATOR_1_UPDATED_NAME);
        indicatorService.updateIndicator(indicatorEntity);

        IndicatorEntity indicatorEntityResult = indicatorService.findIndicatorById(indicatorEntity.getId());
        assertNotNull(indicatorEntityResult);
        assertEquals("IndicatorEntity name is: '" + indicatorEntityResult.getName() + "'"
                + ", expected: '" + TEST_INDICATOR_1_UPDATED_NAME + "'.",
                TEST_INDICATOR_1_UPDATED_NAME, indicatorEntityResult.getName());
    }

    @Test
    public void testFindAllIndicators() {
        IndicatorEntity indicatorEntity1 = constructIndicator(TEST_INDICATOR_1_NAME);
        IndicatorEntity indicatorEntity2 = constructIndicator(TEST_INDICATOR_2_NAME);
        indicatorService.createNewIndicator(indicatorEntity1);
        indicatorService.createNewIndicator(indicatorEntity2);

        List<IndicatorEntity> indicatorList = indicatorService.findAllIndicators();
        assertNotNull(indicatorList);
        assertEquals("IndicatorEntity list size is: " + indicatorList.size()
                + ", expected: " + EXPECTED_INDICATOR_LIST_SIZE,
                EXPECTED_INDICATOR_LIST_SIZE, indicatorList.size());
    }

    @Test
    public void testDeleteIndicator() {
        IndicatorEntity indicatorEntity = constructIndicator(TEST_INDICATOR_1_NAME);
        indicatorService.createNewIndicator(indicatorEntity);
        indicatorService.deleteIndicator(indicatorEntity);

        IndicatorEntity indicatorEntityResult = indicatorService.findIndicatorById(indicatorEntity.getId());
        assertNull(indicatorEntityResult);
    }

    private IndicatorEntity constructIndicator(String name) {
        IndicatorEntity indicatorEntity = new IndicatorEntity();
        indicatorEntity.setName(name);

        return indicatorEntity;
    }
}