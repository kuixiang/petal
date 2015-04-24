package com.sunflower.petal.service;

import com.sunflower.petal.entity.AssemblyRule;
import com.sunflower.petal.entity.Material;
import com.sunflower.petal.entity.MaterialCategory;
import com.sunflower.petal.entity.Product;
import com.sunflower.petal.utils.FileUtil;
import com.sunflower.petal.service.support.CellIterator;
import com.sunflower.petal.service.support.ImportStatus;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import static com.sunflower.petal.service.support.ExcelConstants.*;

/**
 * Created by xiangkui on 14-2-22.
 */
@Component
public class ExcelPumberService {
    private Logger logger= LoggerFactory.getLogger(ExcelPumberService.class);
    @Autowired
    private  MaterialService materialService;
    @Autowired
    private  MaterialCategoryService materialCategoryService;
    @Autowired
    private AssemblyService assemblyService;
    @Autowired
    ProductService productService;

    private Cell getCell(Sheet sheet,int x,int y){
        return sheet.getCell(x,y);
    }
    /**
     * 同步方法，导数据
     * Excel表格数据源
     * @return 导数结果
     */
    public  ImportStatus produceInfo(File file){
       if(!"xls".endsWith(FileUtil.getExtName(file))){
           ImportStatus status= new ImportStatus();
           status.setResult(ImportStatus.Status.ERROR);
           status.setMessage(" cannot support file is not xls,ignore it");
       }
        ImportStatus status=new ImportStatus();
        Workbook book=null;
        try {
             book=Workbook.getWorkbook(file);
            //获得第一个工作表对象
            Sheet sheet=book.getSheet(0);
            int rows=sheet.getRows();
            int counts=sheet.getColumns();
            int end=Math.min(rows,end_row);//避免遍历越界
            //得到第一列第一行的单元格
            long time=System.currentTimeMillis();
            String timeStr=DateFormatUtils.format(time,"yyyyMMddHHmmss");
            Product product=new Product();
            AssemblyRule rule=new AssemblyRule();
            Cell productNameCell=sheet.getCell(title_colum,title_row);
            Cell productLogCell=sheet.getCell(log_colum,log_row);
            product.setName(productNameCell.getContents());
//            product(productLogCell.getContents());

//            //prodcut 入库
//            productService.putProductByName(product);
//            //更新本地变量
//            product=productService.getProductByName(product.getName());
            rule.setProduct(product);
            for(int i=start_row;i<end;i++){
                CellIterator iterator=new CellIterator(sheet, CellIterator.IteratorModel.HORIZON,i,xuhao_column,beizhu_column);
                Material material=new Material();
                material.setId(System.currentTimeMillis());//手工生成一个Id
                int count=0;
                String beizhu="";
                boolean flag=true;//立即停止当前行标志
                while(flag&&iterator.hasNext()){
                    Cell cell=iterator.next();
                    int column=cell.getColumn();
                    String content=cell.getContents();
                    //信息源归档
                    try {//迭代过程中捕获异常信息
                        switch (column){
                            case xuhao_column:
//                                material.setIdentifier(timeStr+"-"+content);
                                break;
                            case fenlei_column://fenlei
                                if(StringUtils.isNotBlank(content)){
                                    MaterialCategory category=new MaterialCategory(null,content);
                                    MaterialCategory category_db=materialCategoryService.getMaterialByName(content);
                                    if(category_db==null){
                                        //添加一个类别项目
                                        materialCategoryService.saveMaterialCategory(category);
                                    }else{
                                        category=category_db;//以数据库的为标准
                                    }
//                                    material.setMaterialCategory(category);
                                }
                                break;
                            case name_guige_column:
                                material.setName(content);
                                if(StringUtils.isBlank(content))
                                   flag=false;//立即停止迭代
                                break;
                            case count_column://材料所需数量
                                count=Integer.parseInt(content);
                                break;
                            case danjia_column:
                                material.setJinjia(Double.parseDouble(content));
                                break;
                            case heji_colum:
                                //ignore
                                break;
                            case yanse_column:
                                material.setYanse(content);
                                break;
                            case beizhu_column:
                                beizhu=content;
//                            material.setBeizhu(content);
                                break;
                            default:
                                throw new IllegalStateException("unkwon index of iterate "+column);
                        }

                    }catch(NumberFormatException e){
                        status.putAttachInfo("坐标位置",i+","+column);
                        status.setMessage("数据格式有误");
                        status.setResult(ImportStatus.Status.ERROR);
                        logger.warn(status.toString());
                    }
                } //end of message
                if(StringUtils.isNotBlank(material.getName())){
                    //添加或者更新一项材料
                    materialService.putMaterialByName(material);
                    //更新material值
                    material=materialService.getMaterialByName(material.getName());
                    rule.addMaterial(material,count,beizhu);
                }
            }
            //紧缩
            rule.tighten();
            //合法
            if(rule.isIllegal()){
                //规则单入库 事物性
//                assemblyService.addOneRule(rule);
                status.setResult(ImportStatus.Status.SUCCESS);
            }else{
               status.setMessage("rule is illegal");
               status.setResult(ImportStatus.Status.ERROR);
            }
        } catch(IOException e) {
            e.printStackTrace();
            status.setResult(ImportStatus.Status.ERROR);
        } catch (BiffException e) {
            e.printStackTrace();
            status.setResult(ImportStatus.Status.ERROR);
        }finally {
            if (book != null) {
                book.close();
            }
        }
        return status;
    }

    /**
     * 异步导数
     * @return
     */
    public  ImportStatus asyncImportInfo(File file){
        throw new UnsupportedOperationException("暂时不支持");
    }
}
