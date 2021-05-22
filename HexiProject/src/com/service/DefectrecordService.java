package com.service;

import com.dao.DefectrecordMapper;
import com.pojo.Defectrecord;
import com.pojo.DefectrecordBase64;
import com.pojo.DefectrecordExample;
import com.pojo.DefectrecordWithBLOBs;
import com.utils.DefectrecoedToDefectrecordBase64;
import com.utils.SwithForSetPhoto;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.*;

@Service
public class DefectrecordService {
    @Autowired
    private DefectrecordMapper defectrecordMapperl;

    private final static ArrayList<String> cartTypeArrayList=new ArrayList<String>();

    private final static ArrayList<String> carstationList=new ArrayList<>();
    public String insertdefectrecord(DefectrecordWithBLOBs defectrecord)
    {
        if (!isVin(defectrecord.getVin()))
        {
            if ("合格".equalsIgnoreCase(defectrecord.getDefectStatus()))
            {
                defectrecord.setType("无");
                defectrecord.setRemark("无");
                defectrecord.setDefectPart("无");
                defectrecord.setDefectMode("无");
                defectrecord.setSpecificPart("无");
                defectrecord.setDefectName("无");
            }
            int i = defectrecordMapperl.insertSelective(defectrecord);

            if (i != 0){
                return "success";
            }

        }




        return null;
    }


    public String insertphoto(Integer id,List<byte[]> bytes)
    {
        DefectrecordWithBLOBs defectrecord = new DefectrecordWithBLOBs();
        defectrecord.setId(id);
        if (bytes!=null&&!bytes.isEmpty())
        {
            int size = bytes.size();
            for (int i=0;i<size;i++)
            {
                if (i==0)
                {
                    defectrecord.setDefectPhoto(bytes.get(i));
                }
                if (i==1)
                {
                    defectrecord.setDefectPhoto2(bytes.get(i));
                }
                if (i==2)
                {
                    defectrecord.setDefectPhoto3(bytes.get(i));
                }
                if (i==3)
                {
                    defectrecord.setDefectPhoto4(bytes.get(i));
                }
            }

        }

        int i = defectrecordMapperl.updateByPrimaryKeySelective(defectrecord);
        if (i!=0)
        {
            return "success";
        }
        return null;
    }

