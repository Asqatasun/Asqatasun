package org.opens.tanaguru.entity.service.reference;

import com.adex.sdk.entity.service.AbstractGenericDataService;
import java.util.Collection;
import org.opens.tanaguru.entity.reference.Criterion;
import org.opens.tanaguru.entity.reference.Theme;

/**
 * 
 * @author jkowalczyk
 */
public class ThemeDataServiceImpl extends AbstractGenericDataService<Theme, Long> implements ThemeDataService {

    public ThemeDataServiceImpl() {
        super();
    }

    @Override
    public Theme read(Long key) {
        Theme entity = super.read(key);
        return entity;
    }

    @Override
    public Collection<? extends Theme> findAll() {
        Collection<? extends Theme> themeList = super.findAll() ;
        for (Theme theme : themeList) {
            for (Criterion cr : theme.getCriterionList()) {
                cr.getReference();
            }
        }
        return themeList;
    }

}