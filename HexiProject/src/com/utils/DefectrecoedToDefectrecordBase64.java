package com.utils;

import com.pojo.DefectrecordBase64;
import com.pojo.DefectrecordWithBLOBs;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Base64;
import java.util.Map;

public class DefectrecoedToDefectrecordBase64 {


    public static DefectrecordBase64 getdefectrecordbase64(DefectrecordWithBLOBs defectrecordWithBLOBs, HttpServletRequest httpServletRequest, Map<String,String> hashmap)
    {
        DefectrecordBase64 defectrecordBase64 = new DefectrecordBase64();
        defectrecordBase64.setId(defectrecordWithBLOBs.getId());
        defectrecordBase64.setComments(defectrecordWithBLOBs.getComments());
        defectrecordBase64.setDefectMode(defectrecordWithBLOBs.getDefectMode());
        defectrecordBase64.setDefectName(defectrecordWithBLOBs.getDefectName());
        defectrecordBase64.setDefectPart(defectrecordWithBLOBs.getDefectPart());
        defectrecordBase64.setRemark(defectrecordWithBLOBs.getRemark());
        defectrecordBase64.setRemark(defectrecordWithBLOBs.getRemark());
        defectrecordBase64.setSpecificPart(defectrecordWithBLOBs.getSpecificPart());
        defectrecordBase64.setStation(defectrecordWithBLOBs.getStation());
        defectrecordBase64.setTimestamp(defectrecordWithBLOBs.getTimestamp());
        defectrecordBase64.setStatus(defectrecordWithBLOBs.getStatus());
        defectrecordBase64.setType(defectrecordWithBLOBs.getType());
        defectrecordBase64.setUsername(defectrecordWithBLOBs.getUsername());
        defectrecordBase64.setVin(defectrecordWithBLOBs.getVin());
        byte[] defectPhoto = defectrecordWithBLOBs.getDefectPhoto();
        byte[] defectPhoto2 = defectrecordWithBLOBs.getDefectPhoto2();
        byte[] defectPhoto3 = defectrecordWithBLOBs.getDefectPhoto3();
        byte[] defectPhoto4 = defectrecordWithBLOBs.getDefectPhoto4();
        if (defectPhoto!=null&&defectPhoto.length!=0) {
            Base64.Encoder encoder = Base64.getEncoder();
            String s = encoder.encodeToString(defectPhoto);
            defectrecordBase64.setDefectPhoto("data:image/png;base64," + s);

        }
        if (defectPhoto2!=null&&defectPhoto2.length!=0)
        {
            Base64.Encoder encoder = Base64.getEncoder();
            String s2 = encoder.encodeToString(defectPhoto2);
            defectrecordBase64.setDefectPhoto2("data:image/png;base64," + s2);

        }
        if (defectPhoto3!=null&&defectPhoto3.length!=0)
        {
            Base64.Encoder encoder = Base64.getEncoder();
            String s3 = encoder.encodeToString(defectPhoto3);
            defectrecordBase64.setDefectPhoto3("data:image/png;base64," + s3);

        }
        if (defectPhoto4!=null&&defectPhoto4.length!=0)
        {
            Base64.Encoder encoder = Base64.getEncoder();
            String s4 = encoder.encodeToString(defectPhoto4);
            defectrecordBase64.setDefectPhoto4("data:image/png;base64," + s4);

        }
        ServletContext servletContext = httpServletRequest.getServletContext();
        for (int i=0;i<4;i++)
        {
            String realPath = servletContext.getRealPath("/photo/" + defectrecordWithBLOBs.getVin()+i + "." + hashmap.get(defectrecordWithBLOBs.getId()+""+i));
            File file = new File(realPath);
            if (file.exists())
            {
                SwithForSetPhoto.setphotourl(i,realPath,defectrecordBase64);
            }
        }
        return defectrecordBase64;
    }
}