    public List<DefectrecordBase64> getDefectrecordBase64(HttpServletRequest httpServletRequest, Map<String,String> collectionPhotoName) throws IOException {
        List<DefectrecordWithBLOBs> defectrecords = defectrecordMapperl.selectByExampleWithBLOBs(null);
        ArrayList<DefectrecordBase64> defectrecordBase64s = new ArrayList<>();
        for (DefectrecordWithBLOBs defectrecord:defectrecords)
        {
            int photoNum=0;
            DefectrecordBase64 defectrecordBase64 = new DefectrecordBase64();
            defectrecordBase64.setId(defectrecord.getId());
            defectrecordBase64.setComments(defectrecord.getComments());
            defectrecordBase64.setDefectMode(defectrecord.getDefectMode());
            defectrecordBase64.setDefectName(defectrecord.getDefectName());
            defectrecordBase64.setDefectPart(defectrecord.getDefectPart());
            defectrecordBase64.setRemark(defectrecord.getRemark());
            defectrecordBase64.setRemark(defectrecord.getRemark());
            defectrecordBase64.setSpecificPart(defectrecord.getSpecificPart());
            defectrecordBase64.setStation(defectrecord.getStation());
            defectrecordBase64.setTimestamp(defectrecord.getTimestamp());
            defectrecordBase64.setStatus(defectrecord.getStatus());
            defectrecordBase64.setType(defectrecord.getType());
            defectrecordBase64.setUsername(defectrecord.getUsername());
            defectrecordBase64.setCarType(defectrecord.getCarType());
            defectrecordBase64.setDefectStatus(defectrecord.getDefectStatus());
            defectrecordBase64.setVin(defectrecord.getVin());
            byte[] defectPhoto = defectrecord.getDefectPhoto();
            byte[] defectPhoto2 = defectrecord.getDefectPhoto2();
            byte[] defectPhoto3 = defectrecord.getDefectPhoto3();
            byte[] defectPhoto4 = defectrecord.getDefectPhoto4();
            if (defectPhoto!=null&&defectPhoto.length!=0) {
                Base64.Encoder encoder = Base64.getEncoder();
                String s = encoder.encodeToString(defectPhoto);
                defectrecordBase64.setDefectPhoto("data:image/png;base64," + s);
                ++photoNum;
            }
            if (defectPhoto2!=null&&defectPhoto2.length!=0)
            {
                Base64.Encoder encoder = Base64.getEncoder();
                String s2 = encoder.encodeToString(defectPhoto2);
                defectrecordBase64.setDefectPhoto2("data:image/png;base64," + s2);
                ++photoNum;
            }
            if (defectPhoto3!=null&&defectPhoto3.length!=0)
            {
                Base64.Encoder encoder = Base64.getEncoder();
                String s3 = encoder.encodeToString(defectPhoto3);
                defectrecordBase64.setDefectPhoto3("data:image/png;base64," + s3);
                ++photoNum;
            }
            if (defectPhoto4!=null&&defectPhoto4.length!=0)
            {
                Base64.Encoder encoder = Base64.getEncoder();
                String s4 = encoder.encodeToString(defectPhoto4);
                defectrecordBase64.setDefectPhoto4("data:image/png;base64," + s4);
                ++photoNum;
            }






                ServletContext servletContext = httpServletRequest.getServletContext();
                String realPath = servletContext.getRealPath("/photo");
                for (int i=0;i<photoNum;i++) {


                    if (collectionPhotoName.get(defectrecord.getId()+""+i) == null) {
                        File filejpg = new File(realPath + "/" + defectrecord.getVin()+i + ".jpg");
                        File filepng = new File(realPath + "/" + defectrecord.getVin()+i + ".png");
                        if (filejpg.exists()) {
                            String photourlforjog = "http://localhost:8088/HexiProject/photo/" + defectrecord.getVin()+i + ".jpg";
                            SwithForSetPhoto.selectPhotoMethod(i,photourlforjog,defectrecordBase64);
                            collectionPhotoName.put(defectrecord.getId()+""+i, "jpg");
                        } else if (filepng.exists()) {
                            String photourlforpng = "http://localhost:8088/HexiProject/photo/" + defectrecord.getVin()+i + ".png";
                            SwithForSetPhoto.selectPhotoMethod(i,photourlforpng,defectrecordBase64);
                            collectionPhotoName.put(defectrecord.getId()+""+i, "png");
                        }

                    }
                    else
                    {
                        File file = new File(realPath + "/" + defectrecord.getVin()+i + "." + collectionPhotoName.get(defectrecord.getId()+""+i));
                        if (!file.exists()) {
                            FileOutputStream fileOutputStream = new FileOutputStream(file);
                            fileOutputStream.write(SwithForSetPhoto.getPhotobyte(i,defectrecord));
                            fileOutputStream.flush();
                            fileOutputStream.close();

                        }
                        String photourl = "http://localhost:8088/HexiProject/photo/" + defectrecord.getVin()+i + "." + collectionPhotoName.get(defectrecord.getId()+""+i);
                        SwithForSetPhoto.setphotourl(i,photourl,defectrecordBase64);
                    }


                }
            if (defectrecordBase64.getPhotourl()==null && defectrecordBase64.getPhotourl2()==null && defectrecordBase64.getPhotourl3()==null && defectrecordBase64.getPhotourl4()==null)
            {
                if (defectPhoto!=null && defectPhoto2!=null && defectPhoto3!=null && defectPhoto4!=null)
                {
                    for (int i=0;i<photoNum;i++)
                    {
                        File file = new File(realPath + "/" + defectrecord.getVin()+i + ".jpg" );
                        FileOutputStream fileOutputStream = new FileOutputStream(file);
                        fileOutputStream.write(SwithForSetPhoto.getPhotobyte(i,defectrecord));
                        fileOutputStream.flush();
                        fileOutputStream.close();
                        collectionPhotoName.put(defectrecord.getId()+""+i, "jpg");
                        String photourl = "http://localhost:8088/HexiProject/photo/" + defectrecord.getVin()+i+".jpg";
                        SwithForSetPhoto.setphotourl(i,photourl,defectrecordBase64);
                    }
                }
            }

            defectrecordBase64s.add(defectrecordBase64);
        }
        return defectrecordBase64s;
    }



    public String deletedefectrecord(Integer id)
    {


        int i = defectrecordMapperl.deleteByPrimaryKey(id);
        if (i!=0)
        {
            return "success";
        }
        return null;
    }

    public String deletephoto(Integer id,HttpServletRequest httpServletRequest,Map<String,String> getphotoname)
    {
        DefectrecordExample defectrecordExample = new DefectrecordExample();
        DefectrecordExample.Criteria criteria = defectrecordExample.createCriteria();
        criteria.andIdEqualTo(id);
        List<DefectrecordWithBLOBs> defectrecords = defectrecordMapperl.selectByExampleWithBLOBs(defectrecordExample);
        String vin = defectrecords.get(0).getVin();
        ServletContext servletContext = httpServletRequest.getServletContext();
        for (int i=0;i<4;i++)
        {
            String realPath = servletContext.getRealPath("/photo/" + vin+i + "." + getphotoname.get(defectrecords.get(0).getId()+""+i));
            File file = new File(realPath);
            if (file.exists())
            {
                file.delete();
                getphotoname.remove(defectrecords.get(0).getId()+""+i);
            }
        }

       return this.deletedefectrecord(id);
    }

