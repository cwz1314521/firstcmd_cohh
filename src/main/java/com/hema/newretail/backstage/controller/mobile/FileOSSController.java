package com.hema.newretail.backstage.controller.mobile;

import com.hema.newretail.CloudBohhApplication;
import com.hema.newretail.backstage.common.queryparam.mobile.IngredientNameByIdCondition;
import com.hema.newretail.backstage.common.utils.Response;
import com.hema.newretail.backstage.common.utils.StringUtil;
import com.hema.newretail.backstage.common.utils.UploadFileUtil;
import com.hema.newretail.backstage.model.moible.InitializeBo;
import com.hema.newretail.backstage.model.moible.VioceBo;
import com.hema.newretail.backstage.service.mobile.FileOSSService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Department 新零售
 * @ClassName FileOSSController
 * @Description 提供给文件中转操作
 * @Author ---CWZ
 * @Date 2018/11/16 10:16
 * @Version 1.0
 **/
@Api(description =  "≡(▔﹏▔)≡提供给文件中转操作接口")
@Controller
@RequestMapping("/mobile")
public class FileOSSController {


    @Autowired
    private FileOSSService fileOSSService;
    private static final Logger logger = LoggerFactory.getLogger(CloudBohhApplication.class);

    /**
     *
     * 功能描述: 根据原料id查询原料名字 ---  加入redis缓存
     *
     * @param: IngredientNameByIdCondition
     * @return: Response
     * @author: cwz
     * @date: 2018/11/16 10:22
     */
    @ApiOperation("≡(▔﹏▔)≡根据原料id查询原料名字")
    @PostMapping("/ingredientNameById")
    @ResponseBody
    public Response IngredientNameById(@RequestParam("id") Long  id){

        return fileOSSService.IngredientNameById(id);
    }
    /**
     *
     * 功能描述: 文件转存
     *
     * @param:
     * @return:
     * @author: cwz
     * @date: 2018/11/16 14:00
     */
    @ApiOperation("≡(▔﹏▔)≡机器日志上传")
    @PostMapping("/uploadTxt")
    @ResponseBody
    public  Response uploadTxt(@RequestBody MultipartFile  file)throws Exception{

        InputStream ins = file.getInputStream();
        File file1=new File(file.getOriginalFilename());
        inputStreamToFile(ins, file1);
        File del = new File(file1.toURI());
                    String url = UploadFileUtil.uploadLocalFile(del, "log/",file1.getName());
                    if (url == null) {
                        //TODO:上传失败的业务处理
                        return Response.failure("上传失败");
                    }
                    logger.info("上传完毕,访问地址:"+url);
                    del.delete();
                    return Response.success(url);
    }

