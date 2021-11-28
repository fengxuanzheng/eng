package com.EnergyProject.server;

import com.EnergyProject.dao.HikCodeDAO;
import com.EnergyProject.pojo.HikCode;
import com.EnergyProject.pojo.HikCodeString;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

@Service
public class HikCodeServer extends ServiceImpl<HikCodeDAO,HikCode> {
    @Autowired
    private HikCodeDAO hikCodeDAO;

    public List<HikCodeString> selectHikCodes(LocalDateTime startTime, LocalDateTime endTime)
    {
        ArrayList<HikCodeString> hikCodeStrings = new ArrayList<>();
        List<HikCode> hikCodes = hikCodeDAO.selectHikCodes(startTime, endTime);
       return hikCodeToString(hikCodes,hikCodeStrings);
    }

    public HikCode selectHikCodeUseString(String code)
    {
        byte[] bytes = code.getBytes();
        int length = bytes.length;
        switch (length)
        {
            case 1 ->{
              return   hikCodeDAO.selectOne(new QueryWrapper<HikCode>().eq("code1",bytes[0]).select("[index]","code_time","code1","code2","code3","code4","code5","code6","code7","code8","code9","code10","code11","code12","code13","code14","code15","code16","code17","code18","status"));
            }
            case 2 -> {

               return    hikCodeDAO.selectOne(new QueryWrapper<HikCode>().eq("code1",bytes[0]).eq("code2",bytes[1])
                       .select("[index]","code_time","code1","code2","code3","code4","code5","code6","code7","code8","code9","code10","code11","code12","code13","code14","code15","code16","code17","code18","status"));
            }
            case 3 ->{
               return hikCodeDAO.selectOne(new QueryWrapper<HikCode>().eq("code1",bytes[0]).eq("code2",bytes[1]).eq("code3",bytes[2])
                       .select("[index]","code_time","code1","code2","code3","code4","code5","code6","code7","code8","code9","code10","code11","code12","code13","code14","code15","code16","code17","code18","status"));
            }
            case 4 ->{
            return     hikCodeDAO.selectOne(new QueryWrapper<HikCode>().eq("code1",bytes[0]).eq("code2",bytes[1]).eq("code3",bytes[2]).eq("code4",bytes[3])
                    .select("[index]","code_time","code1","code2","code3","code4","code5","code6","code7","code8","code9","code10","code11","code12","code13","code14","code15","code16","code17","code18","status"));
            }
            case 5 ->{
              return   hikCodeDAO.selectOne(new QueryWrapper<HikCode>().eq("code1",bytes[0]).eq("code2",bytes[1]).eq("code3",bytes[2]).eq("code4",bytes[3])
                .eq("code5",bytes[4])
                      .select("[index]","code_time","code1","code2","code3","code4","code5","code6","code7","code8","code9","code10","code11","code12","code13","code14","code15","code16","code17","code18","status"));
            }
            case 6 ->{
                return   hikCodeDAO.selectOne(new QueryWrapper<HikCode>().eq("code1",bytes[0]).eq("code2",bytes[1]).eq("code3",bytes[2]).eq("code4",bytes[3])
                        .eq("code5",bytes[4]).eq("code6",bytes[5])
                        .select("[index]","code_time","code1","code2","code3","code4","code5","code6","code7","code8","code9","code10","code11","code12","code13","code14","code15","code16","code17","code18","status"));
            }
            case 7 ->{
                return   hikCodeDAO.selectOne(new QueryWrapper<HikCode>().eq("code1",bytes[0]).eq("code2",bytes[1]).eq("code3",bytes[2]).eq("code4",bytes[3])
                        .eq("code5",bytes[4]).eq("code6",bytes[5]).eq("code7",bytes[6])
                        .select("[index]","code_time","code1","code2","code3","code4","code5","code6","code7","code8","code9","code10","code11","code12","code13","code14","code15","code16","code17","code18","status"));
            }
            case 8 ->{
                return   hikCodeDAO.selectOne(new QueryWrapper<HikCode>().eq("code1",bytes[0]).eq("code2",bytes[1]).eq("code3",bytes[2]).eq("code4",bytes[3])
                        .eq("code5",bytes[4]).eq("code6",bytes[5]).eq("code7",bytes[6]).eq("code8",bytes[7])
                        .select("[index]","code_time","code1","code2","code3","code4","code5","code6","code7","code8","code9","code10","code11","code12","code13","code14","code15","code16","code17","code18","status"));
            }
            case 9 ->{
                return   hikCodeDAO.selectOne(new QueryWrapper<HikCode>().eq("code1",bytes[0]).eq("code2",bytes[1]).eq("code3",bytes[2]).eq("code4",bytes[3])
                        .eq("code5",bytes[4]).eq("code6",bytes[5]).eq("code7",bytes[6]).eq("code8",bytes[7]).eq("code9",bytes[8])
                        .select("[index]","code_time","code1","code2","code3","code4","code5","code6","code7","code8","code9","code10","code11","code12","code13","code14","code15","code16","code17","code18","status"));
            }
            case 10 ->{
                return   hikCodeDAO.selectOne(new QueryWrapper<HikCode>().eq("code1",bytes[0]).eq("code2",bytes[1]).eq("code3",bytes[2]).eq("code4",bytes[3])
                        .eq("code5",bytes[4]).eq("code6",bytes[5]).eq("code7",bytes[6]).eq("code8",bytes[7]).eq("code9",bytes[8])
                .eq("code10",bytes[9])
                        .select("[index]","code_time","code1","code2","code3","code4","code5","code6","code7","code8","code9","code10","code11","code12","code13","code14","code15","code16","code17","code18","status"));
            }
            case 11 ->{
                return   hikCodeDAO.selectOne(new QueryWrapper<HikCode>().eq("code1",bytes[0]).eq("code2",bytes[1]).eq("code3",bytes[2]).eq("code4",bytes[3])
                        .eq("code5",bytes[4]).eq("code6",bytes[5]).eq("code7",bytes[6]).eq("code8",bytes[7]).eq("code9",bytes[8])
                        .eq("code10",bytes[9]).eq("code11",bytes[10])
                        .select("[index]","code_time","code1","code2","code3","code4","code5","code6","code7","code8","code9","code10","code11","code12","code13","code14","code15","code16","code17","code18","status"));
            }
            case 12 ->{
                return   hikCodeDAO.selectOne(new QueryWrapper<HikCode>().eq("code1",bytes[0]).eq("code2",bytes[1]).eq("code3",bytes[2]).eq("code4",bytes[3])
                        .eq("code5",bytes[4]).eq("code6",bytes[5]).eq("code7",bytes[6]).eq("code8",bytes[7]).eq("code9",bytes[8])
                        .eq("code10",bytes[9]).eq("code11",bytes[10]).eq("code12",bytes[11])
                        .select("[index]","code_time","code1","code2","code3","code4","code5","code6","code7","code8","code9","code10","code11","code12","code13","code14","code15","code16","code17","code18","status"));
            }
            case 13 ->{
                return   hikCodeDAO.selectOne(new QueryWrapper<HikCode>().eq("code1",bytes[0]).eq("code2",bytes[1]).eq("code3",bytes[2]).eq("code4",bytes[3])
                        .eq("code5",bytes[4]).eq("code6",bytes[5]).eq("code7",bytes[6]).eq("code8",bytes[7]).eq("code9",bytes[8])
                        .eq("code10",bytes[9]).eq("code11",bytes[10]).eq("code12",bytes[11]).eq("code13",bytes[12])
                        .select("[index]","code_time","code1","code2","code3","code4","code5","code6","code7","code8","code9","code10","code11","code12","code13","code14","code15","code16","code17","code18","status"));
            }
            case 14 ->{
                return   hikCodeDAO.selectOne(new QueryWrapper<HikCode>().eq("code1",bytes[0]).eq("code2",bytes[1]).eq("code3",bytes[2]).eq("code4",bytes[3])
                        .eq("code5",bytes[4]).eq("code6",bytes[5]).eq("code7",bytes[6]).eq("code8",bytes[7]).eq("code9",bytes[8])
                        .eq("code10",bytes[9]).eq("code11",bytes[10]).eq("code12",bytes[11]).eq("code13",bytes[12]).eq("code14",bytes[13])
                        .select("[index]","code_time","code1","code2","code3","code4","code5","code6","code7","code8","code9","code10","code11","code12","code13","code14","code15","code16","code17","code18","status"));
            }
            case 15 ->{
                return   hikCodeDAO.selectOne(new QueryWrapper<HikCode>().eq("code1",bytes[0]).eq("code2",bytes[1]).eq("code3",bytes[2]).eq("code4",bytes[3])
                        .eq("code5",bytes[4]).eq("code6",bytes[5]).eq("code7",bytes[6]).eq("code8",bytes[7]).eq("code9",bytes[8])
                        .eq("code10",bytes[9]).eq("code11",bytes[10]).eq("code12",bytes[11]).eq("code13",bytes[12]).eq("code14",bytes[13])
                .eq("code15",bytes[14])
                        .select("[index]","code_time","code1","code2","code3","code4","code5","code6","code7","code8","code9","code10","code11","code12","code13","code14","code15","code16","code17","code18","status"));
            }
            case 16 ->{
                return   hikCodeDAO.selectOne(new QueryWrapper<HikCode>().eq("code1",bytes[0]).eq("code2",bytes[1]).eq("code3",bytes[2]).eq("code4",bytes[3])
                        .eq("code5",bytes[4]).eq("code6",bytes[5]).eq("code7",bytes[6]).eq("code8",bytes[7]).eq("code9",bytes[8])
                        .eq("code10",bytes[9]).eq("code11",bytes[10]).eq("code12",bytes[11]).eq("code13",bytes[12]).eq("code14",bytes[13])
                        .eq("code15",bytes[14]).eq("code16",bytes[15])
                        .select("[index]","code_time","code1","code2","code3","code4","code5","code6","code7","code8","code9","code10","code11","code12","code13","code14","code15","code16","code17","code18","status"));
            }
            case 17 ->{
                return   hikCodeDAO.selectOne(new QueryWrapper<HikCode>().eq("code1",bytes[0]).eq("code2",bytes[1]).eq("code3",bytes[2]).eq("code4",bytes[3])
                        .eq("code5",bytes[4]).eq("code6",bytes[5]).eq("code7",bytes[6]).eq("code8",bytes[7]).eq("code9",bytes[8])
                        .eq("code10",bytes[9]).eq("code11",bytes[10]).eq("code12",bytes[11]).eq("code13",bytes[12]).eq("code14",bytes[13])
                        .eq("code15",bytes[14]).eq("code16",bytes[15]).eq("code17",bytes[16])
                        .select("[index]","code_time","code1","code2","code3","code4","code5","code6","code7","code8","code9","code10","code11","code12","code13","code14","code15","code16","code17","code18","status"));
            }
            case 18 ->{
                return   hikCodeDAO.selectOne(new QueryWrapper<HikCode>().eq("code1",bytes[0]).eq("code2",bytes[1]).eq("code3",bytes[2]).eq("code4",bytes[3])
                        .eq("code5",bytes[4]).eq("code6",bytes[5]).eq("code7",bytes[6]).eq("code8",bytes[7]).eq("code9",bytes[8])
                        .eq("code10",bytes[9]).eq("code11",bytes[10]).eq("code12",bytes[11]).eq("code13",bytes[12]).eq("code14",bytes[13])
                        .eq("code15",bytes[14]).eq("code16",bytes[15]).eq("code17",bytes[16]).eq("code18",bytes[17])
                .select("[index]","code_time","code1","code2","code3","code4","code5","code6","code7","code8","code9","code10","code11","code12","code13","code14","code15","code16","code17","code18","status"));
            }
            default -> {
                return null;
            }

        }


    }

