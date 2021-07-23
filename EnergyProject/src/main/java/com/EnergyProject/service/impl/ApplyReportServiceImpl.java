package com.EnergyProject.service.impl;

import com.EnergyProject.pojo.ApplyReport;
import com.EnergyProject.dao.ApplyReportMapper;
import com.EnergyProject.server.APPLYREPORTervice;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 魔法少女
 * @since 2021-07-14
 */
@Service
public  class ApplyReportServiceImpl extends ServiceImpl<ApplyReportMapper, ApplyReport> implements APPLYREPORTervice {

    @Autowired
    private ApplyReportMapper applyReportMapper;

    private Map<String,Object> outPutPageData=new HashMap<>();

    public Integer insterApplyReport(ApplyReport applyReport)
    {
       return applyReportMapper.insert(applyReport);
    }

    @Override
    public Map<String, Object> selectStatus(Integer currentPage, Integer pageSize) {
        QueryWrapper<ApplyReport> applyReportQueryWrapper = new QueryWrapper<ApplyReport>();
        Page<ApplyReport> applyReportPage = new Page<>();
        applyReportPage.setCurrent(currentPage).setSize(pageSize);
        Page<ApplyReport> applyReportPageList = applyReportMapper.selectPage(applyReportPage, new QueryWrapper<ApplyReport>().eq("status","未审核").orderByAsc("id"));
        long total = applyReportPageList.getTotal();
        System.out.println(total);
        List<ApplyReport> records = applyReportPageList.getRecords();
        outPutPageData.put("total",total);
        outPutPageData.put("data",records);
        return outPutPageData;
    }

    @Override
    public Integer updataApplyReportDataStatus(Integer[] selectId, String selectMode) {
        ApplyReport applyReport = new ApplyReport();
        applyReport.setStatus(selectMode);
       return applyReportMapper.update(applyReport,new QueryWrapper<ApplyReport>().in("id",Arrays.asList(selectId)));
    }
    @Override
    public Integer singleApplyReportDataStatus(ApplyReport applyReport)
    {
       return applyReportMapper.updateById(applyReport);
    }

    @Override
    public IPage<ApplyReport> getAllApplyReport(Integer page, Integer size) {
       return applyReportMapper.selectPage(new Page<ApplyReport>().setSize(size).setCurrent(page),new QueryWrapper<ApplyReport>().orderByAsc("id"));
    }

    @Override
    public IPage<ApplyReport> selectOfApplyUsername(Map<String,Map<String,Object>> accurateSelect,Integer page, Integer size) {
        Map<String, Object> publice = accurateSelect.get("publice");
        Map<String, Object> selectTime = accurateSelect.get("selectTime");
        if (selectTime!=null) {
            Object startWorkTime = selectTime.get("startWorkTime");
            Object endWorkTime = selectTime.get("endWorkTime");
            Object startApplyTime = selectTime.get("startApplyTime");
            Object endApplyTime = selectTime.get("endApplyTime");
            Object startCheckTime = selectTime.get("startCheckTime");
            Object endCheckTime = selectTime.get("endCheckTime");


            if (startWorkTime != null && endWorkTime != null  && startApplyTime == null && endApplyTime == null && startCheckTime==null && endCheckTime== null) {
                return applyReportMapper.selectPage(new Page<ApplyReport>().setSize(size).setCurrent(page), new QueryWrapper<ApplyReport>().allEq(publice, false).between("workTimeStart", startWorkTime, endWorkTime).orderByAsc("id"));
            } else if (startApplyTime != null && endApplyTime != null && startWorkTime == null && endWorkTime == null && startCheckTime==null && endCheckTime== null) {
                return applyReportMapper.selectPage(new Page<ApplyReport>().setSize(size).setCurrent(page), new QueryWrapper<ApplyReport>().allEq(publice, false).between("applyTime", startApplyTime, endApplyTime).orderByAsc("id"));
            }else if (startCheckTime!=null && endCheckTime !=null &&  startWorkTime == null && endWorkTime == null && startApplyTime == null && endApplyTime == null)
            {
                return applyReportMapper.selectPage(new Page<ApplyReport>().setSize(size).setCurrent(page), new QueryWrapper<ApplyReport>().allEq(publice, false).between("checkTime",startCheckTime,endCheckTime).orderByAsc("id"));
            }
            else if (startWorkTime != null && endWorkTime != null && startApplyTime != null && endApplyTime != null && startCheckTime==null && endCheckTime== null) {
                return applyReportMapper.selectPage(new Page<ApplyReport>().setSize(size).setCurrent(page), new QueryWrapper<ApplyReport>().allEq(publice, false).between("workTimeStart", startWorkTime, endWorkTime).between("applyTime", startApplyTime, endApplyTime).orderByAsc("id"));
            }
            else if (startWorkTime != null && endWorkTime != null&&startCheckTime!=null && endCheckTime !=null&&startApplyTime == null && endApplyTime == null)
            {
                return applyReportMapper.selectPage(new Page<ApplyReport>().setSize(size).setCurrent(page), new QueryWrapper<ApplyReport>().allEq(publice, false).between("workTimeStart", startWorkTime, endWorkTime).between("checkTime", startCheckTime, endCheckTime).orderByAsc("id"));
            }
            else if (startApplyTime != null && endApplyTime != null&&startCheckTime!=null && endCheckTime !=null&&startWorkTime == null && endWorkTime == null)
            {
                return applyReportMapper.selectPage(new Page<ApplyReport>().setSize(size).setCurrent(page), new QueryWrapper<ApplyReport>().allEq(publice, false).between("applyTime", startApplyTime, endApplyTime).between("checkTime", startCheckTime, endCheckTime).orderByAsc("id"));
            }
            else if (startApplyTime != null && endApplyTime != null&&startCheckTime!=null && endCheckTime !=null&&startWorkTime != null && endWorkTime != null)
            {
                return applyReportMapper.selectPage(new Page<ApplyReport>().setSize(size).setCurrent(page), new QueryWrapper<ApplyReport>().allEq(publice, false).between("workTimeStart", startWorkTime, endWorkTime).between("applyTime", startApplyTime, endApplyTime).between("checkTime",startCheckTime,endCheckTime).orderByAsc("id"));
            }
        }
        return applyReportMapper.selectPage(new Page<ApplyReport>().setSize(size).setCurrent(page), new QueryWrapper<ApplyReport>().allEq(publice, false).orderByAsc("id"));

    }