    private void inputStreamToFile(InputStream ins,File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     *
     * 功能描述: 初始化
     *
     * @param:
     * @return:
     * @author: cwz
     * @date: 2018/11/27 10:06
     */
    @ApiOperation("≡(▔﹏▔)≡初始化")
    @PostMapping("/initialize")
    @ResponseBody
    public  Response initialize(){
        InitializeBo initializeBo = new InitializeBo();
        String name="呼保义" +
                ",玉麒麟" +
                ",智多星" +
                ",入云龙" +
                ",豹子头" +
                ",霹雳火" +
                ",小李广" +
                ",小旋风" +
                ",扑天雕" +
                ",美髯公" +
                ",鲁智深" +
                ",武松" +
                ",双枪将" +
                ",天捷星" +
                ",金枪手" +
                ",急先锋" +
                ",神行太保" +
                ",李逵" +
                ",史进" +
                ",天寿星" +
                ",立地太岁" +
                ",天平星" +
                ",浪里白跳" +
                ",神机军师" +
                ",神算子" +
                ",神医" +
                ",安其拉" +
                ",白起" +
                ",不知火舞" +
                ",妲己狄" +
                ",仁杰" +
                ",典韦" +
                ",韩信" +
                ",老夫子" +
                ",刘邦" +
                ",刘禅" +
                ",鲁班七号" +
                ",墨子" +
                ",孙膑" +
                ",孙尚香" +
                ",孙悟空" +
                ",项羽" +
                ",亚瑟" +
                ",周瑜" +
                ",庄周" +
                ",蔡文姬" +
                ",甄姬" +
                ",廉颇" +
                ",程咬金" +
                ",后羿" +
                ",扁鹊" +
                ",钟无艳" +
                ",小乔" +
                ",王昭君" +
                ",虞姬" +
                ",李元芳" +
                ",张飞" +
                ",刘备" +
                ",牛魔张良" +
                ",兰陵王" +
                ",露娜" +
                ",貂蝉" +
                ",达摩" +
                ",曹操" +
                ",芈月" +
                ",荆轲" +
                ",高渐离" +
                ",钟馗" +
                ",花木兰" +
                ",关羽" +
                ",李白" +
                ",宫本武藏" +
                ",吕布" +
                ",嬴政" +
                ",娜可露露" +
                ",武则天" +
                ",赵云" +
                ",姜子牙" +
                ",哪吒" +
                ",诸葛亮" +
                ",黄忠" +
                ",大乔" +
                ",东皇太一" +
                ",庞统" +
                ",鬼谷子" +
                ",女娲" +
                ",如来佛祖" +
                ",观音菩萨" +
                ",伽叶" +
                ",金蝉子" +
                ",灵吉菩萨" +
                ",弥勒佛" +
                ",巨灵神" +
                ",木咤" +
                ",地藏王菩萨" +
                ",十八罗汉" +
                ",斗战胜佛" +
                ",净坛使者" +
                ",八部天龙" +
                ",哪吒三太子" +
                ",太上老君" +
                ",太白金星" +
                ",托塔李天王" +
                ",玉皇大帝" +
                ",西王母" +
                ",二十八宿" +
                ",二郎神" +
                ",菩提祖师" +
                ",寿星" +
                ",镇元大仙" +
                ",阎王" +
                ",昴日星官" +
                ",嫦娥" +
                ",千里眼" +
                ",顺风耳" +
                ",四海龙王" +
                ",东海龙王" +
                ",南海龙王" +
                ",北海龙王" +
                ",西海龙王" +
                ",三清" +
                ",四大天王" +
                ",土地爷" +
                ",赤脚大仙" +
                ",揭谛" +
                ",九曜星" +
                ",大力鬼王" +
                ",六丁六甲" +
                ",城隍" +
                ",福星" +
                ",禄星" +
                ",东华帝君" +
                ",日游神" +
                ",风婆" +
                ",推云童子" +
                ",布雾郎君" +
                ",雷公" +
                ",电母" +
                ",火德星君" +
                ",水德星君" +
                ",混世魔王" +
                ",平天大圣" +
                ",复海大圣" +
                ",混天大圣" +
                ",移山大圣" +
                ",通风大圣" +
                ",驱神大圣" +
                ",牛魔王" +
                ",黑风怪" +
                ",凌虚子" +
                ",黄风大王" +
                ",白骨精" +
                ",黄袍怪" +
                ",金角大王" +
                ",银角大王" +
                ",精细鬼" +
                ",伶俐虫" +
                ",巴山虎" +
                ",依海龙" +
                ",九尾狐狸精" +
                ",乌鸡国假国王" +
                ",铁扇公主" +
                ",红孩儿" +
                ",云里雾" +
                ",雾里云" +
                ",急如火" +
                ",快如风" +
                ",兴烘掀" +
                ",掀烘兴" +
                ",黑水河鼍龙怪" +
                ",虎力大仙" +
                ",鹿力大仙" +
                ",羊力大仙" +
                ",灵感大王" +
                ",独角兕大王" +
                ",六耳猕猴" +
                ",金鼻白毛老鼠精" +
                ",林黛玉" +
                ",薛宝钗" +
                ",贾元春" +
                ",贾迎春" +
                ",贾探春" +
                ",贾惜春" +
                ",李纨" +
                ",妙玉" +
                ",史湘云" +
                ",王熙凤" +
                ",贾巧姐" +
                ",秦可卿" +
                ",晴雯" +
                ",麝月" +
                ",盘古" +
                ",饕餮" +
                ",毕方" +
                ",肥遗" +
                ",三足乌" +
                ",九尾狐" +
                ",强良" +
                ",宙斯" +
                ",赫拉" +
                ",波塞冬" +
                ",得墨忒尔" +
                ",阿波罗" +
                ",阿瑞斯" +
                ",哈迪斯" +
                ",卡俄斯" +
                ",盖亚" +
                ",厄洛斯" +
                ",厄瑞玻斯" +
                ",倪克斯" +
                ",埃特尔" +
                ",赫墨拉" +
                ",卡戎" +
                ",蓬托斯" +
                ",克罗诺斯" +
                ",瑞亚" +
                ",俄刻阿诺斯" +
                ",科俄斯" +
                ",克利俄斯" +
                ",伊阿珀托斯" +
                ",福柏" +
                ",塔那托斯" +
                ",修普诺斯" +
                ",阿特洛波斯" +
                ",拉刻西斯" +
                ",克罗托" +
                ",摩洛斯" +
                ",欧律比亚" +
                ",海神女" +
                ",普洛托" +
                ",特里同" +
                ",米诺斯" +
                ",拉达曼迪斯" +
                ",卡吕普索" +
                ",阿刻罗俄斯" +
                ",阿尔库俄纽斯" +
                ",阿玛耳忒亚" +
                ",伊里丝" +
                ",阿尔刻" +
                ",琉喀忒亚" +
                ",珀耳塞" +
                ",克吕墨涅" +
                ",喀耳刻" +
                ",普洛透斯" +
                ",绪任克斯" +
                ",帕拉斯" +
                ",厄斯" +
                ",格劳克斯" +
                ",塔罗斯" +
                ",墨诺提俄斯" +
                ",比亚" +
                ",克拉托斯" +
                ",丘比特" +
                ",奔波儿灞" +
                ",灞波儿奔";
        List<String> list = StringUtil.StringsToString(name);
        initializeBo.setNickname(list);
        initializeBo.setTel("0531-88888888");
        initializeBo.setSlogan("欢迎！");
        List<VioceBo> list1 = new ArrayList<>();
        VioceBo vioceBo = new VioceBo();
        vioceBo.setRemark("默认");
        vioceBo.setLanguage("中英文（普通话）");
        vioceBo.setName("小燕");
        vioceBo.setParameterCode("xiaoyan");
        vioceBo.setType("青年女声");
        VioceBo vioceBo1 = new VioceBo();
        vioceBo1.setRemark("默认");
        vioceBo1.setLanguage("英文");
        vioceBo1.setName("凯瑟琳");
        vioceBo1.setParameterCode("catherine");
        vioceBo1.setType("青年女声");
        list1.add(vioceBo);
        list1.add(vioceBo1);
        initializeBo.setVoice(list1);
        initializeBo.setCups(420);
        return Response.success(initializeBo);
    }

    /**
     *
     * 功能描述: 机器信息
     *
     * @param:
     * @return:
     * @author: cwz
     * @date: 2018/11/27 10:06
     */
    @ApiOperation("≡(▔﹏▔)≡机器信息")
    @PostMapping("/machine")
    @ResponseBody
    public  Response machine( @RequestParam("uuid") String uuid){
        return fileOSSService.machine(uuid);
    }
}