    public static List<HikCodeString> hikCodeToString(List<HikCode> source,List<HikCodeString> target)
    {
        source.forEach(item->{
            LocalDateTime codeTime = item.getCodeTime();
            Long index = item.getIndex();
            String status = item.getStatus();
            String code1 = new String(new byte[]{item.getCode1()});
            String code2 = new String(new byte[]{item.getCode2()});
            String code3 = new String(new byte[]{item.getCode3()});
            String code4 = new String(new byte[]{item.getCode4()});
            String code5 = new String(new byte[]{item.getCode5()});
            String code6 = new String(new byte[]{item.getCode6()});
            String code7 = new String(new byte[]{item.getCode7()});
            String code8 = new String(new byte[]{item.getCode8()});
            String code9 = new String(new byte[]{item.getCode9()});
            String code10 = new String(new byte[]{item.getCode10()});
            String code11 = new String(new byte[]{item.getCode11()});
            String code12 = new String(new byte[]{item.getCode12()});
            String code13 = new String(new byte[]{item.getCode13()});
            String code14 = new String(new byte[]{item.getCode14()});
            String code15 = new String(new byte[]{item.getCode15()});
            String code16 = new String(new byte[]{item.getCode16()});
            String code17 = new String(new byte[]{item.getCode17()});
            String code18 = new String(new byte[]{item.getCode18()});
           target.add(new HikCodeString(index,codeTime,code1,code2,code3,code4,code5,code6,code7,code8,code9,code10,code11,code12,code13,code14,code15,code16,code17,code18,status));
        });
        return target;
    }
}
