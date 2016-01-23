package com.dw.practWeb.utils.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;
import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;

import com.dw.practWeb.paging.PagedResult;
import com.dw.practWeb.utils.BeanMapper;

@Named
public class BeanMapperImpl extends DozerBeanMapper implements BeanMapper
{

    private static Logger logger = LoggerFactory.getLogger(BeanMapperImpl.class);

    @Inject
    public BeanMapperImpl(@Value("classpath*:dozer/*-dozer.xml") Resource[] resources)
    {
        super();

        try
        {
            List<String> mappings = new ArrayList<String>(resources.length);

            for (Resource r : resources)
            {
                String url = r.getURL().toString();
                logger.debug("Scanned Dozer mapping file : {}", url);
                mappings.add(url);
            }

            setMappingFiles(mappings);
        }
        catch (Exception e)
        {
            logger.error("Error occurred while scanning mapping files.");
        }
    }

    public <T, D> List<D> mapCollection(Collection<T> list, Class<D> classDTOName)
    {
        return fill(null, list, classDTOName, null);
    }

    public <T, D> List<D> mapCollection(Collection<T> list, Class<D> classDTOName, String mapId)
    {
        return fill(null, list, classDTOName, mapId);
    }

    private <T, D> void add(List<D> dtoList, T f, Class<D> classDTOName, String mapId)
    {
        if (mapId == null)
        {
            dtoList.add(map(f, classDTOName));
        }

        if (mapId != null)
        {
            dtoList.add(map(f, classDTOName, mapId));
        }
    }
    
    private <T, D> List<D> fill(Page<T> page, Collection<T> list, Class<D> classDTOName, String mapId)
    {
        List<D> dtoList = new ArrayList<D>();

        if (page != null)
        {
            for (T f : page)
            {
                add(dtoList, f, classDTOName, mapId);
            }
        }

        if (list != null)
        {
            for (T f : list)
            {
                add(dtoList, f, classDTOName, mapId);
            }
        }

        return dtoList;
    }

    @Override
    public <T, D> PagedResult<D> mapCollection(Page<T> page, Class<D> classDTOName)
    {
        return getPagedResult(page, classDTOName, null);
    }

    @Override
    public <T, D> PagedResult<D> mapCollection(Page<T> page, Class<D> classDTOName, String mapId)
    {
        return getPagedResult(page, classDTOName, mapId);
    }

    private <T, D> PagedResult<D> getPagedResult(Page<T> page, Class<D> classDTOName, String mapId)
    {
        PagedResult<D> result = new PagedResult<D>();

        List<D> dtoList = fill(page, null, classDTOName, mapId);

        // name: DESC,city: DESC,id: DESC
        String[] sortStr = page.getSort().toString().split(",");
        String[] sortStr1 =
        {};

        String order = "";
        List<String> sortOn = new ArrayList<String>();

        for (String s : sortStr)
        {
            sortStr1 = s.split(":");
            sortOn.add(StringUtils.trim(sortStr1[0]));
            order = sortStr1[1];
        }

        result.setSortOn(sortOn.toString());
        result.setSortOrder(StringUtils.trim(order));
        result.setPageNo(page.getNumber());
        result.setResults(dtoList);
        result.setRpp(page.getNumberOfElements());
        result.setTotalResults(page.getTotalElements());
        result.setTotalPage(page.getTotalPages());

        return result;
    }
}
