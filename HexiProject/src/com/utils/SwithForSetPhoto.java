package com.utils;


import com.pojo.DefectrecordBase64;
import com.pojo.DefectrecordWithBLOBs;

public class SwithForSetPhoto {

    public static void selectPhotoMethod(Integer integer,String photourl,DefectrecordBase64 defectrecordBase64)
    {
        switch (integer)
        {
            case 0:
                defectrecordBase64.setPhotourl(photourl);
                break;
            case 1:
                defectrecordBase64.setPhotourl2(photourl);
                break;
            case 2:
                defectrecordBase64.setPhotourl3(photourl);
                break;
            case 3:
                defectrecordBase64.setPhotourl4(photourl);
                break;
        }
    }


    public static byte[] getPhotobyte(Integer integer, DefectrecordWithBLOBs defectrecordWithBLOBs)
    {
        switch (integer)
        {
            case 0:
               return defectrecordWithBLOBs.getDefectPhoto();

            case 1:
              return defectrecordWithBLOBs.getDefectPhoto2();

            case 2:
                return defectrecordWithBLOBs.getDefectPhoto3();

            case 3:
                return defectrecordWithBLOBs.getDefectPhoto4();

            default:
                return null;
        }
    }

    public static void setphotourl(Integer integer,String photourl,DefectrecordBase64 defectrecordBase64)
    {
        switch (integer)
        {
            case 0:
                defectrecordBase64.setPhotourl(photourl);
                break;
            case 1:
                defectrecordBase64.setPhotourl2(photourl);
                break;
            case 2:
                defectrecordBase64.setPhotourl3(photourl);
                break;
            case 3:
                defectrecordBase64.setPhotourl4(photourl);
                break;
        }
    }
}
