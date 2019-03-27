package cn.itcast.controller;

import cn.itcast.domain.Items;
import cn.itcast.service.ProductService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/product")
public class ProductController {

    //git-test3
    //git-test2
    @Autowired
    private ProductService productService;

    /*新增代码，测试git*/
    @RequestMapping("/git_test")
    public ModelAndView git_test(){
        ModelAndView mv = new ModelAndView();
        List<Items> products =  productService.findAll();

        mv.addObject("itemsList",products);
        mv.setViewName("itemsList");
        return mv;
    }
    @RequestMapping("/findAll")
    public ModelAndView findAll(){
        ModelAndView mv = new ModelAndView();
       List<Items> products =  productService.findAll();

       mv.addObject("itemsList",products);
       mv.setViewName("itemsList");
       return mv;
    }

    @RequestMapping("/findById")
    public ModelAndView findById(String id){
        ModelAndView mv = new ModelAndView();
        Items items = productService.findById(id);
        mv.addObject("items",items);
        mv.setViewName("editItems");
        return mv;
    }

    @RequestMapping("/updateProduct")
    public String updateProduct(Items items ,HttpServletRequest request, MultipartFile pictureFile) throws IOException, ParseException {

        System.out.println(items);
//        System.out.println("name"+request.getParameter("name"));
//        System.out.println("name:"+items.getName());

        //封装参数
        /*Items items = new Items();
        items.setId(Integer.parseInt(request.getParameter("id")));
        items.setName(request.getParameter("name"));
        items.setPrice(Float.parseFloat(request.getParameter("price")));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date createTime = simpleDateFormat.parse(request.getParameter("createtime"));
        items.setCreatetime(createTime);
        items.setDetail(request.getParameter("detail"));*/

        System.out.println(items);
        //创建文件上传文件夹
        String realPath = request.getSession().getServletContext().getRealPath("/upload-img/");
        File file = new File(realPath);
        if (!file.exists()){
            file.mkdirs();
        }

        //生成文件名
//        String uuid = UUID.randomUUID().toString().replace(".", "-");
        String filename = pictureFile.getOriginalFilename();

        /*存在重复文件，先删除*/
        File file1 = new File(realPath,filename);
        //判断文件是否存在
        if (file1.exists()){
            //文件存在就进行删除
            file1.delete();
        }
        items.setPic(filename);
//        filename = uuid+"-"+filename;
//        System.out.println(filename);

        pictureFile.transferTo(file1);

        //调用service完成修改
        productService.update(items);

        return "redirect:findAll";

    }

}

/*
* 判断文件是否存在进行删除的思路
* （1）现在文件是否存在判断
*       （1）判断磁盘（感觉这个比较靠谱一点，先用这个实现）
*            创建一个file，判断当前路径中是否存在这个文件
*            如果存在，就删除该文件，然后重新调用transform方法
*       （2）判断前端过来的数据
*
*
* （2）是否需要接收来自前端的关于文件的数据？
* */
