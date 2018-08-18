package com.bank.controller;

import com.bank.bean.Customer;
import com.bank.service.ExcelService;
import com.bank.util.ListUtil;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created by CZ on 2018/8/5.
 */
@Controller
@RequestMapping(value = "/success")
public class EmpMainController {
    @RequestMapping(value = "/excel", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public List<Customer> Excel(@RequestParam(value = "file",required = false) MultipartFile file) throws IOException {
//        try {
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
//            e.printStackTrace();
//        }
//        JFileChooser fileChooser = new JFileChooser();
//        fileChooser.setAcceptAllFileFilterUsed(false);
//        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
//        final String[][] fileENames = {
//                { ".xlsx", "Excel 2008 文件(*.xlsx)" },
//                { ".xls", "MS-Excel 2003 文件(*.xls)" }
//        };
//
//        // 显示所有文件
//        fileChooser.addChoosableFileFilter(new FileFilter() {
//
//            public boolean accept(File file) {
//                return true;
//            }
//
//            public String getDescription() {
//                return "所有文件(*.*)";
//            }
//        });
//        for (final String[] fileEName : fileENames) {
//            fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
//                public boolean accept(File file) {
//                    if (file.getName().endsWith(fileEName[0]) || file.isDirectory()) {
//                        return true;
//                    }
//                    return false;
//                }
//                public String getDescription() {
//                    return fileEName[1];
//                }
//            });
//        }
//        fileChooser.showOpenDialog(null);
//        File file = fileChooser.getSelectedFile();
//        System.out.println(id);
//        int uid = Integer.parseInt(id);
//        modelAndView.setViewName("success");
        List<Customer> list = new ArrayList<Customer>();
        if(file != null){
            int id = Integer.parseInt((file.getOriginalFilename()).substring(0,5));
            System.out.println(id);
            File f = new File("D:/trans.xlsx");
            FileUtils.copyInputStreamToFile(file.getInputStream(),f);
            list = ListUtil.listTrans(id,excelService.uplode(f,id));
//            System.out.println(list);
//            modelMap.addAttribute("userList",list);
//            modelAndView.addObject("list",list);
//            System.out.println("--------------------------------------");
//            System.out.println(list.size());
//            System.out.println("--------------------------------------");
//            System.out.println("转换之后的文件："+file);
        }
        return list;
    }


    @Autowired
    private ExcelService excelService;

    public ExcelService getExcelService() {
        return excelService;
    }

    public void setExcelService(ExcelService excelService) {
        this.excelService = excelService;
    }
}
