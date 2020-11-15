/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2020  Asqatasun.org
 *
 * This file is part of Asqatasun.
 *
 * Asqatasun is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Contact us by mail: asqatasun AT asqatasun DOT org
 */
package org.asqatasun.entity.service.audit;

import org.asqatasun.entity.audit.Tag;
import org.asqatasun.entity.audit.TagImpl;
import org.asqatasun.entity.dao.audit.TagDAO;
import org.asqatasun.entity.service.AbstractGenericDataService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author jkowalczyk
 */
@Service("tagDataService")
public class TagDataServiceImpl extends AbstractGenericDataService<Tag, Long> implements
        TagDataService {

    private Map<String, Tag> tagMap = new HashMap<>();

    public TagDataServiceImpl() {
        super();
    }

    @Override
    public Tag findByValue(String value) {
        if (tagMap.containsKey(value)) {
            return tagMap.get(value);
        }
        Tag tag = ((TagDAO) entityDao).retrieveByValue(value);
        if (tag != null) {
            tagMap.put(value, tag);
            return tag;
        } else {
            return new TagImpl(value);
        }
    }

    @Override
    public List<Tag> getTagListFromValues(List<String> values) {
        List<Tag> tagList = new ArrayList<>();
        for (String value : values) {
            tagList.add(findByValue(value));
        }
        return tagList;
    }


}
