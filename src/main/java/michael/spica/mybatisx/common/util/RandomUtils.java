package michael.spica.mybatisx.common.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

/**
 * Created by michael on 2025-11-25.
 */
public class RandomUtils {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Product {
        private String name;
        private String description;
    }

    private static final List<Product> PRODUCTS = Arrays.asList(
            new Product("iPhone 15 Pro", "苹果最新旗舰手机，搭载A17芯片，支持USB-C接口，钛金属边框，专业级摄像头系统。"),
            new Product("华为 Mate 60 Pro", "国产高端智能手机，支持卫星通话，麒麟9000S芯片，鸿蒙操作系统，超可靠玄武架构。"),
            new Product("小米扫地机器人", "智能激光导航，5500Pa大吸力，自动集尘，支持APP远程控制，地毯增压清洁。"),
            new Product("戴森 V15 Detect", "高扭矩吸头，绿色激光探测灰尘，整机HEPA过滤，续航60分钟，适合全屋深度清洁。"),
            new Product("MacBook Air M2", "轻薄便携，13.6英寸Liquid视网膜屏，M2芯片，无风扇设计，续航长达18小时。"),
            new Product("索尼 WH-1000XM5", "行业领先主动降噪耳机，30小时续航，高清音频支持，智能免摘对话功能。"),
            new Product("Switch OLED 游戏机", "7英寸OLED屏幕，增强音频，可拆卸手柄，支持TV模式与掌机模式自由切换。"),
            new Product("SK-II 神仙水", "含90%以上PITERA™，改善肤质、提亮肤色、细腻毛孔，全球畅销护肤精华水。"),
            new Product("Dyson Airwrap 多功能造型器", "无需高温即可打造卷发、直发、蓬松造型，智能温控保护发质，一机多用。"),
            new Product("Apple Watch Ultra 2", "专为极限运动设计，钛金属表壳，双频GPS，100米防水，最长60小时续航。"),
            new Product("乐高 NASA 阿波罗土星五号", "1:110比例复刻登月火箭，含3个宇航员人仔，1969块零件，收藏级拼搭体验。"),
            new Product("Bose QuietComfort 消噪耳塞", "卓越入耳式降噪，舒适贴合耳道，通透模式清晰还原环境音，续航6小时。"),
            new Product("飞利浦电动牙刷 HX9933", "声波震动每分钟31000次，5种清洁模式，AI压力感应，智能追踪刷牙盲区。"),
            new Product("三只松鼠每日坚果", "科学配比6种果仁果干，独立小包装，营养均衡，适合办公、健身、代餐补充能量。"),
            new Product("科沃斯 Deebot T20 Turbo", "热水洗拖布+自动集尘，AI避障识别宠物粪便，毫米级精准建图，解放双手。"),
            new Product("兰蔻小黑瓶精华", "第七代微生态修护精华，深入肌底修护屏障，改善暗沉粗糙，搭配后续护肤品吸收更佳。"),
            new Product("GoPro HERO12 Black", "5.3K超高清视频，HyperSmooth 6.0超强防抖，10米防水，适合滑雪、潜水、骑行等极限拍摄。"),
            new Product("北面 The North Face 冲锋衣", "GORE-TEX 防水透气面料，全压胶接缝，防风保暖，适合登山、徒步、城市通勤。"),
            new Product("任天堂《塞尔达传说：王国之泪》", "开放世界动作冒险神作，自由组合道具解谜，天空、地面、地底三层地图探索。"),
            new Product("欧舒丹乳木果护手霜", "20%乳木果油含量，深层滋养干燥手部肌肤，经典淡雅香气，小巧便携装。")
    );

    /**
     * 随机获取一个商品
     */
    public static Product randomProduct() {
        return RandomUtil.randomEle(PRODUCTS);
    }

    /**
     * 生成订单编号
     */
    public static String generateOrderNo() {
        return DateUtil.format(DateUtil.date(), "yyyyMMddHHmmss") + RandomUtil.randomNumbers(6);
    }
}
