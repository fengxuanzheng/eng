package com.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pojo.Defectrecord;
import com.pojo.DefectrecordBase64;
import com.pojo.DefectrecordWithBLOBs;
import com.service.DefectrecordService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.*;

@Controller
public class DefectrecordController {
    @Autowired
    private DefectrecordService defectrecordService;

    private final HashMap<String,String> collectionPhotoname=new HashMap<>();



    @PostMapping("/insertDefectrecord")
    @ResponseBody
    public Map<String, Object> insertdefectrecord(@Valid @RequestBody(required = false) DefectrecordWithBLOBs defectrecord, BindingResult bindingResult)
    {
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        if (!bindingResult.hasErrors())
        {
            String insertdefectrecord = defectrecordService.insertdefectrecord(defectrecord);
            Integer id = defectrecord.getId();
            stringObjectHashMap.put("getid",id);
            stringObjectHashMap.put("datas",insertdefectrecord);
        }
        else
        {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError fieldError:fieldErrors)
            {
                stringObjectHashMap.put(fieldError.getField(),fieldError.getDefaultMessage());
            }
        }

        return stringObjectHashMap;
    }

    @RequestMapping("/insertPhoto")
    @ResponseBody
    public String insertphoto(@RequestParam(value = "getid",required = false) Integer id, @RequestParam(value = "defectPhoto",required = false) MultipartFile[] multipartFile,HttpServletRequest httpServletRequest) throws IOException {
        try {
            ArrayList<byte[]> bytes1 = new ArrayList<>();
            for (int i=0;i<multipartFile.length;i++) {


                if (!multipartFile[i].isEmpty()) {
                    String originalFilename = multipartFile[i].getOriginalFilename();
                    String[] split = originalFilename.split("\\.");
                    String s = split[split.length - 1];
                    if ("jpg".equalsIgnoreCase(s) || "png".equalsIgnoreCase(s)) {
                        collectionPhotoname.put(id+""+i, s);
                        bytes1.add(multipartFile[i].getBytes());

                    }
                    else
                    {
                        defectrecordService.deletephoto(id,httpServletRequest,collectionPhotoname);
                        return null;
                    }
                }
            }
            String insertphoto = defectrecordService.insertphoto(id, bytes1);
            return insertphoto;
        }catch (Exception e)
        {
            defectrecordService.deletedefectrecord(id);

        }


        return null;
    }

    @RequestMapping("/getdefectrecordbase64")
    @ResponseBody
    @RequiresRoles("管理员")
    public Map<String, Object> getdefectrecordbase64(@RequestParam(value = "page",defaultValue = "1") Integer page, @RequestParam(value = "sizes",defaultValue = "5") Integer sizes, HttpServletRequest httpServletRequest) throws IOException {

        Page<Object> objects = PageHelper.startPage(page, sizes);
        List<DefectrecordBase64> defectrecordBase64 = defectrecordService.getDefectrecordBase64(httpServletRequest,collectionPhotoname);
        long total = objects.getTotal();
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("datas",defectrecordBase64);
        stringObjectHashMap.put("total",total);
        return stringObjectHashMap;
    }

    @RequestMapping("/deleteDefectrecord")
    @ResponseBody
    @RequiresRoles("管理员")
    public String delectDefectrecord(@RequestParam(value = "delectid",required = false) Integer id,HttpServletRequest httpServletRequest)
    {
        String deletedefectrecord = defectrecordService.deletephoto(id,httpServletRequest,collectionPhotoname);
        return deletedefectrecord;
    }

    @RequestMapping("/sortTable")
    @ResponseBody
    @RequiresRoles("管理员")
    public Map<String,Object> sortTable(String vin, HttpServletRequest httpServletRequest, @RequestParam(value = "page",defaultValue = "1") Integer page,  @RequestParam(value = "sizes",defaultValue = "5") Integer sizes)
    {

        Page<Object> objects = PageHelper.startPage(page, sizes);
        DefectrecordBase64 defectrecordBase64 = defectrecordService.sortTable(vin, httpServletRequest, collectionPhotoname);
        long total = objects.getTotal();
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("datas",defectrecordBase64);
        stringObjectHashMap.put("total",total);
        return stringObjectHashMap;
    }

    @RequestMapping("/productFTQ")
    @ResponseBody
    @RequiresRoles("管理员")
    public String productFTQ()
    {
       return defectrecordService.productFPQ();
    }

    @RequestMapping("/addcartType")

    public void addcarType(@RequestParam(value = "carTypeList",required = false) ArrayList<String> stringArrayList,@RequestParam(value = "carstation",required = false) ArrayList<String> carstation, HttpServletResponse httpServletResponse)
    {
        defectrecordService.addcarType(stringArrayList,carstation);
    }

    @RequestMapping("/getcartType")
    @ResponseBody
    public Map<String,ArrayList<String>> getcarType()
    {
        return defectrecordService.getCartTypeArrayList();
    }

    @RequestMapping("/delectcartType")
    @ResponseBody
    public void delectcarType(@RequestParam(value = "delectcarTypeList",required = false) ArrayList<String> carType,@RequestParam(value = "delectcarstationList",required = false) ArrayList<String> carstation,HttpServletResponse httpServletResponse)
    {
        /*System.out.println("删除方法接受前端多数据" + carType );
        ArrayList<String> carTypeList = new ArrayList<>();
        ArrayList<String> carstationList = new ArrayList<>();
        if (carType!=null&&carType.length!=0)
        {
            carTypeList.addAll(Arrays.asList(carType));
        }
        if (carstation!=null&&carstation.length!=0)
        {
            carstationList.addAll(Arrays.asList(carstation));
        }*/
        defectrecordService.delectcarTypeAndcarstation(carType,carstation);
    }



    @GetMapping("/gettype")
    @ResponseBody
    public List<String> selecttype()
    {
        List<String> strings = defectrecordService.selectType();
        return strings;
    }
    @GetMapping("/getdefectpart")
    @ResponseBody
    public List<String> getdefectpart()
    {
        return defectrecordService.selectdefectpart();
    }

    @GetMapping("/getsecifpart")
    @ResponseBody

    public List<String> getsecifpart()
    {
        return defectrecordService.selectspecifpart();
    }

    @GetMapping("/getdefectmod")
    @ResponseBody
    public List<String> getdefectmod()
    {
        return defectrecordService.selectdefectmode();
    }

    @GetMapping("/getremark")
    @ResponseBody
    public List<String> getremark()
    {
        return defectrecordService.selectremark();
    }

}