    public DefectrecordBase64 sortTable(String vin,HttpServletRequest httpServletRequest,Map<String,String> hashmap)
    {
        DefectrecordExample defectrecordExample = new DefectrecordExample();
        DefectrecordExample.Criteria criteria = defectrecordExample.createCriteria();
        criteria.andVinEqualTo(vin);
        List<DefectrecordWithBLOBs> defectrecordWithBLOBs = defectrecordMapperl.selectByExampleWithBLOBs(defectrecordExample);
        if (defectrecordWithBLOBs!=null&&!defectrecordWithBLOBs.isEmpty())
        {

            DefectrecordBase64 defectrecordBase64 = DefectrecoedToDefectrecordBase64.getdefectrecordbase64(defectrecordWithBLOBs.get(0), httpServletRequest, hashmap);
            return defectrecordBase64;
        }
        else
        {
            return null;
        }

    }public String productFPQ()
    {
        DefectrecordExample defectrecordExample = new DefectrecordExample();
        DefectrecordExample.Criteria criteria = defectrecordExample.createCriteria();
        criteria.andDefectStatusEqualTo("合格");
        List<Defectrecord> defectrecords = defectrecordMapperl.selectByExample(defectrecordExample);
        List<Defectrecord> defectrecords1 = defectrecordMapperl.selectByExample(null);
        double ftq=defectrecords.size()/(defectrecords1.size()*1.0)*100;
        NumberFormat currencyInstance = NumberFormat.getNumberInstance();
        currencyInstance.setMaximumFractionDigits(2);
        String format = currencyInstance.format(ftq)+"%";
        return format;
    }

    public void addcarType(ArrayList<String> stringArrayList,ArrayList<String> carstation){
        System.out.println("前端自定义" + stringArrayList);
        if (!stringArrayList.isEmpty())
        {
            for (String cartype:stringArrayList)
            {
                if (!cartTypeArrayList.contains(cartype))
                {
                    cartTypeArrayList.add(cartype);
                }
            }

        }
        if (!carstation.isEmpty())
        {
            for (String onecarstation:carstation)
            {
                if (!carstationList.contains(onecarstation))
                {
                    carstationList.add(onecarstation);
                }
            }

        }
    }

    public Map<String, ArrayList<String>> getCartTypeArrayList()
    {
        HashMap<String, ArrayList<String>> stringArrayListHashMap = new HashMap<>();
        stringArrayListHashMap.put("carType",cartTypeArrayList);
        stringArrayListHashMap.put("carstation",carstationList);
        return stringArrayListHashMap;
    }

    public void delectcarTypeAndcarstation(ArrayList<String> carType,ArrayList<String> carstations)
    {
        System.out.println(carType);
        if (carType!=null&&!carType.isEmpty())
        {
            for (String oneCarType:carType)
            {

                    cartTypeArrayList.remove(oneCarType);

            }
        }

        if (carstations!=null&&!carstations.isEmpty())
        {
            for (String onecarStation:carstations)
            {
                carstationList.remove(onecarStation);

            }
        }
    }


    public List<String> selectType()
    {
        List<String> s = defectrecordMapperl.selectTppe();
        return s;
    }
    public  List<String> selectdefectmode()
    {
        List<String> s = defectrecordMapperl.selectdefectMode();
        return s;
    }
    public  List<String> selectdefectpart()
    {
        List<String> s = defectrecordMapperl.selectdefectPart();
        return s;
    }
    public  List<String> selectspecifpart()
    {
        List<String> strings = defectrecordMapperl.selectspecificPart();
        return strings;
    }
    public  List<String> selectremark()
    {
        List<String> selectremark = defectrecordMapperl.selectremark();
        return selectremark;
    }

    public boolean isVin(String vin)
    {
        DefectrecordExample defectrecordExample = new DefectrecordExample();
        DefectrecordExample.Criteria criteria = defectrecordExample.createCriteria();
        criteria.andVinEqualTo(vin);
        List<Defectrecord> defectrecords = defectrecordMapperl.selectByExample(defectrecordExample);
        if (defectrecords==null||defectrecords.isEmpty())
        {
            return false;
        }
        else
        {
            return true;
        }

    }


}
