package com.lj.music_server.utils;

import cn.hutool.system.oshi.OshiUtil;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.NetworkIF;
import oshi.software.os.OSProcess;
import oshi.util.FormatUtil;

import java.util.*;

public class SystemMessageUtils {
    public static void main(String[] args) {
//        //获取CPU信息
//        System.out.println(OshiUtil.getCpuInfo());
//        //获取系统信息
//        System.out.println(OshiUtil.getSystem());
        //获取内存信息
//        System.out.println(OshiUtil.getMemory());
        //获取磁盘信息
        HardwareAbstractionLayer hardware = OshiUtil.getHardware();
        //TB GB  MB  KB  B
        System.out.println(getMemoryInfo(hardware));
    }


    //获取磁盘总容量与可用容量
    public static String[] getDiskInfo() {
        String[] diskInfo = new String[2];
        diskInfo[0] = byteToGB(OshiUtil.getOs().getFileSystem().getFileStores().get(0).getTotalSpace());
        diskInfo[1] = byteToGB(OshiUtil.getOs().getFileSystem().getFileStores().get(0).getUsableSpace());
        return diskInfo;
    }

    //获取内存信息
    public static Map<String, String> getMemoryInfo(HardwareAbstractionLayer hardware) {
        String total = byteToGB(hardware.getMemory().getTotal());
        String available = byteToGB(hardware.getMemory().getAvailable());
        Map<String, String> cpuInfo = new HashMap<>();
        cpuInfo.put("total", total);
        cpuInfo.put("available", available);
        return cpuInfo;
    }

    //获取CPU信息
    public static Map<String, Object> getCpuInfo(HardwareAbstractionLayer HardwareAbstractionLayer) {
        double free = OshiUtil.getCpuInfo().getFree();
        double sys = OshiUtil.getCpuInfo().getSys();
        int num = OshiUtil.getCpuInfo().getCpuNum();
        Map<String, Object> cpuInfo = new HashMap<>();
        //cpu空闲率
        cpuInfo.put("free", free);
        //cpu使用率
        cpuInfo.put("sys", sys);
        //cpu核心数
        cpuInfo.put("num", num);
        return cpuInfo;
    }

    //获取网络信息
    public static Map<String, String> getNetInfo(HardwareAbstractionLayer hardware) {
        //接收数据综合
        long receive = 0;
        //发送数据综合
        long transmit = 0;
        for (NetworkIF item : hardware.getNetworkIFs()) {
            item.updateAttributes();
            receive += item.getBytesRecv();
            transmit += item.getBytesSent();
        }
        Map<String, String> netInfo = new HashMap<>();
        netInfo.put("receive", receive+"");
        netInfo.put("transmit", transmit+"");

        return netInfo;
    }

    //获取进程信息
    public static Map<String, Object> getProcessInfo() {
        List<OSProcess> processes = OshiUtil.getOs().getProcesses(null, new Comparator<OSProcess>() {
            @Override
            public int compare(OSProcess o1, OSProcess o2) {
                return (int) (o2.getProcessCpuLoadCumulative() - o1.getProcessCpuLoadCumulative());
            }
        }, 10);
        Map<String, Object> processInfo = new HashMap<>();
        ArrayList<Map<String, Object>> processName = new ArrayList<>();
        for (OSProcess process : processes) {
            Map<String, Object> processNameMap = new HashMap<>();
            processNameMap.put("pid", process.getProcessID());
            processNameMap.put("name", process.getName());
            processNameMap.put("cpu", process.getProcessCpuLoadCumulative());
            processNameMap.put("memory", process.getResidentSetSize());
            processNameMap.put("thread", process.getThreadCount());
            processNameMap.put("user", process.getUser());
            processName.add(processNameMap);
        }
        processInfo.put("processName", processName);

        return processInfo;
    }


    public static String byteToGB(long size) {
        //小数两位
        return String.format("%.2f", (double) size / 1024 / 1024 / 1024);
    }
}
