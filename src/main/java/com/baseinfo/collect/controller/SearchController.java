package com.baseinfo.collect.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baseinfo.collect.beans.HouseBean;
import com.baseinfo.collect.beans.TotalHits;
import com.baseinfo.collect.client.*;
import com.baseinfo.collect.dao.*;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baseinfo.collect.common.IndexConstants;
import com.baseinfo.collect.contract.BaseResponse;
import com.baseinfo.collect.service.ESSearchService;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * 搜索Controller
 */
@Controller
public class SearchController {

    private static int PAGESIZE = 20;

	@Autowired
    @Qualifier("esSearchService")
    private ESSearchService esSearchService;

    @Autowired
    @Qualifier("HouseClient")
    private HouseClient houseClient;
    @Autowired
    @Qualifier("PersonClient")
    private PersonClient personClient;
    @Autowired
    @Qualifier("PlaceClient")
    private PlaceClient placeClient;
    @Autowired
    @Qualifier("EmployerClient")
    private EmployerClient employerClient;
    @Autowired
    @Qualifier("CameraClient")
    private CameraClient cameraClient;

    @ResponseBody
    @RequestMapping(value = "/update/house")
    public BaseResponse update(HouseBean house,HttpServletRequest request,HttpServletResponse response) {
        BaseResponse res = new BaseResponse();
        System.out.print(house);
        boolean result = houseClient.updatePeople(house);
        if(result){
            res.setCode(1);
        } else {
            res.setCode(0);
        }
        return res;
    }

    @RequestMapping(value = "/search")
    public ModelAndView search(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView("/searchlist");
        try {
            String searchkey = request.getParameter("searchkey");
        	String type = request.getParameter("type").trim();
        	String pageindexStr = request.getParameter("pageindex");
            model.addObject("searchkey", searchkey);
            model.addObject("type", type);
        	int pageIndex = 1;
            try{
                pageIndex = Integer.parseInt(pageindexStr);
            } catch(NumberFormatException e){}
            if(pageIndex < 1)
                pageIndex = 1;
            model.addObject("pageindex", pageIndex);
            pageIndex--;
        	String indexType = IndexConstants.getIndexByType(type);
            TotalHits hits = new TotalHits(0);
            List<Map<String, Object>> resultList = esSearchService.queryForObjectNotEq(searchkey, pageIndex*PAGESIZE, PAGESIZE, indexType,hits);
            model.addObject(type+"list", resultList);
            long total = hits.getTotal();
            long pagetotal = total/PAGESIZE;
            if(total%PAGESIZE>0){
                pagetotal++;
            }
            model.addObject("total",total);
            model.addObject("pagetotal",pagetotal);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return model;
    }

    @ResponseBody
    @RequestMapping(value = "/delete/{type}/{id}")
    public BaseResponse delete(@PathVariable("type") String type, @PathVariable("id") long id, HttpServletRequest request, HttpServletResponse response) {
        BaseResponse res = new BaseResponse();
        boolean result = false;
        switch (type){
            case "house" :
                result = houseClient.delete(id);
                break;
            case "people" :
                result = personClient.delete(id);
                break;
            case "place" :
                result = placeClient.delete(id);
                break;
            case "employer" :
                result = employerClient.delete(id);
                break;
            case "camera" :
                result = cameraClient.delete(id);
                break;
        }
        if(result) {
            res.setCode(1);
        } else {
            res.setCode(0);
        }
        return res;
    }

    @ResponseBody
    @RequestMapping(value = "/batchdelete/{type}")
    public BaseResponse batchdelete(@PathVariable("type") String type, HttpServletRequest request, HttpServletResponse response) {
        BaseResponse res = new BaseResponse();
        String ids = request.getParameter("ids").trim();
        String[] idStrArr;
        if(StringUtils.isNotBlank(ids)){
            idStrArr = ids.split(",");
            Long[] idArr = new Long[idStrArr.length];
            for (int i=0; i<idStrArr.length; i++) {
                String idStr = idStrArr[i];
                if(StringUtils.isNumeric(idStr))
                    idArr[i] = Long.parseLong(idStr);
            }
            boolean result = false;
            switch (type){
                case "house" :
                    for(long id : idArr)
                        houseClient.delete(id);
                    break;
                case "people" :
                    for(long id : idArr)
                        personClient.delete(id);
                    break;
                case "place" :
                    for(long id : idArr)
                        placeClient.delete(id);
                    break;
                case "employer" :
                    for(long id : idArr)
                        employerClient.delete(id);
                    break;
                case "camera" :
                    for(long id : idArr)
                        cameraClient.delete(id);
                    break;
            }
            res.setCode(1);
        }else {
            res.setCode(0);
        }
        return res;
    }

}