    @Override
    public List<String> getAllUsername() {
      return   applyReportMapper.getAllUsername();
    }

    @Override
    public Integer deleteApplyReport(Integer id) {
       return applyReportMapper.deleteById(id);
    }

    @Override
    public Integer deleteAllApplyReport(List<Integer> ids) {
        return applyReportMapper.delete(new QueryWrapper<ApplyReport>().in("id", ids));
    }

    @Override
    public ApplyReport updataApplyReport(Integer id) {
       return applyReportMapper.selectById(id);
    }

    @Override
    public Integer updataApplyReportFinall(ApplyReport applyReport) {
      return   applyReportMapper.updateById(applyReport);
    }

    @Override
    public IPage<ApplyReport> getAllApplyReportForReject(Integer page, Integer size) {
        return applyReportMapper.selectPage(new Page<ApplyReport>().setSize(size).setCurrent(page),new QueryWrapper<ApplyReport>().eq("status","拒绝").orderByAsc("id"));
    }

    @Override
    public Integer sendRejectText(Map<String,Object> rejectText, Integer id) {
        ApplyReport applyReport = new ApplyReport();
        if (id!=null)
        {
            applyReport.setRejectText((String) rejectText.get("rejectText"));
            applyReport.setId(id);
            return applyReportMapper.updateById(applyReport);
        }
        else
        {
            applyReport.setRejectText((String) rejectText.get("rejectText"));
            List<Integer> ids = (List<Integer>) rejectText.get("ids");
            return applyReportMapper.update(applyReport,new QueryWrapper<ApplyReport>().in("id",ids));


        }


    }

    @Override
    public Integer agreeRejectApplyReportForComment(Integer id) {
        ApplyReport applyReport = new ApplyReport();
        applyReport.setStatus("已审核");
        applyReport.setId(id);
       return applyReportMapper.updateById(applyReport);
    }

    @Override
    public List<ApplyReport> getApplyReportOfAmountData(LocalDateTime startTime, LocalDateTime endTime) {
      return applyReportMapper.selectList(new QueryWrapper<ApplyReport>().ge("workTimeStart",startTime).le("workTimeEnd",endTime).eq("status","已审核").orderByAsc("id"));
    }

    @Override
    public List<ApplyReport> getFirstThreeDaysOfApplyReportOfUnReviewed() {
        return applyReportMapper.getFirstThreeDaysOfApplyReportOfUnReviewed();
    }

}
