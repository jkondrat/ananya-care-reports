package org.motechproject.carereporting.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.motechproject.carereporting.domain.AreaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:testContext.xml")
public class AreaDaoIT extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private AreaDao areaDao;

    private final int PARENT_AREA_ID = 1;
    private final int EXPECTED_CHILD_AREAS = 8;
    private final int EXPECTED_DIRECT_CHILD_AREAS = 2;
    private final int LEVEL_ID = 1;
    private final int EXPECTED_AREAS_BY_LEVEL_ID = 2;

    @Test
    public void testFindAllChildAreasByParentAreaId() {
        List<AreaEntity> areaEntityList = areaDao.getAllChildAreasByParentAreaId(PARENT_AREA_ID);

        assertNotNull(areaEntityList);
        assertEquals(EXPECTED_CHILD_AREAS, areaEntityList.size());
    }

    @Test
    public void testFindDirectChildAreas() {
        List<AreaEntity> areaEntityList = areaDao.getDirectChildAreas(PARENT_AREA_ID);

        assertNotNull(areaEntityList);
        assertEquals(EXPECTED_DIRECT_CHILD_AREAS, areaEntityList.size());
    }

    @Test
    public void testFindAreasByLevelId() {
        List<AreaEntity> areaEntityList = areaDao.getAreasByLevelId(LEVEL_ID);

        assertNotNull(areaEntityList);
        assertEquals(EXPECTED_AREAS_BY_LEVEL_ID, areaEntityList.size());
    }
}