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
        List<D> dtoList = new ArrayList<D>();

        if (list != null)
        {
            for (T f : list)
            {
                dtoList.add(map(f, classDTOName));
            }
        }

        return dtoList;
    }

    public <T, D> List<D> mapCollection(Collection<T> list, Class<D> classDTOName, String mapId)
    {
        List<D> dtoList = new ArrayList<D>();

        if (list != null)
        {
            for (T f : list)
            {
                dtoList.add(map(f, classDTOName, mapId));
            }
        }

        return dtoList;
    }

    @Override
    public <T, D> PagedResult<D> mapCollection(Page<T> page, Class<D> classDTOName)
    {

        PagedResult<D> result = new PagedResult<D>();

        List<D> dtoList = new ArrayList<D>();

        if (page != null)
        {
            for (T f : page)
            {
                dtoList.add(map(f, classDTOName));
            }
        }

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

    @Override
    public <T, D> PagedResult<D> mapCollection(Page<T> page, Class<D> classDTOName, String mapId)
    {
        PagedResult<D> result = new PagedResult<D>();

        List<D> dtoList = new ArrayList<D>();

        if (page != null)
        {
            for (T f : page)
            {
                dtoList.add(map(f, classDTOName, mapId));
            }
        }

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
